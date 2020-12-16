package com.group.tiantian.moments.servlet.moments;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.moments.service.MomentsService;

/**
 * Servlet implementation class DeleteMoment
 */
@WebServlet("/DeleteMoment")
public class DeleteMoment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteMoment() {
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
		// request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 获取请求数据
		int momentsId =  Integer.parseInt(request.getParameter("momentsId"));// 点赞人的手机号
		
		MomentsService addMomentsService = MomentsService.getInstance();
		boolean b1 = addMomentsService.deleteMoment(momentsId);
		
		if(b1) {
			// 返回响应
			response.getWriter().write("图片上传成功");
		}else {
			// 返回响应
			response.getWriter().write("图片上传失败");
		}
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
