package com.group.tiantian.parent.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.entity.Parent;
import com.group.tiantian.entity.ParentMessage;
import com.group.tiantian.parent.service.ParentService;

/**
 * Servlet implementation class ParentMessageServlet
 */
@WebServlet("/ParentMessageServlet")
public class ParentMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParentMessageServlet() {
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
		
		//�õ��ֻ�����
		String phone = request.getParameter("phone");
		System.out.println("�ֻ���" + phone);
		
		//�����ֻ������ѯָ���ҳ�
		ParentService parentService = ParentService.getInstance();
		ParentMessage parentMessage = parentService.selectParentByPhone(phone);
		
		String path = this.getServletContext().getRealPath("/avatar");
		
		//ƴ��JSON��ʽ�ַ���
		String json = "{'phone':'"+parentMessage.getPhone()+"','sex':'"+parentMessage.getSex()+"','nickName':'"+parentMessage.getNickName()+"','headphoto':'"+parentMessage.getHeadPortrait()+"'}";
		System.out.println("��Ϣ��" + json);
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
