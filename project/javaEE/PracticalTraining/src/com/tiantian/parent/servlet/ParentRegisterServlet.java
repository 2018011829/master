package com.tiantian.parent.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tiantian.parent.service.ParentService;

/**
 * Servlet implementation class ParentRegisterServlet
 */
@WebServlet("/ParentRegisterServlet")
public class ParentRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParentRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到手机号码、昵称、密码进行注册
		String phone=request.getParameter("phone");
		String nickname=request.getParameter("nickname");
		String password=request.getParameter("password");
		System.out.println("1.获取相关参数");
		ParentService parentService=ParentService.getInstance();
		response.getWriter().write(""+parentService.resigter(phone, nickname, password));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
