package com.group.tiantian.books.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.tiantian.books.service.MoreBooksService;
import com.group.tiantian.entity.Book;

/**
 * Servlet implementation class MoreBooksServlet
 */
@WebServlet("/MoreBooksServlet")
public class MoreBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MoreBooksServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * ���ؿͻ�������Ҫ��ĳ�����͵�����ͼ��
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// ��ҳ��ȡͼ�飬ÿ�ε�����Ϊ8��
		int pageSize = 6;
		// ��ȡ�������
		String type = request.getParameter("type");
		// �꼶
		String grades = request.getParameter("grades");
		// ��ǰ�Ѿ��е��鱾����
		String currentListSizeStr = request.getParameter("currentListSize");
		if (type != null) {
			if (currentListSizeStr != null && !currentListSizeStr.equals("")) {
				// ��ȡ�����͵�����ͼ��
				int bookIndex=Integer.parseInt(currentListSizeStr);
				MoreBooksService moreBooksService = MoreBooksService.getInstance();
				List<Book> list = moreBooksService.getAllBookByType(type, grades,bookIndex,pageSize);
				if (list != null && list.size()>0) {
					Gson gson = new Gson();
					String result = gson.toJson(list);
					System.out.println(result);
					response.getWriter().write(result);
				} else {
					System.out.println("û�и����鼮�ˣ�");
					response.getWriter().write("û�и����鼮��");
				}
			}else {
				System.out.println("�յ���size����Ϊ�գ�");
//				response.getWriter().write("δ�ҵ������͵��鼮��");
			}

		} else {
			System.out.println("δ�յ��ͻ��˵�������Ϣ��");
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
