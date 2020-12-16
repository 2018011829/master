package com.group.tiantian.moments.servlet.moments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group.tiantian.entity.moments.Attention;
import com.group.tiantian.entity.moments.Comment;
import com.group.tiantian.entity.moments.Moments;
import com.group.tiantian.entity.moments.MomentsPicture;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.moments.service.AttentionService;
import com.group.tiantian.moments.service.CommentsService;
import com.group.tiantian.moments.service.LikeGiveService;
import com.group.tiantian.moments.service.MomentsService;
import com.group.tiantian.util.ConfigUtil;

/**
 * Servlet implementation class MineMomentsInfoSerclet
 */
@WebServlet("/MineMomentsInfoSerclet")
public class MineMomentsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 定义Gson对象属性
	private Gson gson;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MineMomentsInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码方式
		// request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 获取请求数据
		String personPhone = request.getParameter("PersonPhone");// 点赞人的手机号
		// Gson对象实例化
		initGson();
		List<Moments> moments = new ArrayList<>();// 说说列表
		moments.clear();
		List<String> picturesUrl = new ArrayList<>();// 图片网络地址列表
		picturesUrl.clear();
		List<MomentsPicture> pictures = new ArrayList<>();// 图片对象列表
		pictures.clear();
		List<String> likeGiveNames = new ArrayList<>();// 点赞昵称列表
		likeGiveNames.clear();
		List<List> listPicture = new ArrayList<>();// 图片对象列表的列表
		listPicture.clear();
		List<Comment> commentInfo = new ArrayList<>();// 评论信息列表
		commentInfo.clear();
		List<Attention> attentionList = new ArrayList<>();// 关注列表
		attentionList.clear();
		String content = null;// 说说文案
		int likegiveboolen = 0;
		PersonalInfo personalInfo = new PersonalInfo();

		// 获取站点根目录下的图片的相对路径
		String root = getServletContext().getContextPath();
		// 生成图片网络路径
		String urlPath = ConfigUtil.SERVICE_ADDRESS + "imgs/";

		MomentsService addMomentsService = MomentsService.getInstance();
		LikeGiveService likeGiveService = LikeGiveService.getInstance();
		CommentsService commentsService = CommentsService.getInstance();
		AttentionService attentionService = AttentionService.getInstance();

		moments = addMomentsService.getMomentsByPhone(personPhone);// 获取所有说说信息
		for (int i=0; i<moments.size();i++) {
			pictures = addMomentsService.getMomentsPicture(moments.get(i).getId());// 根据说说id获取所有图片名称
			content = addMomentsService.getMomentsContent(moments.get(i).getId());// 根据说说id获取说说文案
			personalInfo = addMomentsService.getPersonalInfo(moments.get(i).getPhoneNumber());// 根据电话号码获取头像和昵称
			likeGiveNames = likeGiveService.likeGiveNames(moments.get(i).getId());// 根据说说id获取点赞昵称列表
			likegiveboolen = likeGiveService.selectLikeGiveboolean(moments.get(i).getId(), personPhone);// 根据说说id和用户手机号获取点赞情况
			commentInfo = commentsService.commentsInfo(moments.get(i).getId());// 根据说说id获取评论情况
			attentionList = attentionService.getAttentionList(personPhone);// 根据当前用户手机号获取关注列表
			if (likeGiveNames.isEmpty()) {
				likeGiveNames.add("没有人点赞呦");
			}
			for (int j = 0; j < pictures.size(); j++) {
				String img = urlPath + pictures.get(j).getMomentsPictureUrl();
				picturesUrl.add(img);
			}
			String json1 = gson.toJson(picturesUrl);
			moments.get(i).setPictureUrl(json1);// 设置图片
			moments.get(i).setContent(content);// 设置说说文案
			moments.get(i).setName(personalInfo.getName());// 设置个人昵称
			moments.get(i).setLikegiveboolen(likegiveboolen);// 设置当前用户是否对此说说点赞
			String json2 = gson.toJson(likeGiveNames);
			moments.get(i).setLikeGiveName(json2);// 设置点赞此说说得人的昵称
			String json3 = gson.toJson(commentInfo);
			moments.get(i).setComments(json3);// 设置此说说的评论信息
			String json4 = gson.toJson(attentionList);
			moments.get(i).setAttentionList(json4);
			// 生成头像图片网络路径
			String urlPath1 = ConfigUtil.SERVICE_ADDRESS + "avatar/";
			moments.get(i).setHeadPortraitUrl(urlPath1 + personalInfo.getPhotoUrl());// 设置发说说用户的头像
			picturesUrl.clear();
		}
		Collections.reverse(moments);
		// 序列化
		String json = gson.toJson(moments);
		// 获取网络输出流，并将图片的网络资源路径返回给客户端
		response.getWriter().write(json);
	}

	/**
	 * 初始化Gson对象
	 */
	private void initGson() {
		gson = new GsonBuilder()// 创建GsonBuilder对象
				.setPrettyPrinting()// 格式化输出
				.serializeNulls()// 允许输出Null值属性
				.setDateFormat("YY:MM:dd")// 日期格式化
				.create();// 创建Gson对象
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
