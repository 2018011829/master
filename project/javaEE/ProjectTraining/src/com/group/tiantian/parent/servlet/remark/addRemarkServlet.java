package com.group.tiantian.parent.servlet.remark;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.parent.service.ParentService;

/**
 * Servlet implementation class addRemarkServlet
 */
@WebServlet("/addRemarkServlet")
public class addRemarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addRemarkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromPhone=request.getParameter("fromPhone");
		String fromPhoneNickname=request.getParameter("fromPhoneNickname");
		String toPhone=request.getParameter("toPhone");
		String toPhoneNickname=request.getParameter("toPhoneNickname");
		ParentService.getInstance().addRemarks(fromPhone,fromPhoneNickname,toPhone,toPhoneNickname);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
