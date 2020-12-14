package com.group.tiantian.moments.servlet.moments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.tiantian.moments.service.AddMomentsService;

/**
 * Servlet implementation class TimeStringServlet
 */
@WebServlet("/TimeStringServlet")
public class TimeStringServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TimeStringServlet() {
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
		String time = request.getParameter("time");//��ǰ��˵˵��ʱ��
		String personalPhone = request.getParameter("personalPhone");//��ǰ��˵˵�˵��ֻ���
		String content = request.getParameter("content");//��ǰ˵˵���İ�
		System.out.println("time" + time);
		System.out.println("personalPhone" + personalPhone);

		ServletContext application = this.getServletContext();// ��timeд��������
		application.setAttribute("time", time);
		application.setAttribute("personalPhone", personalPhone);

		AddMomentsService addMomentsService = AddMomentsService.getInstance();
		addMomentsService.insertPersonalInfo(personalPhone, time);//��ʱ����ֻ��Ŵ������ݿ�
		
		int momentsId = addMomentsService.serchMomentsId(personalPhone, time);//�õ��ֻ��ź�ʱ���Ӧ��˵˵��id
		System.out.println("�İ�momentsId��"+momentsId);
		addMomentsService.insertContent(content, momentsId, time,personalPhone);//��ͼƬ���ƣ�˵˵id���ֻ��ţ�ʱ��������ݿ�

		// ������Ӧ
		response.getWriter().write("�յ����ݣ�" + time);

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
