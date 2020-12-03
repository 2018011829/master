package com.group.tiantian.parent.service;

import com.google.gson.Gson;
import com.group.tiantian.parent.dao.ParentDao;

public class ParentService {
	private static ParentService parentService;
	private static ParentDao parentDao;

	/**
	 * �õ�һ��parentServiceʵ��
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
	 * ע���û�
	 * 
	 * @param phone
	 * @param nickname
	 * @param password
	 * @return true:�ֻ�����ע��ɹ�; false:�ֻ�����ע��ʧ��
	 */
	public boolean resigter(String phone, String nickname, String password) {
		boolean b = false;
		b=parentDao.addParent(phone, nickname, password);
		
		return b;
	}

	/**
	 * �����û����ֻ����Ƿ��Ѿ�ע��
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
	 * �ж��û����������Ƿ�ƥ�� ���û��Ƿ����
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
	 * ����ĳ����ĸ��Ϣ��Json��
	 * @param phone
	 * @return json��
	 */
	public String getOneParentInfo(String phone) {
		return new Gson().toJson(parentDao.selectOneParent(phone));
	}

	public String searchParentsByPhone(String query) {
		return new Gson().toJson(parentDao.queryParentsByPhone(query));
		
	}
}
