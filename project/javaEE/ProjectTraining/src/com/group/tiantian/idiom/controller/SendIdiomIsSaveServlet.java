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

import com.group.tiantian.idiom.service.IdiomServiceImpl;

/**
 * Servlet implementation class SendIdiomIsSaveServlet
 */
@WebServlet("/SendIdiomIsSaveServlet")
public class SendIdiomIsSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendIdiomIsSaveServlet() {
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
		System.out.println("收到的需要进行判断是否已经收藏的成语相关信息：" + str);
		if(str != null) {
			String[] strings = str.split("&");
			String idiomName = strings[0];
			String phone = strings[1];
			String childName = strings[2];
			// 创建IdiomServiceImpl对象
			IdiomServiceImpl idiomServiceImpl = new IdiomServiceImpl();
			// 根据获取到的信息查询当前成语是否被当前用户的这个孩子收藏
			boolean flag = idiomServiceImpl.findIdiomIsSavedByInfo(phone, childName, idiomName);
			// 获取网络输出流
			OutputStream out = response.getOutputStream();
			String msg = null;
			if(flag) {
				msg = "该成语已被收藏";
			}else {
				msg = "该成语未被收藏";
			}
			out.write(msg.getBytes("utf-8"));
			out.flush();
			// 关闭流
			out.close();
		}
	}

}
