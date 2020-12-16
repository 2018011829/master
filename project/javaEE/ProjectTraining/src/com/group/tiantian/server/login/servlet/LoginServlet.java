package com.group.tiantian.server.login.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.server.login.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		System.out.println("LoginServlet");
		// ��ȡ������
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		// �ж���Ϣ�Ƿ�Ϊ��
		if (userName != null && !userName.equals("")) {
			if (userPwd != null && !userPwd.equals("")) {
				// ����û��Ƿ����
				LoginService loginService = LoginService.getInstance();
				boolean a = loginService.isExist(userName);
				if (a) {
					boolean b = loginService.isExistUser(userName, userPwd);
					if (b) {
						System.out.println("�û����ڣ�");
						request.getSession().setAttribute("userName", userName);
						request.getRequestDispatcher("HomeServlet").forward(request, response);
					} else {
						System.out.println("�������");
						request.setAttribute("errorInfo", "�������");
						request.setAttribute("userName", userName);
						request.setAttribute("userPwd", userPwd);
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
				} else {
					System.out.println("�û��������ڣ�");
					request.setAttribute("userName", userName);
					request.setAttribute("userPwd", userPwd);
					request.setAttribute("errorInfo", "���û������ڣ�");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("errorInfo", "���벻��Ϊ�գ�");
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("errorInfo", "�û�������Ϊ�գ�");
			request.setAttribute("userPwd", userPwd);
			request.getRequestDispatcher("index.jsp").forward(request, response);
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
