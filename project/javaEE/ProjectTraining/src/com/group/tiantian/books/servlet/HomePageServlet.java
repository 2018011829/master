package com.group.tiantian.books.servlet;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.tiantian.books.service.HomePageService;
import com.group.tiantian.entity.Book;

/**
 * Servlet implementation class HomePageServlet
 */
@WebServlet("/BookHomePageServlet")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * ��ͻ��˷�����ҳҪչʾ������
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String grades=request.getParameter("info");
		HomePageService homePageService=HomePageService.getInstance();
		TreeMap<String,List<Book>> treeMap=homePageService.getHomePageInfo(grades);
		if(treeMap!=null) {
			Gson gson=new Gson();
			String gsonStr=gson.toJson(treeMap);
			System.out.println(gsonStr);
			response.getWriter().write(gsonStr);
		}else {
			System.out.println("δ�ҵ����ݣ�");
			response.getWriter().write("��Ҫ��ȡ������δ�ҵ���");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
