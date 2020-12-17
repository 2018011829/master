package com.group.tiantian.server.idiom.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.entity.Idiom;
import com.group.tiantian.server.idiom.service.IdiomTypeService;

/**
 * Servlet implementation class AddIdiomServlet
 */
@WebServlet("/AddIdiomServlet")
public class AddIdiomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddIdiomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idiom=request.getParameter("idiom");
		int idiomParentType=Integer.parseInt(request.getParameter("idiomParentType"));
		int idiomType=Integer.parseInt(request.getParameter("idiomType"));
		int type=0;
		if(idiomType==0) {
			type=idiomParentType;
		}else {
			type=idiomType;
		}
		boolean isSuccess=IdiomTypeService.getInstance().addIdiom(idiom,type);
		if(isSuccess) {
			response.sendRedirect("addIdiomSuccess.html");
		}else {
			
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
