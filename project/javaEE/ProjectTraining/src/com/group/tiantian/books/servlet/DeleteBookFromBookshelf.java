package com.group.tiantian.books.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.tiantian.books.service.BookshelfService;
import com.group.tiantian.entity.Bookshelf;

/**
 * Servlet implementation class DeleteBookFromBookshelf
 */
@WebServlet("/DeleteBookFromBookshelf")
public class DeleteBookFromBookshelf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookFromBookshelf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String bookshelfInfo = request.getParameter("info");
		if (bookshelfInfo != null) {
			// ��Gson��ת�ɶ���
			Bookshelf bookshelf = new Gson().fromJson(bookshelfInfo, Bookshelf.class);
			System.out.println("�����ɾ���飺"+bookshelf.toString());
			// �����ݱ���
			BookshelfService bookshelfService = BookshelfService.getInstance();
			boolean a = bookshelfService.searchBookFromBookshelf(bookshelf);
			if (!a) {
				boolean b = bookshelfService.deleteBookFromBookshelf(bookshelf);
				if (b) {
					System.out.println("ȡ���ɹ�");
					response.getWriter().write("ȡ���ɹ�");
				} else {
					System.out.println("ȡ��ʧ��");
					response.getWriter().write("ȡ��ʧ��");
				}
			}else {
				System.out.println("deleteBookFromBookshelf���ݲ�����");
				response.getWriter().write("��δ�������");
			}
		} else {
			System.out.println("����鵽��ܵ�����Ϊ�գ�");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
