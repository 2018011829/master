package com.group.tiantian.server.home.service;

import com.group.tiantian.server.home.dao.ParentDao;

public class ParentService {
	
	private static ParentService parentService;
	
	/**
	 * 得到一个parentService实例
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
	 * 获取用户总数量
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int getCount() {
		int count=ParentDao.getCount();
		return count;
	}
}
