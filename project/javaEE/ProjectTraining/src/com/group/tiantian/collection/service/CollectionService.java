package com.group.tiantian.collection.service;

import java.util.List;

import com.group.tiantian.collection.dao.CollectionDao;
import com.group.tiantian.entity.Collection;

public class CollectionService {
	public static CollectionDao collectionDao;
	public static CollectionService collectionService;
	
	/**
	 * 得到一个CollectionService实例
	 * 
	 * @return
	 */
	public static CollectionService getInstance() {
		if (null == collectionService) {
			collectionService = new CollectionService();
		}
		if (null == collectionDao) {
			collectionDao = CollectionDao.getInstance();
		}
		return collectionService;
	}
	
	/**
	 * 查找收藏的数据是否存在
	 * @param collection
	 * @return
	 */
	public boolean searchCollection(Collection collection) {
		boolean b=collectionDao.searchCollection(collection);
		
		return b;
	}
	
	/**
	 * 将收藏的数据保存
	 * @param collection
	 * @return
	 */
	public boolean addCollection(Collection collection) {
		boolean b=collectionDao.addCollection(collection);
		
		return b;
	}
	
	/**
	 * 将收藏的数据删除
	 * @param collection
	 * @return
	 */
	public boolean deleteCollection(Collection collection) {
		boolean b=collectionDao.deleteCollection(collection);
		
		return b;
	}
	
	
	/**
	 * 查询收藏的图书名称
	 */
	public List<String> searchBookFromCollection(String phone,String cname,String type) {
		
		List<String> names=collectionDao.searchBookFromCollection(phone, cname, type);
		return names;
	}
}
