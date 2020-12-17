package com.group.tiantian.server.idiom.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.server.entity.IdiomType;
import com.group.tiantian.server.idiom.service.IdiomTypeService;

/**
 * Servlet implementation class AddIdiomTypeServlet
 */
@WebServlet("/AddIdiomTypeServlet")
public class AddIdiomTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddIdiomTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String userName=request.getParameter("userName");
		String idiomParentType = request.getParameter("idiomParentType");//父类型
		String idiomChildType = request.getParameter("idiomChildType");//子类型
		if(userName!=null && !userName.equals("")) {
			request.setAttribute("userName", userName);
			IdiomTypeService idionTypeService = IdiomTypeService.getInstance();
			if(idiomParentType.equals("")) {//父类型为空
				boolean temp = false;
				if(idionTypeService.idiomIfExist(idiomChildType)) {//判断子类型存不存在于表中
					System.out.println("该类型已存在");
					request.setAttribute("errorInfo", "该类型已存在");
					request.getRequestDispatcher("addIdiomType.jsp").forward(request, response);
				}else {
					temp = idionTypeService.addIdiomParentType(idiomChildType);//如果不存在，将子类型存入表中，父类型为0
				}
				if(temp) {//父类型添加成功 更新站点数据
					IdiomTypeService idiomTypeService=IdiomTypeService.getInstance();
					this.getServletContext().setAttribute("idiomTypes", idiomTypeService.getAllTypes());
					request.getRequestDispatcher("GetIdiomTypesServlet?userName="+userName).forward(request, response);
				}else {
					request.setAttribute("errorInfo", "存入失败");
					request.getRequestDispatcher("addIdiomType.jsp").forward(request, response);
				}
			}else {//父类型不为空
				boolean temp = false;
				int parentId = idionTypeService.getIdiomId(idiomParentType);//查询父类型的id
				if(idionTypeService.idiomIfExist(idiomChildType)) {
					request.setAttribute("errorInfo", "该类型已存在");
					request.getRequestDispatcher("addIdiomType.jsp").forward(request, response);
				}else {
					temp = idionTypeService.addIdiomChildType(idiomChildType, parentId);//将子类型的名字和父类型的id存入数据库中
				}
				if(temp) {
					request.getRequestDispatcher("GetIdiomTypesServlet?userName="+userName).forward(request, response);
				}else {
					request.setAttribute("errorInfo", "存入失败");
					request.getRequestDispatcher("addIdiomType.jsp").forward(request, response);
				}
			}
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
