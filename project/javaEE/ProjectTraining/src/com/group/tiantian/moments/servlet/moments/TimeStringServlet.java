package com.group.tiantian.moments.servlet.moments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.tiantian.moments.service.AddMomentsService;

/**
 * Servlet implementation class TimeStringServlet
 */
@WebServlet("/TimeStringServlet")
public class TimeStringServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TimeStringServlet() {
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
		String time = request.getParameter("time");//当前发说说的时间
		String personalPhone = request.getParameter("personalPhone");//当前发说说人的手机号
		String content = request.getParameter("content");//当前说说的文案
		System.out.println("time" + time);
		System.out.println("personalPhone" + personalPhone);

		ServletContext application = this.getServletContext();// 将time写入作用域
		application.setAttribute("time", time);
		application.setAttribute("personalPhone", personalPhone);

		AddMomentsService addMomentsService = AddMomentsService.getInstance();
		addMomentsService.insertPersonalInfo(personalPhone, time);//将时间和手机号存入数据库
		
		int momentsId = addMomentsService.serchMomentsId(personalPhone, time);//得到手机号和时间对应的说说的id
		System.out.println("文案momentsId："+momentsId);
		addMomentsService.insertContent(content, momentsId, time,personalPhone);//将图片名称，说说id，手机号，时间存入数据库

		// 返回响应
		response.getWriter().write("收到数据：" + time);

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
