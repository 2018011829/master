package com.group.tiantian.books.service;

import com.group.tiantian.books.dao.BookshelfDao;
import com.group.tiantian.entity.Bookshelf;

public class BookshelfService {

	public static BookshelfDao bookshelfDao;
	public static BookshelfService bookshelfService;
	
	/**
	 * �õ�һ��BookshelfServiceʵ��
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
	 * �����ղص������Ƿ����
	 * @param collection
	 * @return
	 */
	public boolean searchBookFromBookshelf(Bookshelf bookshelf) {
		boolean b=bookshelfDao.searchBookFromBookshelf(bookshelf);
		
		return b;
	}
	
	/**
	 * ��������ݷ������
	 * @param bookshelf
	 * @return
	 */
	public boolean addBookToBookshelf(Bookshelf bookshelf) {
		boolean b=bookshelfDao.addBookToBookshelf(bookshelf);
		
		return b;
	}
	
	/**
	 * ��������ݴ������ɾ��
	 * @param bookshelf
	 * @return
	 */
	public boolean deleteBookFromBookshelf(Bookshelf bookshelf) {
		boolean b=bookshelfDao.deleteBookFromBookshelf(bookshelf);
		
		return b;
	}
}
