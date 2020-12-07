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
 * Servlet implementation class SearchCollectionFromServer
 */
@WebServlet("/SearchCollectionFromServer")
public class SearchCollectionFromServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCollectionFromServer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String bookCollectionInfo = request.getParameter("info");
		if (bookCollectionInfo != null) {
			// ��Gson��ת�ɶ���
			Collection collection = new Gson().fromJson(bookCollectionInfo, Collection.class);
			System.out.println("�ղأ�"+collection.toString());
			// ��ѯ����
			CollectionService collectionService = CollectionService.getInstance();
			boolean b=collectionService.searchCollection(collection);
			if (!b) {
				System.out.println("���Ѿ��ղ�");
				response.getWriter().write("���Ѿ��ղ�");
			}else {
				System.out.println("��û���ղ�");
				response.getWriter().write("��û���ղ�");
			}
		} else {
			System.out.println("�ղ�����Ϊ�գ�");
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
