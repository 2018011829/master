package com.group.tiantian.server.book.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.group.tiantian.entity.Book;
import com.group.tiantian.server.book.service.BookTypeService;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// ��ȡ��Ϣ
		String userName = null;
		FileItem itemImg=null;
		FileItem itemFile=null;
		Book book = new Book();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> listItem = upload.parseRequest(request);
			for (FileItem item : listItem) {
				if (item.isFormField()) {// �ж��Ƿ�Ϊ�ļ�
					if (item.getFieldName().equals("bookAuthor")) {
						book.setAuthor(item.getString("utf-8"));
					}
					if (item.getFieldName().equals("bookType")) {
						System.out.println(item.getString("utf-8"));
						book.setType(item.getString("utf-8"));
					}
					if (item.getFieldName().equals("bookName")) {
						book.setName(item.getString("utf-8"));
					}
					if (item.getFieldName().equals("bookGrades")) {
						book.setGrades(item.getString("utf-8"));
					}
					if (item.getFieldName().equals("bookIntroduce")) {
						book.setIntroduce(item.getString("utf-8"));
					}
					if (item.getFieldName().equals("userName")) {
						userName = item.getString("utf-8");
//						System.out.println(user);
					}
				} else {// ���ļ� �����ļ��Ķ�д
					if (item.getFieldName().equals("bookImg")) {
						String name = "" + System.currentTimeMillis();
						String ext = item.getName().substring(item.getName().lastIndexOf("."), item.getName().length());
						book.setImg(name + ext);
						itemImg=item;
//						System.out.println(path + "/" + name + ext);
					}
					if (item.getFieldName().equals("book")) {
						String name = item.getName();
						book.setContent(name);
						itemFile=item;
//						System.out.println(path + "/" + name );
					}
					
				}
			}
			System.out.println("��ӵ�ͼ����Ϣ��"+book.toString());
			if(userName!=null && !userName.equals("")) {
				//��������ӵ����ݿ�
				BookTypeService bookTypeService=BookTypeService.getInstance();
				//�����鼮�Ƿ��Ѿ�����
				boolean a=bookTypeService.searchBook(book.getName());
				if(!a) {
					boolean b=bookTypeService.addBook(book);
					if(b) {
						String path = this.getServletContext().getRealPath("");
						itemImg.write(new File(path + "/bookImgs/" + book.getImg()));
						itemFile.write(new File(path + "/books/" + book.getContent() ));
						request.setAttribute("userName", userName);
						request.getRequestDispatcher("GetBookInfoServlet").forward(request, response);
					}else {
						request.setAttribute("userName", userName);
						request.setAttribute("newBook", book);
						request.setAttribute("errorInfo", "���ʧ�ܣ�");
						request.getRequestDispatcher("addBook.jsp").forward(request, response);
					}
				}else {
					request.setAttribute("userName", userName);
					request.setAttribute("newBook", book);
					request.setAttribute("errorInfo", "�鼮�Ѵ��ڣ�");
					request.getRequestDispatcher("addBook.jsp").forward(request, response);
				}
				
			}else {
				System.out.println("����δ��¼��");
				response.sendRedirect("error.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
