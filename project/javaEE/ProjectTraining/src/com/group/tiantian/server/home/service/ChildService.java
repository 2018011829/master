package com.group.tiantian.server.home.service;

import com.group.tiantian.server.home.dao.ChildDao;

public class ChildService {

private static ChildService childService;
	
	/**
	 * 得到一个parentService实例
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
	 * 获取用户总数量
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int getCount() {
		int count=ChildDao.getCount();
		return count;
	}
}
