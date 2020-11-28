package com.group.tiantian.classifyidiom.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group.tiantian.classifyidiom.service.ClassifyIdiomServiceImpl;

/**
 * 2020-11-25
 * 2020-11-28
 * 
 * @author lrf
 */
@WebServlet("/SendClassifyIdiomServlet")
public class SendClassifyIdiomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendClassifyIdiomServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �������ڱ����������LinkedHashMap
		LinkedHashMap<String, List<String>> idiomMap = null;
		// ����ClassifyIdiomServiceImpl����
		ClassifyIdiomServiceImpl serviceImpl = new ClassifyIdiomServiceImpl();
		// �����ݿ��л�ȡ���������Ϣ
		idiomMap = serviceImpl.getAllClassifyIdiom();
		System.out.println("��ȡ���ĳ�������map��" + idiomMap.toString());
		// ת����json�����ظ��ͻ���
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		String jsonStr = gson.toJson(idiomMap);
		OutputStream out = response.getOutputStream();
		out.write(jsonStr.getBytes("utf-8"));
		out.flush();
		// �ر���
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
