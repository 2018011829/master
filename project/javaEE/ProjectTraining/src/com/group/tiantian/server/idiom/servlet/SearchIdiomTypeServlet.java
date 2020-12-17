package com.group.tiantian.server.idiom.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.server.entity.Idiom;
import com.group.tiantian.server.entity.IdiomType;
import com.group.tiantian.server.entity.Page;
import com.group.tiantian.server.idiom.service.IdiomTypeService;

/**
 * Servlet implementation class SearchIdiomTypeServlet
 */
@WebServlet("/SearchIdiomTypeServlet")
public class SearchIdiomTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchIdiomTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("SearchBookServlet");
		String userName=request.getParameter("userName");
		//获取当前页
		String page=request.getParameter("page");
		String searchInfo=request.getParameter("searchInfo");
		//默认显示第一页
		int pageNum=1,pageSize=6;
		if(page!=null && !page.equals("")) {
			pageNum=Integer.parseInt(page);
		}
		if(userName!=null && !userName.equals("")) {
			//分页展示书籍
			IdiomTypeService idiomTypeService=IdiomTypeService.getInstance();
			System.out.println("搜索的信息："+searchInfo);
			Page<IdiomType> pageInfo=idiomTypeService.listByPage(pageNum, pageSize,searchInfo);
			request.setAttribute("page", pageInfo);
			request.setAttribute("searchInfo", searchInfo);
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("showSearchIdiomType.jsp").forward(request, response);
		}else {
			System.out.println("您还未登录！");
			response.sendRedirect("error.jsp");
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
