package com.group.tiantian.moments.servlet.attention;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.moments.service.AttentionService;

/**
 * Servlet implementation class DeleteAttention
 */
@WebServlet("/DeleteAttention")
public class DeleteAttention extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAttention() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ��뷽ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// ��ȡ��������
		String personPhone = request.getParameter("personPhone");
		String momentsPhone = request.getParameter("momentsPhone");
		AttentionService attentionService = AttentionService.getInstance();
		attentionService.deleteAttention(personPhone, momentsPhone);

		// ������Ӧ
		response.getWriter().write("�յ����ݣ�" + personPhone);
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
