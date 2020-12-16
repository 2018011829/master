package com.group.tiantian.server.book.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.entity.Book;
import com.group.tiantian.server.book.service.BookTypeService;

/**
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/UpdateBookServlet")
public class UpdateBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("UpdateBookServlet");
		String userName=request.getParameter("userName");
		String idStr=request.getParameter("id");
		//获取当前页
		String page=request.getParameter("page");
		//默认显示第一页
		int pageNum=1,pageSize=6;
		if(page!=null && !page.equals("")) {
			pageNum=Integer.parseInt(page);
		}
		if(userName!=null && !userName.equals("")) {
			//根据id获取书籍信息
			if(idStr!=null && !idStr.equals("")) {
				int id=Integer.parseInt(idStr);
				Book book=BookTypeService.getInstance().searchBookInfo(id);
				if(book!=null) {
					request.setAttribute("page", pageNum);
					request.setAttribute("userName", userName);
					request.setAttribute("newBook", book);
					request.getRequestDispatcher("updateBook.jsp").forward(request, response);
				}else {
					System.out.println("数据库无该书籍！");
				}
			}else {
				System.out.println("未获取到书籍信息的id！");
			}
		}else {
			System.out.println("您还未登录！");
			response.sendRedirect("error.jsp");
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
