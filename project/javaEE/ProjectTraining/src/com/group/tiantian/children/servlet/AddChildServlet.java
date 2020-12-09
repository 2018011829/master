package com.group.tiantian.children.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.children.service.ChildrenService;

/**
 * Servlet implementation class AddChildServlet
 */
@WebServlet("/AddChildServlet")
public class AddChildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddChildServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取传递过来的孩子的信息
		String name = request.getParameter("name");
		String grade = request.getParameter("grade");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("parentPhone");
		
		ChildrenService childrenService = ChildrenService.getInstance();
		childrenService.addChild(name, grade, sex, phone);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
