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
 * Servlet implementation class DeleteBookFromCollection
 */
@WebServlet("/DeleteBookFromCollection")
public class DeleteBookFromCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteBookFromCollection() {
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
		System.out.println("取消收藏");
		if (bookCollectionInfo != null) {
			// 将Gson串转成对象
			Collection collection = new Gson().fromJson(bookCollectionInfo, Collection.class);
			System.out.println("取消收藏："+collection.toString());
			// 将数据保存
			CollectionService collectionService = CollectionService.getInstance();
			boolean a = collectionService.searchCollection(collection);
			if (!a) {
				boolean b = collectionService.deleteCollection(collection);
				if (b) {
					System.out.println("取消收藏成功");
					response.getWriter().write("取消收藏成功");
				} else {
					System.out.println("取消收藏失败");
					response.getWriter().write("取消收藏失败");
				}
			}else {
				System.out.println("DeleteBookFromCollection数据不存在");
				response.getWriter().write("您还未收藏哦！");
			}

		} else {
			System.out.println("取消收藏数据为空！");
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
