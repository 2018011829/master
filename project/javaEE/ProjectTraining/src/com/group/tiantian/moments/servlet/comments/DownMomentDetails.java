package com.group.tiantian.moments.servlet.comments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group.tiantian.entity.moments.Comment;
import com.group.tiantian.entity.moments.Moments;
import com.group.tiantian.entity.moments.MomentsPicture;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.entity.moments.ReplyContent;
import com.group.tiantian.moments.service.MomentsService;
import com.group.tiantian.moments.service.CommentsService;
import com.group.tiantian.moments.service.LikeGiveService;
import com.group.tiantian.moments.service.ReplyContentService;
import com.group.tiantian.util.ConfigUtil;

/**
 * Servlet implementation class DownMomentDetails
 */
@WebServlet("/DownMomentDetails")
public class DownMomentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 定义Gson对象属性
	private Gson gson;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownMomentDetails() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 获取请求数据
		int momentsId = Integer.parseInt(request.getParameter("momentsId"));// 获取需要获取信息的说说id
		String likegivePersonPhone = request.getParameter("likegivePersonPhone");//点赞人的手机号
		
		initGson();// Gson对象初始化
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
		List<ReplyContent> replyContentInfo = new ArrayList<>();//回复信息列表
		replyContentInfo.clear();
		String content = null;// 说说文案
		int likegiveboolen = 0;
		PersonalInfo personalInfo = new PersonalInfo();

		// 获取站点根目录下的图片的相对路径
		String root = getServletContext().getContextPath();
		// 生成图片网络路径
		String urlPath = ConfigUtil.SERVICE_ADDRESS + "imgs/";

		// 获取当前所有说说id，根据说说id获取图片名称和说说文案
		MomentsService addMomentsService = MomentsService.getInstance();
		LikeGiveService likeGiveService = LikeGiveService.getInstance();
		CommentsService commentsService = CommentsService.getInstance();
		ReplyContentService replyContentService = ReplyContentService.getInstance();
		Moments moment = addMomentsService.getMomentsInfo(momentsId);

		pictures = addMomentsService.getMomentsPicture(momentsId);// 根据说说id获取所有图片名称
		content = addMomentsService.getMomentsContent(momentsId);// 根据说说id获取说说文案
		personalInfo = addMomentsService.getPersonalInfo(moment.getPhoneNumber());// 根据电话号码获取头像和昵称
		likeGiveNames = likeGiveService.likeGiveNames(momentsId);// 根据说说id获取点赞昵称列表
		likegiveboolen = likeGiveService.selectLikeGiveboolean(momentsId, likegivePersonPhone);// 根据说说id和用户手机号获取点赞情况
		commentInfo = commentsService.commentsInfo(momentsId);// 根据说说id获取评论情况
		replyContentInfo = replyContentService.replyContentInfo(momentsId);
		//根据说说id获取回复情况
		if (likeGiveNames.isEmpty()) {
			likeGiveNames.add("没有人点赞呦");
		}
		for (int j = 0; j < pictures.size(); j++) {
			String img = urlPath + pictures.get(j).getMomentsPictureUrl();
			picturesUrl.add(img);
		}
		String json1 = gson.toJson(picturesUrl);
		moment.setPictureUrl(json1);// 设置图片
		moment.setContent(content);// 设置说说文案
		moment.setName(personalInfo.getName());// 设置个人昵称
		moment.setLikegiveboolen(likegiveboolen);// 设置当前用户是否对此说说点赞
		String json2 = gson.toJson(likeGiveNames);
		moment.setLikeGiveName(json2);// 设置点赞此说说得人的昵称
		String json3 = gson.toJson(commentInfo);
		moment.setComments(json3);// 设置此说说的评论信息
		String json4 = gson.toJson(replyContentInfo);
		moment.setReplyContent(json4);
		// 生成头像图片网络路径
		String urlPath1 = ConfigUtil.SERVICE_ADDRESS + "avatar/";
		moment.setHeadPortraitUrl(urlPath1 + personalInfo.getPhotoUrl());// 设置发说说用户的头像
		picturesUrl.clear();

		// 序列化
		String json = gson.toJson(moment);

		System.out.println(json);
		// 获取网络输出流，并将图片的网络资源路径返回给客户端
		response.getWriter().write(json);
		// picturesUrl.toString().substring(1, picturesUrl.toString().length() - 1)
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
