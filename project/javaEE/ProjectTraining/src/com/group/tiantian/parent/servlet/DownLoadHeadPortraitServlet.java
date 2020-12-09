package com.group.tiantian.parent.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownLoadHeadPortraitServlet
 */
@WebServlet("/DownLoadHeadPortraitServlet")
public class DownLoadHeadPortraitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadHeadPortraitServlet() {
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
		
		//��ȡȫ�ֱ���
		ServletContext application = this.getServletContext();
		// ���ؿͻ��˷�����ͼƬ
		InputStream is = request.getInputStream();
		// ��ȡ���������
		String path = getServletContext().getRealPath("/");
		System.out.println(path);
		String picture = "android" + System.currentTimeMillis() + ".png";//ͼƬ����
		application.setAttribute("headImgName",picture);
		System.out.println(picture);
		
		BufferedInputStream bufferedInputStream = new BufferedInputStream(is);

		OutputStream outputStream = new FileOutputStream(path + "avatar/" + picture);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream( outputStream );

		byte[] b=new byte[1024*1024];//����һ������ȡ1KB������

		int length = 0 ; //����ʵ�ʶ�ȡ���ֽ���
		while( (length = bufferedInputStream.read( b ) )!= -1 ){
			//length ����ʵ�ʶ�ȡ���ֽ���
			bufferedOutputStream.write(b, 0, length );
		}
        //������������д�뵽�ļ�
		bufferedOutputStream.flush();
		System.out.println("������ɣ�");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
