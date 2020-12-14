package com.group.tiantian.moments.servlet.likeGive;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group.tiantian.entity.moments.LikeGiveInfo;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.moments.service.LikeGiveService;

/**
 * Servlet implementation class LikegivePersonAndMomentsIdDelete
 */
@WebServlet("/LikegivePersonAndMomentsIdDelete")
public class LikegivePersonAndMomentsIdDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 定义Gson对象属性
		private Gson gson;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LikegivePersonAndMomentsIdDelete() {
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
		initGson();// 初始化gson对象
		// 获取请求数据
		String likegivePerson = request.getParameter("likegivePerson");// 得到点赞人的手机号
		int momentsId = Integer.parseInt(request.getParameter("momentsId"));// 得到被点赞说说的id
		System.out.println("手机号" + likegivePerson + ":" + momentsId);
		
		LikeGiveService likeGiveService = LikeGiveService.getInstance();// 创建点赞service对象
		likeGiveService.deleteLikeGiveInfo(momentsId, likegivePerson);
		
		
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
