package com.group.tiantian.parent.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.parent.service.ParentService;

/**
 * Servlet implementation class DownLoadMessageServlet
 */
@WebServlet("/DownLoadMessageServlet")
public class DownLoadMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					// ���ñ��뷽ʽ
					request.setCharacterEncoding("utf-8");
					response.setContentType("text/html;charset=utf-8");
					
					String headName = null;
					//�ж�ͷ���Ƿ������޸�
					ServletContext application = this.getServletContext();
					if(application.getAttribute("headImgName")!=null&&!application.getAttribute("headImgName").equals("")) {
						headName = application.getAttribute("headImgName").toString();
						application.removeAttribute("headImgName");
						System.out.println("����" + application.getAttribute("headImaName"));
					}
					//�õ��ֻ�����
					String phone = request.getParameter("phone");
					String nickName = request.getParameter("nickName");
					if(headName == null) {
						headName = request.getParameter("headName");
						String [] nameStrings = headName.split("/");
						headName = nameStrings[nameStrings.length-1];
					}
				
					System.out.println("�ֻ���" + phone);
					System.out.println("�ǳ�" + nickName);
					System.out.println("ͷ��" + headName);
					
					ParentService parentService = ParentService.getInstance();
					parentService.updateParentMessage(phone,nickName, headName);
					
					response.getWriter().write("success");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
