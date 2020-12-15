package com.group.tiantian.idiom.service;

import java.util.List;

import com.group.tiantian.entity.IdiomSearchHistory;
import com.group.tiantian.idiom.dao.IdiomHistoryDaoImpl;

public class IdiomHistoryServiceImpl {
	
	/**
	 * 根据登录的家长手机号以及对应的孩子姓名，查询该用户所有的搜索历史
	 * 
	 * @param phone
	 * @param childName
	 * @return
	 */
	public List<String> listIdiomSearchHistory(String phone, String childName){
		return new IdiomHistoryDaoImpl().findHistoryByInfo(phone, childName);
	}
	
	/**
	 * 保存搜索记录
	 * @param idiomSearchHistory
	 * @return
	 */
	public boolean saveIdiomSearchHistory(IdiomSearchHistory idiomSearchHistory) {
		return new IdiomHistoryDaoImpl().saveIdiomHistory(idiomSearchHistory);
	}
	
	/**
	 * 删除搜索记录
	 * @param phone
	 * @param childName
	 * @param searchStr
	 * @return
	 */
	public boolean deleteIdiomSearchHistory(String phone, String childName,String searchStr) {
		return new IdiomHistoryDaoImpl().deleteIdiomHistory(phone, childName, searchStr);
	}
}
