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
	public List<Book> getAllBookByType(String type,String grades,int bookIndex,int size){
		List<Book> list=bookDao.getBooksByType(type,grades,bookIndex,size);
		
		return list;
	}
	
	/**
	 * 从数据库中根据书名查找书籍
	 * 
	 * @param name 书的名称
	 * @return 返回该名称的书
	 */
	public Book getBooksByName(String name) {

		return bookDao.getBookByName(name);
	}
}
