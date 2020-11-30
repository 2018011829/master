package com.group.tiantian.books.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.tiantian.books.service.MoreBooksService;
import com.group.tiantian.entity.Book;

/**
 * Servlet implementation class MoreBooksServlet
 */
@WebServlet("/MoreBooksServlet")
public class MoreBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoreBooksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * ���ؿͻ�������Ҫ��ĳ�����͵�����ͼ��
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ�������
		String type=request.getParameter("type");
		if(type!=null) {
			//��ȡ�����͵�����ͼ��
			MoreBooksService moreBooksService=MoreBooksService.getInstance();
			List<Book> list=moreBooksService.getAllBookByType(type);
			if(list!=null) {
				Gson gson=new Gson();
				String result=gson.toJson(list);
				System.out.println(result);
				response.getWriter().write(result);
			}else {
				System.out.println("δ�ҵ������͵��鼮��");
				response.getWriter().write("δ�ҵ������͵��鼮��");
			}
		}else {
			System.out.println("δ�յ��ͻ��˵�������Ϣ��");
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
