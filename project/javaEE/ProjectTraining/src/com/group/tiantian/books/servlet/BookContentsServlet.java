package com.group.tiantian.books.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.tiantian.books.service.BookContentsService;
import com.group.tiantian.entity.Content;

/**
 * Servlet implementation class BookContentsServlet
 */
@WebServlet("/BookContentsServlet")
public class BookContentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookContentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * ��ͻ��˷���ĳ�����Ŀ¼�б�
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//�������
		String articleName=request.getParameter("content");
		System.out.println(articleName);
		//��ȡĿ¼�б�
		BookContentsService bookContentsService=BookContentsService.getInstance();
		List<Content> contents=bookContentsService.getBookContents(articleName);
		if(contents!=null) {
			Gson gson=new Gson();
			String gsonStr=gson.toJson(contents);
			System.out.println(gsonStr);
			response.getWriter().write(gsonStr);
		}else {
			System.out.println(articleName+":�½��б��ȡʧ�ܣ�");
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
