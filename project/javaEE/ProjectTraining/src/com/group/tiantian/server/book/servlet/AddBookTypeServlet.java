package com.group.tiantian.server.book.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.server.book.service.BookTypeService;

/**
 * Servlet implementation class AddBookTypeServlet
 */
@WebServlet("/AddBookTypeServlet")
public class AddBookTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("AddBookTypeServlet");
		String userName=request.getParameter("userName");
		String newBookType=request.getParameter("newBookType");
		if(userName!=null && !userName.equals("")) {
			if(newBookType!=null && !newBookType.equals("")) {
				//��ͼ��������Ϣ��ӵ����ݿ�
				boolean a=BookTypeService.getInstance().searchBookType(newBookType);
				if(!a) {
					boolean b=BookTypeService.getInstance().addBookType(newBookType);
					if(b) {
						this.getServletContext().setAttribute("bookTypes", BookTypeService.getInstance().getAllTypes());
						request.setAttribute("userName", userName);
						request.getRequestDispatcher("GetBookTypesServlet?userName="+userName).forward(request, response);
					}else {
						request.setAttribute("userName", userName);
						request.setAttribute("errorInfo", "���ʧ�ܣ�");
						request.getRequestDispatcher("addBookType.jsp").forward(request, response);
					}
				}else {
					request.setAttribute("userName", userName);
					request.setAttribute("errorInfo", "�������Ѵ��ڣ�");
					request.getRequestDispatcher("addBookType.jsp").forward(request, response);
				}
			}else {
				request.setAttribute("errorInfo", "����������Ϊ�գ�");
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("addBookType.jsp").forward(request, response);
			}
		}else {
			System.out.println("����δ��¼��");
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
