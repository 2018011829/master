package com.group.tiantian.server.idiom.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.server.idiom.service.IdiomTypeService;

/**
 * Servlet implementation class AddIdiom
 */
@WebServlet("/AddIdiom")
public class AddIdiom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddIdiom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("addIdiomÖÐ×ª");
		String userName=request.getParameter("userName");
		if(userName!=null && !userName.equals("")) {
			request.getServletContext().setAttribute("idiomTypes", IdiomTypeService.getInstance().getAllTypes());
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("addIdiom.jsp").forward(request, response);
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
