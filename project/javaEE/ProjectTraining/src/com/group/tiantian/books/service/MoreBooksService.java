package com.group.tiantian.books.service;

import java.util.List;

import com.group.tiantian.books.dao.BookDao;
import com.group.tiantian.entity.Book;


public class MoreBooksService {

	public static MoreBooksService moreBooksService;
	public static BookDao bookDao;
	
	/**
	 * �õ�һ��MoreBooksServiceʵ��
	 * 
	 * @return
	 */
	public static MoreBooksService getInstance() {
		if (null == moreBooksService) {
			moreBooksService = new MoreBooksService();
		}
		if (null == bookDao) {
			bookDao = BookDao.getInstance();
		}
		return moreBooksService;
	}
	
	/**
	 * ��ȡ�����͵������鼮
	 * @param type �鼮����
	 * @return ���ظ����͵������鼮�ļ���
	 */
	public List<Book> getAllBookByType(String type){
		List<Book> list=bookDao.getBooksByType(type);
		
		return list;
	}
}
