package com.group.tiantian.books.service;

import com.group.tiantian.books.dao.ReadBookDao;

public class ReadBookService {

	private static ReadBookService readBookService;
	private static ReadBookDao readBookDao;
	
	/**
	 * �õ�һ��ReadBookServiceʵ��
	 * 
	 * @return
	 */
	public static ReadBookService getInstance() {
		if (null == readBookService) {
			readBookService = new ReadBookService();
		}
		if (null == readBookDao) {
			readBookDao = ReadBookDao.getInstance();
		}
		return readBookService;
	}
	
	
}
