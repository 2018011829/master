package com.group.tiantian.server.book.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.server.book.service.BookTypeService;

/**
 * Servlet implementation class DeleteBookServlet
 */
@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteBookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("GetBookInfoServlet");
		String userName = request.getParameter("userName");
		String idStr = request.getParameter("id");
		// ��ȡ��ǰҳ
		String page = request.getParameter("page");
		// Ĭ����ʾ��һҳ
		int pageNum = 1, pageSize = 6;
		if (page != null && !page.equals("")) {
			pageNum = Integer.parseInt(page);
		}
		if (userName != null && !userName.equals("")) {
			// ����idɾ���鼮 �������鼮չʾ����
			int id = Integer.parseInt(idStr);
			boolean b = BookTypeService.getInstance().deleteBook(id);
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("GetBookInfoServlet?userName="+userName+"&page="+pageNum).forward(request, response);
		} else {
			System.out.println("����δ��¼��");
			response.sendRedirect("error.jsp");
		}
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