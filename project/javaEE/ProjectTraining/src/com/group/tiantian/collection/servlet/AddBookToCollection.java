package com.group.tiantian.collection.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.tiantian.collection.service.CollectionService;
import com.group.tiantian.entity.Collection;

/**
 * Servlet implementation class AddBookToCollection
 */
@WebServlet("/AddBookToCollection")
public class AddBookToCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBookToCollection() {
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
		String bookCollectionInfo = request.getParameter("info");
		if (bookCollectionInfo != null) {
			// ��Gson��ת�ɶ���
			Collection collection = new Gson().fromJson(bookCollectionInfo, Collection.class);
			System.out.println("�ղأ�"+collection.toString());
			// �����ݱ���
			CollectionService collectionService = CollectionService.getInstance();
			boolean a = collectionService.searchCollection(collection);
			if (a) {
				boolean b = collectionService.addCollection(collection);
				if (b) {
					System.out.println("�ղسɹ�");
					response.getWriter().write("�ղسɹ�");
				} else {
					System.out.println("�ղ�ʧ��");
					response.getWriter().write("�ղ�ʧ��");
				}
			}else {
				System.out.println("AddBookToCollection�����Ѵ���");
				response.getWriter().write("���Ѿ��ղ�");
			}
		} else {
			System.out.println("�ղ�����Ϊ�գ�");
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
