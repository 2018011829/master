package com.group.tiantian.server.book.service;

import java.util.List;

import com.group.tiantian.books.dao.TypeDao;
import com.group.tiantian.entity.Book;
import com.group.tiantian.entity.BookType;
import com.group.tiantian.server.book.dao.BookDao;
import com.group.tiantian.server.entity.Page;

public class BookTypeService {

	private static BookTypeService bookTypeService;
	private static TypeDao typeDao;
	
	/**
	 * 得到一个BookTypeService实例
	 * 
	 * @return
	 */
	public static BookTypeService getInstance() {
		if (null == bookTypeService) {
			bookTypeService = new BookTypeService();
		}
		if (null == typeDao) {
			typeDao = TypeDao.getInstance();
		}
		return bookTypeService;
	}
	
	/**
	 * 根据id查找书的信息
	 * @param 
	 * @return 
	 */
	public Book searchBookInfo(int id) {
		Book book=BookDao.searchBookInfo(id);
		return book;
	}
	
	/**
	 * 查找书名是否已经存在
	 * @param name
	 * @return 存在返回true
	 */
	public boolean searchBook(String name) {
		boolean b=BookDao.searchBook(name);
		return b;
	}
	
	/**
	 * 将书的信息添加到数据库
	 * @param book
	 * @return
	 */
	public boolean addBook(Book book) {
		boolean b=BookDao.addBook(book);
		
		return b;
	}
	
	/**
	 * 从数据库中查找所有图书的类型
	 * @return 返回所有图书类型的集合
	 */
	public List<BookType> getAllTypes(){
		List<BookType> list=typeDao.getAllTypes();
		
		return list;
	}
	
	/**
	 * 根据当前页获取书的类型
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<BookType> listByPage(int pageNum, int pageSize){
		Page<BookType> page = new Page<BookType>(pageNum, pageSize);
		int count = typeDao.getCount();
		List<BookType> list = typeDao.getTypes(pageNum, pageSize);
		System.out.println(list.toString());
		page.setList(list);
		page.setTotalCount(count);
		
		return page;
	}
	
	/**
	 * 获取书籍总数量
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int getBookCount() {
		int count=BookDao.getCount();
		return count;
	}
	
	/**
	 * 获取类型总数量
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int getBookTypeCount() {
		int count=typeDao.getCount();
		return count;
	}
	
	/**
	 * 根据当前页获取书的类型
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<Book> getBooksByPage(int pageNum, int pageSize){
		Page<Book> page = new Page<Book>(pageNum, pageSize);
		int count = BookDao.getCount();
		List<Book> list = BookDao.getBooksByPage(pageNum, pageSize);
		System.out.println(list.toString());
		page.setList(list);
		page.setTotalCount(count);
		
		return page;
	}
	
	/**
	 * 获取收藏书籍的总数量
	 * @return
	 */
	public int getSaveBookCount() {
		int count=BookDao.getSaveBookCount();
		return count;
	}
	
	/**
	 * 获取加入书架的总数量
	 * @return
	 */
	public int getBookshelfCount() {
		int count=BookDao.getBookshelfCount();
		return count;
	}
}
