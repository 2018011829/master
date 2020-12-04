package com.group.tiantian.moments.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.moments.service.AddMomentsService;

/**
 * Servlet implementation class MomentsContentServlet
 */
@WebServlet("/MomentsContentServlet")
public class MomentsContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MomentsContentServlet() {
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
		String content = request.getParameter("content");
		System.out.println("收到数据:" + content);
		// 返回响应
		response.getWriter().write("收到数据：" + content);
		
		ServletContext application = this.getServletContext();
		String time = (String) application.getAttribute("time");//获取当前发说说的时间
		String personalPhone = (String) application.getAttribute("personalPhone");//获取当前发说说的手机号
		
		AddMomentsService addMomentsService =AddMomentsService.getInstance();
		int momentsId = addMomentsService.serchMomentsId(personalPhone, time);//得到手机号和时间对应的说说的id
		addMomentsService.insertContent(content, momentsId, time,personalPhone);//将图片名称，说说id，手机号，时间存入数据库
		
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
