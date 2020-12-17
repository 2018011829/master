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
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookServlet() {
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
		String beforeBookName=null;
		String beforeBookImg=null;
		int page=1;
		Book book = new Book();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> listItem = upload.parseRequest(request);
			for (FileItem item : listItem) {
				if (item.isFormField()) {// �ж��Ƿ�Ϊ�ļ�
					if (item.getFieldName().equals("id")) {
						String idStr=item.getString("utf-8");
						if(idStr!=null && !idStr.equals("")) {
							int id=Integer.parseInt(idStr);
							book.setId(id);
						}
					}
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
					if (item.getFieldName().equals("beforeImg")) {
						beforeBookImg=item.getString("utf-8");
					}
					if (item.getFieldName().equals("beforeContent")) {
						beforeBookName=item.getString("utf-8");
					}
					if (item.getFieldName().equals("page")) {
						String pageStr=item.getString("utf-8");
						if(pageStr!=null && !pageStr.equals("")) {
							page=Integer.parseInt(pageStr);
						}
					}
				} else {// ���ļ� �����ļ��Ķ�д
					if (item.getFieldName().equals("bookImg")) {
						String name = "" + System.currentTimeMillis();
						if(item.getName()!=null && !item.getName().equals("")) {
							System.out.println("���ͼƬ��Ϊ��");
							String ext = item.getName().substring(item.getName().lastIndexOf("."), item.getName().length());
							book.setImg(name + ext);
							itemImg=item;
						}else {
							book.setImg(beforeBookImg);
						}
//						System.out.println(path + "/" + name + ext);
					}
					if (item.getFieldName().equals("book")) {
						String str = "" + System.currentTimeMillis();
						String name = item.getName();
						if(name!=null && !name.equals("")) {
							System.out.println("ͼ���ļ���Ϊ�գ�");
							String ext = name.substring(name.lastIndexOf("."), item.getName().length());
							book.setContent(str+ext);
							itemFile=item;
//						System.out.println(path + "/" + name );
						}else {
							book.setImg(beforeBookName);
						}
					}
				}
			}
			System.out.println("�޸ĵ�ͼ����Ϣ��"+book.toString());
			if(userName!=null && !userName.equals("")) {
				//�������� �޸����ݿ�
				BookTypeService bookTypeService=BookTypeService.getInstance();
				//�����鼮�Ƿ��Ѿ�����
				boolean a=bookTypeService.searchBook(book.getName());
				if(a) {
					boolean b=bookTypeService.updateBook(book);
					if(b) {
						//����Դ���浽վ��
						String path = this.getServletContext().getRealPath("");
						if(itemImg!=null) {
							itemImg.write(new File(path + "/bookImgs/" + book.getImg()));
						}
						if(itemFile!=null) {
							itemFile.write(new File(path + "/books/" + book.getContent() ));
						}
						//��ת��չʾͼ�����
						request.setAttribute("page", page);
						request.setAttribute("userName", userName);
						request.getRequestDispatcher("GetBookInfoServlet?userName="+userName+"&page="+page).forward(request, response);
					}else {
						request.setAttribute("userName", userName);
						request.setAttribute("newBook", book);
						request.setAttribute("errorInfo", "�޸�ʧ�ܣ�");
						request.getRequestDispatcher("addBook.jsp").forward(request, response);
					}
				}else {
					request.setAttribute("userName", userName);
					request.setAttribute("newBook", book);
					request.setAttribute("errorInfo", "�鼮�����ڣ�");
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
