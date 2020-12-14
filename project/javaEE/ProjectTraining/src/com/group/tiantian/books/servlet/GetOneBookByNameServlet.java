package com.group.tiantian.books.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.tiantian.books.service.HomePageService;
import com.group.tiantian.entity.Book;

/**
 * Servlet implementation class GetOneBookByNameServlet
 */
@WebServlet("/GetOneBookByNameServlet")
public class GetOneBookByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOneBookByNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String bookName=request.getParameter("bookName");
		if(bookName!=null && !bookName.equals("")) {
			HomePageService homePageService=HomePageService.getInstance();
			Book book=homePageService.getBookByName(bookName);
			if(book!=null) {
				String bookStr=new Gson().toJson(book);
				System.out.println(bookStr);
				response.getWriter().write(bookStr);
			}else {
				System.out.println("图书不存在！");
				response.getWriter().write("图书不存在！");
			}
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
