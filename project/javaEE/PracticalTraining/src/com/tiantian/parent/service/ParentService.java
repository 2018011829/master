package com.tiantian.parent.service;

import com.tiantian.parent.dao.ParentDao;

public class ParentService {
	private static ParentService parentService;
	private static ParentDao parentDao;
	/**
	 * 得到一个parentService实例
	 * @return
	 */
	public static ParentService getInstance() {
		if(null==parentService){
			parentService=new ParentService();
		}
		if(null==parentDao) {
			parentDao=ParentDao.getInstance();
		}
		return parentService;
	}
	
	/**
	 * 注册用户
	 * @param phone
	 * @param nickname
	 * @param password
	 * @return true:手机号码注册成功; false:手机号码已经存在
	 */
	public boolean resigter(String phone,String nickname,String password) {
		//如果该手机号码没有注册过
		if(!parentDao.isExist(phone)) {
			System.out.println("2.该号码未注册");
			parentDao.addParent(phone, nickname, password);
			return true;
		}else {
			return false;
		}
	}
}
