package com.group.tiantian.idiom.service;

import java.util.List;

import com.group.tiantian.entity.IdiomSearchHistory;
import com.group.tiantian.idiom.dao.IdiomHistoryDaoImpl;

public class IdiomHistoryServiceImpl {
	
	/**
	 * ���ݵ�¼�ļҳ��ֻ����Լ���Ӧ�ĺ�����������ѯ���û����е�������ʷ
	 * 
	 * @param phone
	 * @param childName
	 * @return
	 */
	public List<String> listIdiomSearchHistory(String phone, String childName){
		return new IdiomHistoryDaoImpl().findHistoryByInfo(phone, childName);
	}
	
	/**
	 * ����������¼
	 * @param idiomSearchHistory
	 * @return
	 */
	public boolean saveIdiomSearchHistory(IdiomSearchHistory idiomSearchHistory) {
		return new IdiomHistoryDaoImpl().saveIdiomHistory(idiomSearchHistory);
	}
	
	/**
	 * ɾ��������¼
	 * @param phone
	 * @param childName
	 * @param searchStr
	 * @return
	 */
	public boolean deleteIdiomSearchHistory(String phone, String childName,String searchStr) {
		return new IdiomHistoryDaoImpl().deleteIdiomHistory(phone, childName, searchStr);
	}
}
