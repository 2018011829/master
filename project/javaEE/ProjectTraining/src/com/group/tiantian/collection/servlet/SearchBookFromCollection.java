package com.group.tiantian.collection.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.group.tiantian.books.service.MoreBooksService;
import com.group.tiantian.collection.service.CollectionService;
import com.group.tiantian.entity.Book;

/**
 * Servlet implementation class SearchBookFromCollection
 */
@WebServlet("/SearchBookFromCollection")
public class SearchBookFromCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookFromCollection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> bookName = new ArrayList<String>();
		Book book = new Book();
		String phone = request.getParameter("phone");
		String cname = request.getParameter("cname");
		String type = request.getParameter("type");
		System.out.println("phone:"+phone);
		System.out.println("cname:"+cname);
		System.out.println("type:"+type);
		
		bookName = CollectionService.getInstance().searchBookFromCollection(phone, cname, type);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < bookName.size(); i++) {
			JSONObject jObject = new JSONObject();
			book = MoreBooksService.getInstance().getBooksByName(bookName.get(i));
			jObject.put("name", book.getName());
			jObject.put("author", book.getAuthor());
			jObject.put("type", book.getType());
			jObject.put("introduce", book.getIntroduce());
			jObject.put("img", book.getImg());
			jObject.put("content",book.getContent());
			
			jsonArray.put(jObject);
		}
		
		JSONObject jBooks = new JSONObject();
		jBooks.put("books", jsonArray);
		String json = jBooks.toString();
		System.out.println("收藏图书的信息串：" + json);
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
