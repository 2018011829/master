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
		// 设置编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 获取请求数据
		String replyContentPerson = request.getParameter("likegivePerson");// 得到评论人的手机号
		int momentsId = Integer.parseInt(request.getParameter("momentsId"));// 得到被评论说说的id
		int position = Integer.parseInt(request.getParameter("position"));//得到被回复的评论id
		String replyContent = request.getParameter("replyContent");// 评论内容
		System.out.println("手机号" + replyContentPerson + ":" + momentsId + ":" + replyContent);

		ReplyContentService replyContentService = ReplyContentService.getInstance();// 创建添加评论service对象
		PersonalInfo personalInfo = replyContentService.getPersonalInfo(replyContentPerson);// 根据评论人的手机号得到评论人的个人信息
		String likeGiveName = personalInfo.getName();// 得到评论人的昵称
		String personHead = personalInfo.getPhotoUrl();// 得到评论人头像
		System.out.println(likeGiveName);
		replyContentService.insertreplyContent(momentsId,position,replyContent, replyContentPerson, likeGiveName, personHead);// 将被点赞说说id，点赞人昵称和点赞人手机号存入数据库

		response.getWriter().write("收到数据");
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
