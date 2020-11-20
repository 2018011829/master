package com.tiantian.parent.service;

import com.tiantian.parent.dao.ParentDao;

public class ParentService {
	private static ParentService parentService;
	private static ParentDao parentDao;
	/**
	 * �õ�һ��parentServiceʵ��
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
	 * ע���û�
	 * @param phone
	 * @param nickname
	 * @param password
	 * @return true:�ֻ�����ע��ɹ�; false:�ֻ������Ѿ�����
	 */
	public boolean resigter(String phone,String nickname,String password) {
		//������ֻ�����û��ע���
		if(!parentDao.isExist(phone)) {
			System.out.println("2.�ú���δע��");
			parentDao.addParent(phone, nickname, password);
			return true;
		}else {
			return false;
		}
	}
}
