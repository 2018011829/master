package com.group.tiantian.server.home.service;

import com.group.tiantian.server.home.dao.ChildDao;

public class ChildService {

private static ChildService childService;
	
	/**
	 * �õ�һ��parentServiceʵ��
	 * 
	 * @return
	 */
	public static ChildService getInstance() {
		if (null == childService) {
			childService = new ChildService();
		}
		return childService;
	}

	/**
	 * ��ȡ�û�������
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int getCount() {
		int count=ChildDao.getCount();
		return count;
	}
}
