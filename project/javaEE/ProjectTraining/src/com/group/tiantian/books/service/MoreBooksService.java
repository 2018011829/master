package com.group.tiantian.books.service;

import java.util.List;

import com.group.tiantian.books.dao.BookDao;
import com.group.tiantian.entity.Book;


public class MoreBooksService {

	public static MoreBooksService moreBooksService;
	public static BookDao bookDao;
	
	/**
	 * 得到一个MoreBooksService实例
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
	 * 获取该类型的所有书籍
	 * @param type 书籍类型
	 * @return 返回该类型的所有书籍的集合
	 */
	public List<Book> getAllBookByType(String type){
		List<Book> list=bookDao.getBooksByType(type);
		
		return list;
	}
}
