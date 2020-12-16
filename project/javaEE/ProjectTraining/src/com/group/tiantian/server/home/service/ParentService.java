package com.group.tiantian.server.home.service;

import com.group.tiantian.server.home.dao.ParentDao;

public class ParentService {
	
	private static ParentService parentService;
	
	/**
	 * �õ�һ��parentServiceʵ��
	 * 
	 * @return
	 */
	public static ParentService getInstance() {
		if (null == parentService) {
			parentService = new ParentService();
		}
		return parentService;
	}

	/**
	 * ��ȡ�û�������
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int getCount() {
		int count=ParentDao.getCount();
		return count;
	}
}
