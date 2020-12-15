package com.group.tiantian.collection.service;

import java.util.List;

import com.group.tiantian.collection.dao.CollectionDao;
import com.group.tiantian.entity.Collection;

public class CollectionService {
	public static CollectionDao collectionDao;
	public static CollectionService collectionService;
	
	/**
	 * �õ�һ��CollectionServiceʵ��
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
	 * �����ղص������Ƿ����
	 * @param collection
	 * @return
	 */
	public boolean searchCollection(Collection collection) {
		boolean b=collectionDao.searchCollection(collection);
		
		return b;
	}
	
	/**
	 * ���ղص����ݱ���
	 * @param collection
	 * @return
	 */
	public boolean addCollection(Collection collection) {
		boolean b=collectionDao.addCollection(collection);
		
		return b;
	}
	
	/**
	 * ���ղص�����ɾ��
	 * @param collection
	 * @return
	 */
	public boolean deleteCollection(Collection collection) {
		boolean b=collectionDao.deleteCollection(collection);
		
		return b;
	}
	
	
	/**
	 * ��ѯ�ղص�ͼ������
	 */
	public List<String> searchBookFromCollection(String phone,String cname,String type) {
		
		List<String> names=collectionDao.searchBookFromCollection(phone, cname, type);
		return names;
	}
}
