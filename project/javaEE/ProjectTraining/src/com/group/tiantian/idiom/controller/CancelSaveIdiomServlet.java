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
 * Servlet implementation class CancelSaveIdiomServlet
 */
@WebServlet("/CancelSaveIdiomServlet")
public class CancelSaveIdiomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CancelSaveIdiomServlet() {
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
		// 获取网络输入流
		InputStream in = request.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
		String str = reader.readLine();
		System.out.println("收到的需要取消收藏的成语相关信息：" + str);
		if (str != null) {
			String[] strings = str.split("&");
			String idiomName = strings[0];
			String phone = strings[1];
			String childName = strings[2];
			// 创建IdiomServiceImpl对象
			IdiomServiceImpl idiomServiceImpl = new IdiomServiceImpl();
			// 根据成语相关信息查询该成语收藏信息的id
			int id = idiomServiceImpl.findSaveIdiomIdByInfo(phone, childName, idiomName);
			// 根据该成语收藏信息的id值取消收藏
			boolean flag = idiomServiceImpl.cancelSaveIdiomInfo(id);
			// 获取网络输出流
			OutputStream out = response.getOutputStream();
			String msg = null;
			if (flag) {
				msg = "成语取消收藏成功！";
			} else {
				msg = "成语取消收藏失败！";
			}
			out.write(msg.getBytes("utf-8"));
			out.flush();
			// 关闭流
			out.close();
		}
	}

}
