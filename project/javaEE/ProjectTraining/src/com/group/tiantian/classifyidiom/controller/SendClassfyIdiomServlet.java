package com.group.tiantian.classifyidiom.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.tiantian.classifyidiom.service.ClassifyIdiomServiceImpl;
import com.group.tiantian.entity.ClassifyIdiom;

/**
 * 2020-11-25
 * 
 * @author lrf
 */
@WebServlet("/SendClassfyIdiomServlet")
public class SendClassfyIdiomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendClassfyIdiomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �������ڱ����������LinkedHashMap
		LinkedHashMap<ClassifyIdiom, List<ClassifyIdiom>> idiomMap = null;
		// ����ClassifyIdiomServiceImpl����
		ClassifyIdiomServiceImpl serviceImpl = new ClassifyIdiomServiceImpl();
		// �����ݿ��л�ȡ���������Ϣ
		idiomMap = serviceImpl.getAllClassifyIdiom();
		System.out.println("��ȡ���ĳ������ͣ�" + idiomMap.toString());
		String str = idiomMap.toString();
		OutputStream out = response.getOutputStream();
		out.write(str.getBytes("utf-8"));
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
