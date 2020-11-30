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
		// 获取网络输入流
		InputStream in = request.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
		String str = reader.readLine();
		System.out.println("获取到的成语子类型：" + str);
		if(null != str) {
			// 创建ClassifyIdiomServiceImpl对象
			ClassifyIdiomServiceImpl classifyIdiomServiceImpl = new ClassifyIdiomServiceImpl();
			// 根据成语子类型名称查询该类型所对应的id
			int classification = classifyIdiomServiceImpl.getIdByClassifyName(str);
			//创建用于保存成语的集合
			List<String> idiomList = null;
			// 创建IdiomServiceImpl对象
			IdiomServiceImpl idiomServiceImpl = new IdiomServiceImpl();
			// 根据成语所属类型查询成语(从数据库中获取该类型的成语)
			idiomList = idiomServiceImpl.listIdiomByClassification(classification);
			System.out.println("查询到类型【" + str + "】的id为： " + classification + " ，包含的成语有：" + idiomList.toString());
			// 转换成json串返回给客户端
			Gson gson = new Gson();
			String jsonStr = gson.toJson(idiomList);
			System.out.println("获取到的json串：" + jsonStr);
			OutputStream out = response.getOutputStream();
			out.write(jsonStr.getBytes("utf-8"));
			out.flush();
			// 关闭流
			out.close();
		}
	}

}
