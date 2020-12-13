package com.group.tiantian.server.login.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.server.login.service.LoginService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("RegisterServlet");
		// 获取表单数据
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		// 判断信息是否为空
		if (userName != null && !userName.equals("")) {
			if (userPwd != null && !userPwd.equals("")) {
				// 检查用输入的信息是否合法
				if (userName.length() < 6 || userName.length() > 10) {
					System.out.println("用户名不合法！");
					request.setAttribute("errorInfo", "用户名位数为1~10位之间！");
					request.setAttribute("userName", userName);
					request.setAttribute("userPwd", userPwd);
					request.getRequestDispatcher("register.jsp").forward(request, response);
				} else {
					if (userPwd.length() < 8 || userPwd.length() > 20) {
						System.out.println("密码不合法！");
						request.setAttribute("errorInfo", "密码位数为1~20位之间！");
						request.setAttribute("userName", userName);
						request.setAttribute("userPwd", userPwd);
						request.getRequestDispatcher("register.jsp").forward(request, response);
					} else {
						// 检查用户是否存在
						LoginService loginService = LoginService.getInstance();
						boolean a = loginService.isExist(userName);
						if (!a) {
							boolean b = loginService.registerUser(userName, userPwd);
							if (b) {
								System.out.println("注册成功！");
								request.getRequestDispatcher("index.jsp").forward(request, response);
							} else {
								System.out.println("注册失败！");
								request.setAttribute("errorInfo", "密码错误！");
								request.setAttribute("userName", userName);
								request.setAttribute("userPwd", userPwd);
								request.getRequestDispatcher("register.jsp").forward(request, response);
							}
						} else {
							System.out.println("用户名已经存在！");
							request.setAttribute("userName", userName);
							request.setAttribute("userPwd", userPwd);
							request.setAttribute("errorInfo", "用户名已经存在！");
							request.getRequestDispatcher("register.jsp").forward(request, response);
						}
					}

				}

			} else {
				request.setAttribute("errorInfo", "密码不能为空！");
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("errorInfo", "用户名不能为空！");
			request.setAttribute("userPwd", userPwd);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
