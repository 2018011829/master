package com.group.tiantian.server.book.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.server.book.service.BookTypeService;

/**
 * Servlet implementation class UpdateBookType
 */
@WebServlet("/UpdateBookType")
public class UpdateBookType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("UpdateBookTypeÖÐ×ª");
		String userName = request.getParameter("userName");
		String idStr = request.getParameter("id");
		String type = request.getParameter("type");
		if (userName != null && !userName.equals("")) {
			request.setAttribute("type", type);
			request.setAttribute("idStr", idStr);
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("updateBookType.jsp").forward(request, response);
		} else {
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
