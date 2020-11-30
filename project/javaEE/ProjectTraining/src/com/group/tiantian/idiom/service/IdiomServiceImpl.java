package com.group.tiantian.idiom.service;

import java.util.List;

import com.group.tiantian.idiom.dao.IdiomDaoImpl;

/**
 * 2020-11-25
 * @author lrf
 */
public class IdiomServiceImpl {
	
	/**
	 * ���ݳ������������ѯ�÷�������г���
	 * @param classification
	 * @return
	 */
	public List<String> listIdiomByClassification(int classification){
		return new IdiomDaoImpl().findByClassification(classification);
	}
	
	
}
