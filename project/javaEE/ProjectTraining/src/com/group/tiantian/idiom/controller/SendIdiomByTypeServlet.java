package com.group.tiantian.idiom.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.tiantian.classifyidiom.service.ClassifyIdiomServiceImpl;
import com.group.tiantian.idiom.service.IdiomServiceImpl;

/**
 * 2020-11-28
 * 
 * @author lrf
 */
@WebServlet("/SendIdiomByTypeServlet")
public class SendIdiomByTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendIdiomByTypeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		System.out.println("��ȡ���ĳ��������ͣ�" + str);
		if(null != str) {
			// ����ClassifyIdiomServiceImpl����
			ClassifyIdiomServiceImpl classifyIdiomServiceImpl = new ClassifyIdiomServiceImpl();
			// ���ݳ������������Ʋ�ѯ����������Ӧ��id
			int classification = classifyIdiomServiceImpl.getIdByClassifyName(str);
			//�������ڱ������ļ���
			List<String> idiomList = null;
			// ����IdiomServiceImpl����
			IdiomServiceImpl idiomServiceImpl = new IdiomServiceImpl();
			// ���ݳ����������Ͳ�ѯ����(�����ݿ��л�ȡ�����͵ĳ���)
			idiomList = idiomServiceImpl.listIdiomByClassification(classification);
			System.out.println("��ѯ�����͡�" + str + "����idΪ�� " + classification + " �������ĳ����У�" + idiomList.toString());
			// ת����json�����ظ��ͻ���
			Gson gson = new Gson();
			String jsonStr = gson.toJson(idiomList);
			System.out.println("��ȡ����json����" + jsonStr);
			OutputStream out = response.getOutputStream();
			out.write(jsonStr.getBytes("utf-8"));
			out.flush();
			// �ر���
			out.close();
		}
	}

}
