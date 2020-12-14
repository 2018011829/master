package com.group.tiantian.parent.serviceServlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.entity.Parent;
import com.group.tiantian.parent.dao.ParentDao;
import com.group.tiantian.parent.service.ParentService;
import com.group.tiantian.util.ConfigUtil;
import com.group.tiantian.util.Page;

/**
 * Servlet implementation class AllParentsInfo
 */
@WebServlet("/AllParentsInfoServlet")
public class AllParentsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllParentsInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//将图片路径存入application作用域中
	    ServletContext servletContext=request.getServletContext();
	    servletContext.setAttribute("avatarPath", ConfigUtil.SETVER_AVATAR);
		//获取当前页的图片
	    System.out.println("来到了");
	    int pageNum=1,pageSize=10;
	    String page1=request.getParameter("page");
	    if(page1!=null && !page1.equals("")) {
	    	pageNum=Integer.parseInt(page1);
	    }
	    ParentService parentService=ParentService.getInstance();
	    Page<Parent> page = parentService.getPage(pageNum,pageSize);
	    servletContext.setAttribute("page", page);
	    response.sendRedirect("userInfo.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
