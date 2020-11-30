package com.group.tiantian.idiom.service;

import java.util.List;

import com.group.tiantian.idiom.dao.IdiomDaoImpl;

/**
 * 2020-11-25
 * @author lrf
 */
public class IdiomServiceImpl {
	
	/**
	 * 根据成语所属分类查询该分类的所有成语
	 * @param classification
	 * @return
	 */
	public List<String> listIdiomByClassification(int classification){
		return new IdiomDaoImpl().findByClassification(classification);
	}
	
	
}
