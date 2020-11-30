package com.group.tiantian.books.service;

import java.util.List;
import java.util.TreeMap;

import com.group.tiantian.books.dao.BookDao;
import com.group.tiantian.books.dao.TypeDao;
import com.group.tiantian.entity.Book;
import com.group.tiantian.entity.BookType;


public class HomePageService {

	private static HomePageService homePageService;
	private static BookDao bookDao;
	private static TypeDao typeDao;

	/**
	 * �õ�һ��HomePageServiceʵ��
	 * 
	 * @return
	 */
	public static HomePageService getInstance() {
		if (null == homePageService) {
			homePageService = new HomePageService();
		}
		if (null == bookDao) {
			bookDao = BookDao.getInstance();
		}
		if (null == typeDao) {
			typeDao = TypeDao.getInstance();
		}
		return homePageService;
	}
	
	/**
	 * ��ȡ��ҳ����Ҫ����Ϣ
	 * @return ˳��洢��/ֵ�ԣ����ݼ���˳��keyΪ������ͣ�valueΪѡ����������ļ���
	 */
	public TreeMap<String,List<Book>> getHomePageInfo(){
		TreeMap<String,List<Book>> treeMap=null;
		List<BookType> bookTypes=typeDao.getAllTypes();
		if(bookTypes!=null) {
			treeMap=new TreeMap<String,List<Book>>();
			for(BookType bookType:bookTypes) {
				String type=bookType.getType();
				List<Book> books=bookDao.getSixBooksByType(type);
				treeMap.put(type, books);
			}
		}
		return treeMap;
	}
}