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
			// 将Gson串转成对象
			Bookshelf bookshelf = new Gson().fromJson(bookshelfInfo, Bookshelf.class);
			System.out.println("从书架删除书："+bookshelf.toString());
			// 将数据保存
			BookshelfService bookshelfService = BookshelfService.getInstance();
			boolean a = bookshelfService.searchBookFromBookshelf(bookshelf);
			if (!a) {
				boolean b = bookshelfService.deleteBookFromBookshelf(bookshelf);
				if (b) {
					System.out.println("取消成功");
					response.getWriter().write("取消成功");
				} else {
					System.out.println("取消失败");
					response.getWriter().write("取消失败");
				}
			}else {
				System.out.println("deleteBookFromBookshelf数据不存在");
				response.getWriter().write("书未加入书架");
			}
		} else {
			System.out.println("添加书到书架的数据为空！");
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
