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
	 * @param grades �������ڵ��꼶1-3,4-6
	 */
	public TreeMap<String,List<Book>> getHomePageInfo(String grades){
		TreeMap<String,List<Book>> treeMap=null;
		List<BookType> bookTypes=typeDao.getAllTypes();
		if(bookTypes!=null) {
			treeMap=new TreeMap<String,List<Book>>();
			for(BookType bookType:bookTypes) {
				String type=bookType.getType();
				if(bookDao.getSixBooksByType(type,grades).size()!=0) {
					List<Book> books=bookDao.getSixBooksByType(type,grades);
					treeMap.put(type, books);
				}else {
					continue;
				}
			}
		}
		return treeMap;
	}
	
	/**
	 * ����������ȡ�鼮��������Ϣ
	 * @param bookName
	 * @return
	 */
	public Book getBookByName(String bookName) {
		Book book = bookDao.getBookByName(bookName);
		return book;
	}
}
