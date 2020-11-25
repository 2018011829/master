package com.group.tiantian.classifyidiom.service;

import java.util.List;

import com.group.tiantian.classifyidiom.dao.IdiomDaoImpl;
import com.group.tiantian.entity.Idiom;

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
	public List<Idiom> listIdiomByClassification(int classification){
		return new IdiomDaoImpl().findByClassification(classification);
	}
	
	
}
