package com.group.tiantian.books.service;

import java.util.List;

import com.group.tiantian.books.dao.BookContentsDao;
import com.group.tiantian.entity.Content;


public class BookContentsService {

	private static BookContentsService bookContentsService;
	private static BookContentsDao bookContentsDao;
	
	/**
	 * 得到一个BookContentsService实例
	 * 
	 * @return
	 */
	public static BookContentsService getInstance() {
		if (null == bookContentsService) {
			bookContentsService = new BookContentsService();
		}
		if (null == bookContentsDao) {
			bookContentsDao = BookContentsDao.getInstance();
		}
		return bookContentsService;
	}
	
	/**
	 * 插入某本书目录的数据
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 插入成功 true
	 */
	public boolean insertContentData(double id, int start, String articleName, String contentName) {
		boolean b=bookContentsDao.insertContents(id, start, articleName, contentName);
		return b;
	}
	
	/**
	 * 获取某本书的所有目录
	 * @param articleName
	 * @return
	 */
	public List<Content> getBookContents(String articleName){
		List<Content> contents=bookContentsDao.getBookContents(articleName);
		
		return contents;
	}
}
