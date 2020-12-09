package com.group.tiantian.moments.servlet.addMoments;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.group.tiantian.moments.service.AddMomentsService;

/**
 * Servlet implementation class MomentsInfoServlet
 */
@WebServlet("/MomentsPicturesServlet")
public class MomentsPicturesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MomentsPicturesServlet() {
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
		// ���ؿͻ��˷�����ͼƬ
		InputStream is = request.getInputStream();
		// ��ȡ���������
		String path = getServletContext().getRealPath("/");
		System.out.println(path);
		String pictureName = "android" + System.currentTimeMillis() + ".jpg";// ͼƬ����
		System.out.println(pictureName);
		OutputStream os = new FileOutputStream(path + "imgs/" + pictureName);
		// ѭ����д
		int b = -1;
		while ((b = is.read()) != -1) {
			os.write(b);
		}
		is.close();
		os.close();
		System.out.println("������ɣ�");
		
		ServletContext application = this.getServletContext();
		String time = (String) application.getAttribute("time");//��ȡ��ǰ��˵˵��ʱ��
		String personalPhone = (String) application.getAttribute("personalPhone");//��ȡ��ǰ��˵˵���ֻ���
		System.out.println("ͼƬtime��"+time);
		System.out.println("ͼƬpersonalPhone��"+personalPhone);
		
		AddMomentsService addMomentsService =AddMomentsService.getInstance();
		int momentsId = addMomentsService.serchMomentsId(personalPhone, time);//�õ��ֻ��ź�ʱ���Ӧ��˵˵��id
		addMomentsService.insertPictureUrl(pictureName, momentsId, time,personalPhone);//��ͼƬ���ƣ�˵˵id���ֻ��ţ�ʱ��������ݿ�
		
		
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
