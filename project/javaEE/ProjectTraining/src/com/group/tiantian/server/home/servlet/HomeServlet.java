package com.group.tiantian.server.home.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.server.book.service.BookTypeService;
import com.group.tiantian.server.entity.IdiomType;
import com.group.tiantian.server.home.service.ChildService;
import com.group.tiantian.server.home.service.ParentService;
import com.group.tiantian.server.idiom.service.IdiomTypeService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 站点数据统计
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("GetBookTypesServlet");
		String userName=request.getParameter("userName");
		if(userName!=null && !userName.equals("")) {
			//获取用户使用信息
			IdiomTypeService idiomTypeService=IdiomTypeService.getInstance();
			BookTypeService bookTypeService=BookTypeService.getInstance();
			ParentService parentService=ParentService.getInstance();
			ChildService childService=ChildService.getInstance();
			//获取成语收藏数
			int saveIdiomCount=idiomTypeService.getSaveIdiomCount();
			//获取书籍收藏数
			int saveBookCount=bookTypeService.getSaveBookCount();
			//获取书籍加入书架数
			int bookShelfCount=bookTypeService.getBookshelfCount();
			//获取用户注册数
			int parentCount=parentService.getCount();
			//获取使用的孩子数
			int childCount=childService.getCount();
			List<Integer> userData=new ArrayList<Integer>();
			userData.add(saveIdiomCount);
			userData.add(saveBookCount);
			userData.add(bookShelfCount);
			userData.add(parentCount);
			userData.add(childCount);
			//获取站点数据信息
			//成语类型数
			int idiomTypeCount=idiomTypeService.getCount();
			this.getServletContext().setAttribute("pageIdiomTypes", idiomTypeService.getAllTypes());
			List<String> list=new ArrayList<String>();
			for(IdiomType idiomType:idiomTypeService.getAllTypes()) {
				if(!list.contains(idiomType.getParentType())) {
					list.add(idiomType.getParentType());
				}
			}
			this.getServletContext().setAttribute("idiomParentsTypes", list);
			//成语总数
			int idiomCount=idiomTypeService.getIdiomCount();
			//书籍类型数
			int bookTypeCount=bookTypeService.getBookTypeCount();
			this.getServletContext().setAttribute("bookTypes", bookTypeService.getAllTypes());
			//书籍总数
			int bookCount=bookTypeService.getBookCount();
			List<Integer> localData=new ArrayList<Integer>();
			localData.add(idiomTypeCount);
			localData.add(idiomCount);
			localData.add(bookTypeCount);
			localData.add(bookCount);
			request.setAttribute("userData", userData);
			request.setAttribute("localData", localData);
			System.out.println("userData:"+userData.toString());
			request.getRequestDispatcher("home.jsp").forward(request, response);
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
