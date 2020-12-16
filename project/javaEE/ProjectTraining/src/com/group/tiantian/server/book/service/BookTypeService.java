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
	 * �õ�һ��BookTypeServiceʵ��
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
	 * ����id���������Ϣ
	 * @param 
	 * @return 
	 */
	public Book searchBookInfo(int id) {
		Book book=BookDao.searchBookInfo(id);
		return book;
	}
	
	/**
	 * ���������Ƿ��Ѿ�����
	 * @param name
	 * @return ���ڷ���true
	 */
	public boolean searchBook(String name) {
		boolean b=BookDao.searchBook(name);
		return b;
	}
	
	/**
	 * �������Ϣ��ӵ����ݿ�
	 * @param book
	 * @return
	 */
	public boolean addBook(Book book) {
		boolean b=BookDao.addBook(book);
		
		return b;
	}
	
	/**
	 * �����ݿ��в�������ͼ�������
	 * @return ��������ͼ�����͵ļ���
	 */
	public List<BookType> getAllTypes(){
		List<BookType> list=typeDao.getAllTypes();
		
		return list;
	}
	
	/**
	 * ���ݵ�ǰҳ��ȡ�������
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
	 * ��ȡ�鼮������
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int getBookCount() {
		int count=BookDao.getCount();
		return count;
	}
	
	/**
	 * ��ȡ����������
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int getBookTypeCount() {
		int count=typeDao.getCount();
		return count;
	}
	
	/**
	 * ���ݵ�ǰҳ��ȡ�������
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
	 * ��ȡ�ղ��鼮��������
	 * @return
	 */
	public int getSaveBookCount() {
		int count=BookDao.getSaveBookCount();
		return count;
	}
	
	/**
	 * ��ȡ������ܵ�������
	 * @return
	 */
	public int getBookshelfCount() {
		int count=BookDao.getBookshelfCount();
		return count;
	}
}
