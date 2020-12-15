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
import com.group.tiantian.idiom.service.IdiomHistoryServiceImpl;

/**
 * ��ͻ��˷���������ʷ
 */
@WebServlet("/SendSearchIdiomHistoryServlet")
public class SendSearchIdiomHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendSearchIdiomHistoryServlet() {
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
		System.out.println("�յ�����Ҫ���ҵĳ������������Ϣ��" + str);
		if (str != null) {
			String[] strings = str.split("&");
			String phone = strings[0];
			String childName = strings[1];
			// ����IdiomHistoryServiceImpl����
			IdiomHistoryServiceImpl idiomHistoryServiceImpl = new IdiomHistoryServiceImpl();
			// ���ݻ�ȡ������Ϣ���ҳ���������¼
			List<String> historyList = idiomHistoryServiceImpl.listIdiomSearchHistory(phone, childName);
			String jsonStr = null;
			if (historyList.size() != 0) {
				// ת����json�����ظ��ͻ���
				Gson gson = new Gson();
				jsonStr = gson.toJson(historyList);
			}else {
				jsonStr = "û��������ʷ";
			}
			OutputStream out = response.getOutputStream();
			out.write(jsonStr.getBytes("utf-8"));
			out.flush();
			// �ر���
			out.close();
		}
	}

}
