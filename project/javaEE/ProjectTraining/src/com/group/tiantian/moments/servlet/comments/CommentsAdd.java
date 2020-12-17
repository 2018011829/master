package com.group.tiantian.moments.servlet.comments;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.moments.service.CommentsService;
import com.group.tiantian.moments.service.LikeGiveService;

/**
 * Servlet implementation class CommentsAdd
 */
@WebServlet("/CommentsAdd")
public class CommentsAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentsAdd() {
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
		String commentPerson = request.getParameter("likegivePerson");// �õ������˵��ֻ���
		int momentsId = Integer.parseInt(request.getParameter("momentsId"));// �õ�������˵˵��id
		String commentContent = request.getParameter("commentContent");//��������
		String time = request.getParameter("time");//����ʱ��
		System.out.println("�ֻ���" + commentPerson + ":" + momentsId+":"+commentContent+":"+time);

		CommentsService commentsService = CommentsService.getInstance();// �����������service����
		PersonalInfo personalInfo = commentsService.getPersonalInfo(commentPerson);// ���������˵��ֻ��ŵõ������˵ĸ�����Ϣ
		String likeGiveName = personalInfo.getName();// �õ������˵��ǳ�
		String personHead = personalInfo.getPhotoUrl();//�õ�������ͷ��
		System.out.println(likeGiveName);
		commentsService.insertMoments(momentsId,commentContent,commentPerson,likeGiveName,personHead,time);// ��������˵˵id���������ǳƺ͵������ֻ��Ŵ������ݿ�

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
