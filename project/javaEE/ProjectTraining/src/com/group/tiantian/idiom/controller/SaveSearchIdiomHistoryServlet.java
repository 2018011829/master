package com.group.tiantian.idiom.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.entity.IdiomSearchHistory;
import com.group.tiantian.idiom.service.IdiomHistoryServiceImpl;

/**
 * ���տͻ��˷��͵ĳ������������ʷ
 */
@WebServlet("/SaveSearchIdiomHistoryServlet")
public class SaveSearchIdiomHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveSearchIdiomHistoryServlet() {
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
		System.out.println("�յ�����Ҫ����ĵĳ������������Ϣ��" + str);
		if (str != null) {
			String[] strings = str.split("&");
			String phone = strings[0];
			String childName = strings[1];
			String searchStr = strings[2];
			// ����IdiomSearchHistory����
			IdiomSearchHistory idiomSearchHistory = new IdiomSearchHistory();
			idiomSearchHistory.setPhone(phone);
			idiomSearchHistory.setChildName(childName);
			idiomSearchHistory.setSearchStr(searchStr);
			idiomSearchHistory.setStatus(1);
			// ����IdiomHistoryServiceImpl����
			IdiomHistoryServiceImpl idiomHistoryServiceImpl = new IdiomHistoryServiceImpl();
			// ���ݻ�ȡ������Ϣ�������������¼
			boolean flag = idiomHistoryServiceImpl.saveIdiomSearchHistory(idiomSearchHistory);
			if (flag) {
				System.out.println("����������Ϣ����ɹ���");
			} else {
				System.out.println("����������Ϣ����ʧ�ܣ�");
			}
		}
	}

}
