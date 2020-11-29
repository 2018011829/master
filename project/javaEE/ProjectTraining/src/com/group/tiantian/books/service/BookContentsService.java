package com.group.tiantian.books.service;

import java.util.List;

import com.group.tiantian.books.dao.BookContentsDao;
import com.group.tiantian.entity.Content;


public class BookContentsService {

	private static BookContentsService bookContentsService;
	private static BookContentsDao bookContentsDao;
	
	/**
	 * �õ�һ��BookContentsServiceʵ��
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
	 * ����ĳ����Ŀ¼������
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return ����ɹ� true
	 */
	public boolean insertContentData(double id, int start, String articleName, String contentName) {
		boolean b=bookContentsDao.insertContents(id, start, articleName, contentName);
		return b;
	}
	
	/**
	 * ��ȡĳ���������Ŀ¼
	 * @param articleName
	 * @return
	 */
	public List<Content> getBookContents(String articleName){
		List<Content> contents=bookContentsDao.getBookContents(articleName);
		
		return contents;
	}
}
