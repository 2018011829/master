package com.group.tiantian.server.book.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.server.book.service.BookTypeService;

/**
 * Servlet implementation class AddBookType
 */
@WebServlet("/AddBookType")
public class AddBookType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("addBookTypeÖÐ×ª");
		String userName=request.getParameter("userName");
		if(userName!=null && !userName.equals("")) {
			this.getServletContext().setAttribute("bookTypes", BookTypeService.getInstance().getAllTypes());
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("addBookType.jsp").forward(request, response);
		}else {
			System.out.println("Äú»¹Î´µÇÂ¼£¡");
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
