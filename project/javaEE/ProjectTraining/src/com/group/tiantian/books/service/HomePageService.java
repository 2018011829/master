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
	 * 得到一个HomePageService实例
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
	 * 获取首页所需要的信息
	 * @return 顺序存储键/值对，根据键的顺序，key为书的类型，value为选出的六本书的集合
	 * @param grades 孩子所在的年级1-3,4-6
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
	 * 根据书名获取书籍的所有信息
	 * @param bookName
	 * @return
	 */
	public Book getBookByName(String bookName) {
		Book book = bookDao.getBookByName(bookName);
		return book;
	}
}
