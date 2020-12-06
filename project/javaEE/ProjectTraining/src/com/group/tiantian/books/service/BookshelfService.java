package com.group.tiantian.books.service;

import com.group.tiantian.books.dao.BookshelfDao;
import com.group.tiantian.entity.Bookshelf;

public class BookshelfService {

	public static BookshelfDao bookshelfDao;
	public static BookshelfService bookshelfService;
	
	/**
	 * 得到一个BookshelfService实例
	 * 
	 * @return
	 */
	public static BookshelfService getInstance() {
		if (null == bookshelfService) {
			bookshelfService = new BookshelfService();
		}
		if (null == bookshelfDao) {
			bookshelfDao = BookshelfDao.getInstance();
		}
		return bookshelfService;
	}
	
	/**
	 * 查找收藏的数据是否存在
	 * @param collection
	 * @return
	 */
	public boolean searchBookFromBookshelf(Bookshelf bookshelf) {
		boolean b=bookshelfDao.searchBookFromBookshelf(bookshelf);
		
		return b;
	}
	
	/**
	 * 将书的数据放入书架
	 * @param bookshelf
	 * @return
	 */
	public boolean addBookToBookshelf(Bookshelf bookshelf) {
		boolean b=bookshelfDao.addBookToBookshelf(bookshelf);
		
		return b;
	}
	
	/**
	 * 将书的数据从书架中删除
	 * @param bookshelf
	 * @return
	 */
	public boolean deleteBookFromBookshelf(Bookshelf bookshelf) {
		boolean b=bookshelfDao.deleteBookFromBookshelf(bookshelf);
		
		return b;
	}
}
