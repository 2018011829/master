package com.group.tiantian.moments.servlet.comments;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.moments.service.CommentsService;
import com.group.tiantian.moments.service.ReplyContentService;

/**
 * Servlet implementation class ReplyContentAddAdd
 */
@WebServlet("/ReplyContentAddAdd")
public class ReplyContentAddAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReplyContentAddAdd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ��뷽ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// ��ȡ��������
		String replyContentPerson = request.getParameter("likegivePerson");// �õ������˵��ֻ���
		int momentsId = Integer.parseInt(request.getParameter("momentsId"));// �õ�������˵˵��id
		int position = Integer.parseInt(request.getParameter("position"));//�õ����ظ�������id
		String replyContent = request.getParameter("replyContent");// ��������
		System.out.println("�ֻ���" + replyContentPerson + ":" + momentsId + ":" + replyContent);

		ReplyContentService replyContentService = ReplyContentService.getInstance();// �����������service����
		PersonalInfo personalInfo = replyContentService.getPersonalInfo(replyContentPerson);// ���������˵��ֻ��ŵõ������˵ĸ�����Ϣ
		String likeGiveName = personalInfo.getName();// �õ������˵��ǳ�
		String personHead = personalInfo.getPhotoUrl();// �õ�������ͷ��
		System.out.println(likeGiveName);
		replyContentService.insertreplyContent(momentsId,position,replyContent, replyContentPerson, likeGiveName, personHead);// ��������˵˵id���������ǳƺ͵������ֻ��Ŵ������ݿ�

		response.getWriter().write("�յ�����");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
