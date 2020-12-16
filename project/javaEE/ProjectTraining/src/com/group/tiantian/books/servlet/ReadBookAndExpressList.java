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

import com.group.tiantian.books.service.BookContentsService;


/**
 * Servlet implementation class ReadBookAndExpressList
 */
@WebServlet("/ReadBookAndExpressList")
public class ReadBookAndExpressList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadBookAndExpressList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 解析某本书中所包含的目录并将目录存入数据库
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//书的名称
		String bookName="意大利童话.txt";
		//拼接读取路径
		String path=getServletContext().getRealPath("/books/")+bookName;
		System.out.println(path);
		//按行读取文件
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
		//要读取的目录名规则
		String testString = "";
		int i=1;
		double j=1;
		String part="";
		while((testString=br.readLine())!=null) {
//			System.out.println(testString);
//			if(testString.trim().matches("([第].{1,5}[节])")) {
			if(testString.trim().matches("([第].{1,5}[章])(\\s{0,})(.{1,})")) {
//			if(testString.trim().matches("^[0123456789]{1,2}(.{1,})")) {
//			if(testString.trim().matches("([第].{1,5}[卷].{1})(\\s{1,})(.{1,})")) {
//			if(testString.trim().matches("([第](\\s{1}).{1,5}(\\s{1})[章])")) {
//			if(testString.trim().matches("^[1234567890]{1,5}")) {
//			if(testString.trim().matches("^[@].{2,}")) {
//			if(testString.trim().matches("^[1234567890]{1,2}")) {
				part=testString;
				System.out.println(part);
				boolean b=BookContentsService.getInstance().insertContentData(j, i, bookName, part);
				System.out.println(part+":"+b);
				if(b) {
					j++;
				}
			}
//			if(testString.trim().matches("\\s{0,}([第].{1,5}[章节卷回])(\\s{0,})(.{0,})")) {
//				System.out.println(testString);
//			}
//			if(testString.trim().matches("^[一二三四五六七八九十]{1,3}")) {
//				System.out.println(testString);
//				boolean b=BookContentsService.getInstance().insertContentData(j,i, bookName, part+"  第"+testString+"章");
//				System.out.println(part+"  第"+testString+"章");
//				if(b) {
//					j++;
//				}
//			}
			i++;
		}
		br.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
