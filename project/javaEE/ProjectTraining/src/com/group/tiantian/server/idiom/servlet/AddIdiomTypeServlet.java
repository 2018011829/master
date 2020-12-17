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
		String idiomParentType = request.getParameter("idiomParentType");//������
		String idiomChildType = request.getParameter("idiomChildType");//������
		if(userName!=null && !userName.equals("")) {
			request.setAttribute("userName", userName);
			IdiomTypeService idionTypeService = IdiomTypeService.getInstance();
			if(idiomParentType.equals("")) {//������Ϊ��
				boolean temp = false;
				if(idionTypeService.idiomIfExist(idiomChildType)) {//�ж������ʹ治�����ڱ���
					System.out.println("�������Ѵ���");
					request.setAttribute("errorInfo", "�������Ѵ���");
					request.getRequestDispatcher("addIdiomType.jsp").forward(request, response);
				}else {
					temp = idionTypeService.addIdiomParentType(idiomChildType);//��������ڣ��������ʹ�����У�������Ϊ0
				}
				if(temp) {//��������ӳɹ� ����վ������
					IdiomTypeService idiomTypeService=IdiomTypeService.getInstance();
					this.getServletContext().setAttribute("idiomTypes", idiomTypeService.getAllTypes());
					request.getRequestDispatcher("GetIdiomTypesServlet?userName="+userName).forward(request, response);
				}else {
					request.setAttribute("errorInfo", "����ʧ��");
					request.getRequestDispatcher("addIdiomType.jsp").forward(request, response);
				}
			}else {//�����Ͳ�Ϊ��
				boolean temp = false;
				int parentId = idionTypeService.getIdiomId(idiomParentType);//��ѯ�����͵�id
				if(idionTypeService.idiomIfExist(idiomChildType)) {
					request.setAttribute("errorInfo", "�������Ѵ���");
					request.getRequestDispatcher("addIdiomType.jsp").forward(request, response);
				}else {
					temp = idionTypeService.addIdiomChildType(idiomChildType, parentId);//�������͵����ֺ͸����͵�id�������ݿ���
				}
				if(temp) {
					request.getRequestDispatcher("GetIdiomTypesServlet?userName="+userName).forward(request, response);
				}else {
					request.setAttribute("errorInfo", "����ʧ��");
					request.getRequestDispatcher("addIdiomType.jsp").forward(request, response);
				}
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
