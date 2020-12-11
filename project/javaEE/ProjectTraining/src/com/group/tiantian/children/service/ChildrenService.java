package com.group.tiantian.children.service;

import java.util.List;

import com.group.tiantian.children.dao.ChildrenDao;
import com.group.tiantian.entity.Child;

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
	
	/**
	 * 查询某电话号码下的所有孩子信息
	 * @return 
	 * */
	
	public List<Child> queryChildrenByPhone(String phone){
		
		return childrenDao.queryChildrenByPhone(phone);
		
	}
}
