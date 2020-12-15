package com.group.tiantian.idiom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.tiantian.idiom.service.IdiomServiceImpl;

/**
 * Servlet implementation class SearchSaveIdiomByInfoServlet
 */
@WebServlet("/SearchSaveIdiomByInfoServlet")
public class SearchSaveIdiomByInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchSaveIdiomByInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String phone = request.getParameter("phone");
		String cname = request.getParameter("cname");
		System.out.println("手机号："+phone);
		System.out.println("孩子名："+phone);
		
		List<String> idioms = new IdiomServiceImpl().findSaveIdiomByInfo(phone, cname);
		String json = new Gson().toJson(idioms);
		System.out.println("收藏的成语集合："+json);
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
