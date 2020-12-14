package com.group.tiantian.moments.servlet.attention;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.moments.service.AttentionService;

/**
 * Servlet implementation class AddAttentionServlet
 */
@WebServlet("/AddAttentionServlet")
public class AddAttentionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddAttentionServlet() {
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
		String personPhone = request.getParameter("personPhone");
		String momentsPhone = request.getParameter("momentsPhone");
		AttentionService attentionService = AttentionService.getInstance();
		attentionService.insertAttention(personPhone, momentsPhone);
		System.out.println("收到手机号和发说说人手机号:"+personPhone+"+"+momentsPhone);

		// 返回响应
		response.getWriter().write("收到数据：" + personPhone);
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
