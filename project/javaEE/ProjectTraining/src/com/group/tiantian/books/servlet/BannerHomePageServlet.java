package com.group.tiantian.books.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class BannerHomePageServlet
 */
@WebServlet("/BannerHomePageServlet")
public class BannerHomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BannerHomePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 向客户端返回图书主页要显示的轮播图
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取图片集合
		List<String> imgs=new ArrayList<>();
		imgs.add("img1.jpg");
		imgs.add("img2.jpg");
		imgs.add("img3.jpg");
		imgs.add("img4.jpg");
		imgs.add("img5.jpg");
		String imgsStr=new Gson().toJson(imgs);
		response.getWriter().write(imgsStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
