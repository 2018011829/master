package com.group.tiantian.server.idiom.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.server.entity.IdiomType;
import com.group.tiantian.server.idiom.service.IdiomTypeService;

/**
 * Servlet implementation class addIdiomType
 */
@WebServlet("/AddIdiomType")
public class AddIdiomType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddIdiomType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("addIdiomType中转");
		String userName=request.getParameter("userName");
		if(userName!=null && !userName.equals("")) {
			request.setAttribute("userName", userName);
			//成语类型数
			IdiomTypeService idiomTypeService=IdiomTypeService.getInstance();
			this.getServletContext().setAttribute("idiomTypes", idiomTypeService.getAllTypes());
			request.getRequestDispatcher("addIdiomType.jsp").forward(request, response);
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
