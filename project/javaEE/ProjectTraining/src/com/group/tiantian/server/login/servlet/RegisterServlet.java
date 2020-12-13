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
		// ��ȡ������
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		// �ж���Ϣ�Ƿ�Ϊ��
		if (userName != null && !userName.equals("")) {
			if (userPwd != null && !userPwd.equals("")) {
				// ������������Ϣ�Ƿ�Ϸ�
				if (userName.length() < 6 || userName.length() > 10) {
					System.out.println("�û������Ϸ���");
					request.setAttribute("errorInfo", "�û���λ��Ϊ1~10λ֮�䣡");
					request.setAttribute("userName", userName);
					request.setAttribute("userPwd", userPwd);
					request.getRequestDispatcher("register.jsp").forward(request, response);
				} else {
					if (userPwd.length() < 8 || userPwd.length() > 20) {
						System.out.println("���벻�Ϸ���");
						request.setAttribute("errorInfo", "����λ��Ϊ1~20λ֮�䣡");
						request.setAttribute("userName", userName);
						request.setAttribute("userPwd", userPwd);
						request.getRequestDispatcher("register.jsp").forward(request, response);
					} else {
						// ����û��Ƿ����
						LoginService loginService = LoginService.getInstance();
						boolean a = loginService.isExist(userName);
						if (!a) {
							boolean b = loginService.registerUser(userName, userPwd);
							if (b) {
								System.out.println("ע��ɹ���");
								request.getRequestDispatcher("index.jsp").forward(request, response);
							} else {
								System.out.println("ע��ʧ�ܣ�");
								request.setAttribute("errorInfo", "�������");
								request.setAttribute("userName", userName);
								request.setAttribute("userPwd", userPwd);
								request.getRequestDispatcher("register.jsp").forward(request, response);
							}
						} else {
							System.out.println("�û����Ѿ����ڣ�");
							request.setAttribute("userName", userName);
							request.setAttribute("userPwd", userPwd);
							request.setAttribute("errorInfo", "�û����Ѿ����ڣ�");
							request.getRequestDispatcher("register.jsp").forward(request, response);
						}
					}

				}

			} else {
				request.setAttribute("errorInfo", "���벻��Ϊ�գ�");
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("errorInfo", "�û�������Ϊ�գ�");
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
