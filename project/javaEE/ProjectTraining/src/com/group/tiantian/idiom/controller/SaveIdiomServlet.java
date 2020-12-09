package com.group.tiantian.idiom.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.entity.IdiomSave;
import com.group.tiantian.idiom.service.IdiomServiceImpl;

/**
 * Servlet implementation class SaveIdiomServlet
 */
@WebServlet("/SaveIdiomServlet")
public class SaveIdiomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveIdiomServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ����������
		InputStream in = request.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
		String str = reader.readLine();
		System.out.println("�յ�����Ҫ�ղصĳ��������Ϣ��" + str);
		if (str != null) {
			String[] strings = str.split("&");
			String idiomName = strings[0];
			String phone = strings[1];
			String childName = strings[2];
			// ����IdiomSave����
			IdiomSave idiomSave = new IdiomSave();
			idiomSave.setIdiomName(idiomName);
			idiomSave.setPhone(phone);
			idiomSave.setChildName(childName);
			// ����IdiomServiceImpl����
			IdiomServiceImpl idiomServiceImpl = new IdiomServiceImpl();
			// ���ݻ�ȡ������Ϣ�ղس���
			boolean flag = idiomServiceImpl.saveIdiomInfo(idiomSave);
			// ��ȡ���������
			OutputStream out = response.getOutputStream();
			String msg = null;
			if (flag) {
				msg = "�����ղسɹ���";
			} else {
				msg = "�����ղ�ʧ�ܣ�";
			}
			out.write(msg.getBytes("utf-8"));
			out.flush();
			// �ر���
			out.close();
		}
	}

}
