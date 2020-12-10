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
	 * 返回客户端所需要的某种类型的所有图书
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 分页获取图书，每次的数量为8条
		int pageSize = 6;
		// 获取书的类型
		String type = request.getParameter("type");
		// 年级
		String grades = request.getParameter("grades");
		// 当前已经有的书本数量
		String currentListSizeStr = request.getParameter("currentListSize");
		if (type != null) {
			if (currentListSizeStr != null && !currentListSizeStr.equals("")) {
				// 获取该类型的所有图书
				int bookIndex=Integer.parseInt(currentListSizeStr);
				MoreBooksService moreBooksService = MoreBooksService.getInstance();
				List<Book> list = moreBooksService.getAllBookByType(type, grades,bookIndex,pageSize);
				if (list != null && list.size()>0) {
					Gson gson = new Gson();
					String result = gson.toJson(list);
					System.out.println(result);
					response.getWriter().write(result);
				} else {
					System.out.println("没有更多书籍了！");
					response.getWriter().write("没有更多书籍了");
				}
			}else {
				System.out.println("收到的size数据为空！");
//				response.getWriter().write("未找到该类型的书籍！");
			}

		} else {
			System.out.println("未收到客户端的需求信息！");
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
