package com.group.tiantian.parent.service;

import com.google.gson.Gson;
import com.group.tiantian.parent.dao.ParentDao;

public class ParentService {
	private static ParentService parentService;
	private static ParentDao parentDao;

	/**
	 * 得到一个parentService实例
	 * 
	 * @return
	 */
	public static ParentService getInstance() {
		if (null == parentService) {
			parentService = new ParentService();
		}
		if (null == parentDao) {
			parentDao = ParentDao.getInstance();
		}
		return parentService;
	}

	/**
	 * 注册用户
	 * 
	 * @param phone
	 * @param nickname
	 * @param password
	 * @return true:手机号码注册成功; false:手机号码注册失败
	 */
	public boolean resigter(String phone, String nickname, String password) {
		boolean b = false;
		b=parentDao.addParent(phone, nickname, password);
		
		return b;
	}

	/**
	 * 查找用户的手机号是否已经注册
	 * 
	 * @param phone
	 * @return
	 */
	public boolean isExistPhone(String phone) {
		boolean b = false;
		if (parentDao.isExist(phone)) {
			b = true;
		}
		return b;
	}

	/**
	 * 判断用户名和密码是否匹配 即用户是否存在
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	public boolean isExistUser(String phone, String password) {
		boolean b = false;
		if (parentDao.isExistUser(phone, password)) {
			b = true;
		}
		return b;
	}
	
	/**
	 * 返回某个父母信息的Json串
	 * @param phone
	 * @return json串
	 */
	public String getOneParentInfo(String phone) {
		return new Gson().toJson(parentDao.selectOneParent(phone));
	}

	public String searchParentsByPhone(String query) {
		return new Gson().toJson(parentDao.queryParentsByPhone(query));
		
	}
}
