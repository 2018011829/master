package com.group.tiantian.server.login.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.server.login.service.LoginService;

/**
 * Servlet implementation class UpdateAdminInfoServlet
 */
@WebServlet("/UpdateAdminInfoServlet")
public class UpdateAdminInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAdminInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("UpdateAdminInfoServlet");
		String userName=request.getParameter("userName");
		//根据用户名查找密码
		String userPwd=request.getParameter("userPwd");
		if (userName != null && !userName.equals("")) {
			//修改密码
			boolean b=LoginService.getInstance().updatePwd(userName,userPwd);
			if(b) {
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("AdminInfoServlet?userName="+userName).forward(request, response);
			}else {
				request.setAttribute("errorInfo", "修改失败！");
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("updateAdminInfo.jsp").forward(request, response);
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
