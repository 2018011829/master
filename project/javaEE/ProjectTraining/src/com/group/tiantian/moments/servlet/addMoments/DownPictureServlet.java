package com.group.tiantian.moments.servlet.addMoments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group.tiantian.entity.moments.Moments;
import com.group.tiantian.entity.moments.MomentsPicture;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.moments.service.AddMomentsService;
import com.group.tiantian.util.ConfigUtil;

/**
 * Servlet implementation class DownLoadImgNameServlet
 */
@WebServlet("/DownPictureServlet")
public class DownPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 定义Gson对象属性
	private Gson gson;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownPictureServlet() {
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
		//request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// Gson对象实例化
		initGson();
		List<Moments> moments = new ArrayList<>();// 说说列表
		moments.clear();
		List<String> picturesUrl = new ArrayList<>();// 图片网络地址列表
		picturesUrl.clear();
		List<MomentsPicture> pictures = new ArrayList<>();// 图片对象列表
		pictures.clear();
		List<List> listPicture = new ArrayList<>();// 图片对象列表的列表
		String content = null;//说说文案
		PersonalInfo personalInfo = new PersonalInfo();
		
		// 获取站点根目录下的图片的相对路径
		String root = getServletContext().getContextPath();
		// 生成图片网络路径
		String urlPath = ConfigUtil.SERVICE_ADDRESS + "imgs/";

		// 获取当前所有说说id，根据说说id获取图片名称和说说文案
		AddMomentsService addMomentsService = AddMomentsService.getInstance();
		moments = addMomentsService.getMoments();// 获取所有说说信息
		
		for (int i = 0; i < moments.size(); i++) {
			pictures = addMomentsService.getMomentsPicture(moments.get(i).getId());//根据说说id获取所有图片名称
			content = addMomentsService.getMomentsContent(moments.get(i).getId());//根据说说id获取说说文案
			personalInfo = addMomentsService.getPersonalInfo(moments.get(i).getPhoneNumber());//根据电话号码获取头像和昵称
			for (int j = 0; j < pictures.size(); j++) {
				String img = urlPath + pictures.get(j).getMomentsPictureUrl();
				picturesUrl.add(img);
			}

			String json1 = gson.toJson(picturesUrl);
			moments.get(i).setPictureUrl(json1);//设置图片
			moments.get(i).setContent(content);//设置说说文案
			moments.get(i).setName(personalInfo.getName());//设置个人昵称
			// 生成头像图片网络路径
			String urlPath1 = ConfigUtil.SERVICE_ADDRESS + "avatar/";
			moments.get(i).setHeadPortraitUrl(urlPath1+personalInfo.getPhotoUrl());
			picturesUrl.clear();
		}
		
		 //序列化
        String json = gson.toJson(moments);

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
