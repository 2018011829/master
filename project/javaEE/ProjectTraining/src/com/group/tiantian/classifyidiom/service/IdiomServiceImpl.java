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
	 * 根据成语所属分类查询该分类的所有成语
	 * @param classification
	 * @return
	 */
	public List<Idiom> listIdiomByClassification(int classification){
		return new IdiomDaoImpl().findByClassification(classification);
	}
	
	
}
