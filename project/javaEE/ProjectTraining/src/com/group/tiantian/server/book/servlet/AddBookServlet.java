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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 获取信息
		String userName = null;
		FileItem itemImg = null;
		FileItem itemFile = null;
		Book book = new Book();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> listItem = upload.parseRequest(request);
			for (FileItem item : listItem) {
				if (item.isFormField()) {// 判断是否为文件
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
				} else {// 是文件 进行文件的读写
					if (item.getFieldName().equals("bookImg")) {
						String name = "" + System.currentTimeMillis();
						if (item.getName() != null && !item.getName().equals("")) {
							System.out.println("添加图片不为空");
							String ext = item.getName().substring(item.getName().lastIndexOf("."),
									item.getName().length());
							book.setImg(name + ext);
							itemImg = item;
						}
//						System.out.println(path + "/" + name + ext);
					}
					if (item.getFieldName().equals("book")) {
						String str = "" + System.currentTimeMillis();
						String name = item.getName();
						if (name != null && !name.equals("")) {
							System.out.println("图书文件不为空！");
							String ext = name.substring(name.lastIndexOf("."), item.getName().length());
							book.setContent(str + ext);
							itemFile = item;
//						System.out.println(path + "/" + name );
						}
					}

				}
			}
			System.out.println("添加的图书信息：" + book.toString());
			if (userName != null && !userName.equals("")) {
				// 将数据添加到数据库
				BookTypeService bookTypeService = BookTypeService.getInstance();
				// 查找书籍是否已经存在
				boolean a = bookTypeService.searchBook(book.getName());
				if (!a) {
					if (itemImg != null) {
						if (itemFile != null) {
							boolean b = bookTypeService.addBook(book);
							if (b) {
								// 将资源保存到站点
								String path = this.getServletContext().getRealPath("");
								if (itemImg != null) {
									itemImg.write(new File(path + "/bookImgs/" + book.getImg()));
									if (itemFile != null) {
										itemFile.write(new File(path + "/books/" + book.getContent()));
									}
								}
								request.setAttribute("userName", userName);
								request.getRequestDispatcher("GetBookInfoServlet?userName=" + userName).forward(request,
										response);
							} else {
								request.setAttribute("userName", userName);
								request.setAttribute("newBook", book);
								request.setAttribute("errorInfo", "添加失败！");
								request.getRequestDispatcher("addBook.jsp").forward(request, response);
							}
						} else {
							request.setAttribute("userName", userName);
							request.setAttribute("newBook", book);
							request.setAttribute("errorInfo", "书籍已存在！");
							request.getRequestDispatcher("addBook.jsp").forward(request, response);
						}
					} else {
						request.setAttribute("userName", userName);
						request.setAttribute("errorInfo", "电子书籍不能为空！");
						request.getRequestDispatcher("addBook.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("userName", userName);
					request.setAttribute("errorInfo", "书的照片不能为空！");
					request.getRequestDispatcher("addBook.jsp").forward(request, response);
				}

			} else {
				System.out.println("您还未登录！");
				response.sendRedirect("error.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
