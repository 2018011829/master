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
		// 设置编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 下载客户端发来的图片
		InputStream is = request.getInputStream();
		// 获取本地输出流
		String path = getServletContext().getRealPath("/");
		System.out.println(path);
		String pictureName = "android" + System.currentTimeMillis() + ".jpg";// 图片命名
		System.out.println(pictureName);
		OutputStream os = new FileOutputStream(path + "imgs/" + pictureName);
		// 循环读写
		int b = -1;
		while ((b = is.read()) != -1) {
			os.write(b);
		}
		is.close();
		os.close();
		System.out.println("下载完成！");
		
		ServletContext application = this.getServletContext();
		String time = (String) application.getAttribute("time");//获取当前发说说的时间
		String personalPhone = (String) application.getAttribute("personalPhone");//获取当前发说说的手机号
		System.out.println("图片time："+time);
		System.out.println("图片personalPhone："+personalPhone);
		
		AddMomentsService addMomentsService =AddMomentsService.getInstance();
		int momentsId = addMomentsService.serchMomentsId(personalPhone, time);//得到手机号和时间对应的说说的id
		addMomentsService.insertPictureUrl(pictureName, momentsId, time,personalPhone);//将图片名称，说说id，手机号，时间存入数据库
		
		
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
