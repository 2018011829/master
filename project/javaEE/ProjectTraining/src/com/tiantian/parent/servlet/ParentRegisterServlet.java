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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ��뷽ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// �õ��ֻ����롢�ǳơ��������ע��
		String phone = request.getParameter("phone");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		System.out.println("1.��ȡ��ز���");
		ParentService parentService = ParentService.getInstance();
		if (!parentService.isExistPhone(phone)) {
			boolean b = parentService.resigter(phone, nickname, password);
			if (b) {
				response.getWriter().write("success");
				System.out.println("ע��ɹ���" + phone);
			} else {
				response.getWriter().write("�ֻ���ע��ʧ�ܣ�");
				System.out.println("ע��ʧ�ܣ�" + phone);
			}
		}else {
			response.getWriter().write("�ֻ�����ע�ᣡ");
			System.out.println("ע��ʧ�ܣ�" + phone);
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
