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
 * Servlet implementation class likegivePersonAndMomentsId
 */
@WebServlet("/LikegivePersonAndMomentsId")
public class LikegivePersonAndMomentsId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 定义Gson对象属性
	private Gson gson;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LikegivePersonAndMomentsId() {
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
		initGson();//初始化gson对象
		// 获取请求数据
		String likegivePerson = request.getParameter("likegivePerson");// 得到点赞人的手机号
		int momentsId = Integer.parseInt(request.getParameter("momentsId"));// 得到被点赞说说的id
		System.out.println("手机号"+likegivePerson + ":" + momentsId);

		LikeGiveService likeGiveService = LikeGiveService.getInstance();// 创建点赞service对象
		PersonalInfo personalInfo = likeGiveService.getPersonalInfo("19831127375");// 根据点赞人的手机号得到点赞人的个人信息
		String likeGiveName = personalInfo.getName();// 得到点赞人的昵称
		likeGiveService.insertLikeGiveInfo(momentsId, likeGiveName, likegivePerson);// 将被点赞说说id，点赞人昵称和点赞人手机号存入数据库
		List<String> likeGiveNames = likeGiveService.likeGiveNames(momentsId);// 获取该条说说点赞人昵称列表
		String json0 = gson.toJson(likeGiveNames);//将点赞人昵称列表构造成json对象
		LikeGiveInfo likeGiveInfo = new LikeGiveInfo(json0, likeGiveNames.size());// 将点赞人列表和点赞人的数量构造成一个点赞信息对象
		//序列化
        String json = gson.toJson(likeGiveInfo);//将信息对象变成json串
		// 返回响应
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
