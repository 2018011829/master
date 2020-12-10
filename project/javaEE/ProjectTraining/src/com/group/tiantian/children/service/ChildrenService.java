package com.group.tiantian.children.service;

import com.group.tiantian.children.dao.ChildrenDao;

public class ChildrenService {
	private static ChildrenService childrenService;
	private static ChildrenDao childrenDao;
	
	
	/**
	 * 得到一个childrenService实例
	 * 
	 * @return ChildrenService
	 */
	public static ChildrenService getInstance() {
		if (null == childrenService) {
			childrenService = new ChildrenService();
		}
		if (null == childrenDao) {
			childrenDao = ChildrenDao.getInstance();
		}
		return childrenService;
	}
	
	/**
	 * 添加孩子信息
	 * */
	public Boolean addChild(String name,String grade,String sex,String parentPhone) {
		
		return childrenDao.addChild(name, grade, sex, parentPhone);
		
	}
}
