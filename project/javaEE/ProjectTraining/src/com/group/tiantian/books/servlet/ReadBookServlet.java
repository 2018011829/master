package com.group.tiantian.books.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.tiantian.entity.Content;

/**
 * Servlet implementation class ReadBookServlet
 */
@WebServlet("/ReadBookServlet")
public class ReadBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ�½���Ϣ
		String currentInfo=request.getParameter("currentContent");
		String nextInfo=request.getParameter("nextContent");
		if(currentInfo!=null && !currentInfo.equals("") && nextInfo!=null && !nextInfo.equals("")) {
			Content currentContent=new Gson().fromJson(currentInfo, Content.class);
			Content nextContent=new Gson().fromJson(nextInfo, Content.class);
			//�����½���Ϣ ��ȡ����½�����
			String text=getBookTextByContent(currentContent, nextContent);
			System.out.println(text);
			response.getWriter().write(text);
		}else {
			System.out.println("δ��ȡ���½���Ϣ��");
		}
		
	}
	
	/**
	 * �����½���Ϣ��ȡ����½���������
	 * @param content
	 * @return
	 */
	public String getBookTextByContent(Content currentContent,Content nextContent) {
		String text="";
		//��ȡ����ļ���
		String fileName=currentContent.getArticleName();
		//��ȡ�ļ��Ĵ洢·��
		String filePath=getServletContext().getRealPath("/books/")+fileName;
		System.out.println(filePath);
		//��ȡ��ʼ������
		int start=currentContent.getStart();
		//��ȡ����������
		int end=nextContent.getStart();
		//���ж�ȡ�ļ�
		StringBuffer buffer=new StringBuffer();
		String temp="";
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"utf-8"));
			int row=1;
			while((temp=br.readLine())!=null) {
				if(start<=row && row<end) {
					buffer.append(temp);
				}
				row++;
			}
			text=buffer.toString();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return text;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
