package com.group.tiantian.server.login.service;

import com.group.tiantian.server.login.dao.LoginDao;

public class LoginService {
	private static LoginService loginService;
	private static LoginDao loginDao;

	/**
	 * �õ�һ��parentServiceʵ��
	 * 
	 * @return
	 */
	public static LoginService getInstance() {
		if (null == loginService) {
			loginService = new LoginService();
		}
		if (null == loginDao) {
			loginDao = LoginDao.getInstance();
		}
		return loginService;
	}
	
	/**
	 * �ж��û����Ƿ����
	 * 
	 * @param userName �û���
	 * @return �Ƿ����
	 */
	public boolean isExist(String userName) {
		boolean b=loginDao.isExist(userName);
				
		return b;
	}

	/**
	 * �ж��û����������Ƿ�ƥ�� ���û��Ƿ����
	 * 
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public boolean isExistUser(String userName, String userPwd) {
		boolean b = loginDao.isExistUser(userName, userPwd);
		
		return b;
	}
	
	/**
	 * ע���û�
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean registerUser(String userName,String password) {
		boolean b=loginDao.registerUser(userName, password);
		
		return b;
	}
}
