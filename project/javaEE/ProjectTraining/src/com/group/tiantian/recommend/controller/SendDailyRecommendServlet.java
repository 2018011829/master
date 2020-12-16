package com.group.tiantian.recommend.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.tiantian.entity.Recommend;
import com.group.tiantian.recommend.service.RecommendServiceImpl;

/**
 * Servlet implementation class SendDailyRecommendServlet
 */
@WebServlet("/SendDailyRecommendServlet")
public class SendDailyRecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendDailyRecommendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---------------ÿ���Ƽ�-----------------");
		//�������ڱ���ÿ���Ƽ���List
		List<Recommend> recommendList = null;
		//����RecommendServiceImpl����
		RecommendServiceImpl recommendServiceImpl = new RecommendServiceImpl();
		// ��ȡÿ���Ƽ�
		recommendList = recommendServiceImpl.listRecommend();
		// ת����json�����ظ��ͻ���
		Gson gson = new Gson();
		String jsonStr = gson.toJson(recommendList);
		System.out.println("ÿ���Ƽ�json����" + jsonStr);
		OutputStream out = response.getOutputStream();
		out.write(jsonStr.getBytes("utf-8"));
		out.flush();
		// �ر���
		out.close();
	}

}
