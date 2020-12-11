package com.group.tiantian.children.servlet;

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

import com.group.tiantian.children.service.ChildrenService;
import com.group.tiantian.entity.Child;

/**
 * Servlet implementation class QueryChildrenServlet
 */
@WebServlet("/QueryChildrenServlet")
public class QueryChildrenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryChildrenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String phone = request.getParameter("phone");
		System.out.println("ÊÖ»úºÅ" + phone);
		List<Child> children = new ArrayList<Child>();
		children = ChildrenService.getInstance().queryChildrenByPhone(phone);
		JSONArray jsonArray = new JSONArray();
		for(Child child : children) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", child.getId());
			jObject.put("name", child.getName());
			jObject.put("grade", child.getGrade());
			jObject.put("sex", child.getSex());
			jObject.put("parentPhone", child.getParentPhone());
			
			jsonArray.put(jObject);
		}
		
		JSONObject jChildren = new JSONObject();
		jChildren.put("children", jsonArray);
		String json = jChildren.toString();
		System.out.println("json´®" + json);
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
