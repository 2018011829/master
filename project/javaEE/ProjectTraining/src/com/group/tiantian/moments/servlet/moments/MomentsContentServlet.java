package com.group.tiantian.moments.servlet.moments;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.moments.service.MomentsService;

/**
 * Servlet implementation class MomentsContentServlet
 */
@WebServlet("/MomentsContentServlet")
public class MomentsContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MomentsContentServlet() {
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
		// ��ȡ��������
		String content = request.getParameter("content");
		System.out.println("�յ�����:" + content);
		// ������Ӧ
		response.getWriter().write("�յ����ݣ�" + content);
		
		ServletContext application = this.getServletContext();
		String time = (String) application.getAttribute("time");//��ȡ��ǰ��˵˵��ʱ��
		String personalPhone = (String) application.getAttribute("personalPhone");//��ȡ��ǰ��˵˵���ֻ���
		System.out.println("�İ�time��"+time);
		System.out.println("�İ�personalPhone��"+personalPhone);
		
		MomentsService addMomentsService =MomentsService.getInstance();
		int momentsId = addMomentsService.serchMomentsId(personalPhone, time);//�õ��ֻ��ź�ʱ���Ӧ��˵˵��id
		System.out.println("�İ�momentsId��"+momentsId);
		addMomentsService.insertContent(content, momentsId, time,personalPhone);//��ͼƬ���ƣ�˵˵id���ֻ��ţ�ʱ��������ݿ�
		
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
