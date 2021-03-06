package com.group.tiantian.classifyidiom.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.group.tiantian.classifyidiom.dao.ClassifyIdiomDaoImpl;

/**
 * 2020-11-25
 * 2020-11-28
 * 
 * @author lrf
 */
public class ClassifyIdiomServiceImpl {
	
	/**
	 * 获取所有分类父菜单和其对应的子菜单
	 * @return
	 */
	public LinkedHashMap<String, List<String>> getAllClassifyIdiom(){
		return new ClassifyIdiomDaoImpl().findAllIdiomType();
	}
	
	
	/**
	 * 根据成语子分类名称查询该分类的id
	 * @param classifyName
	 * @return
	 */
	public int getIdByClassifyName(String classifyName) {
		return new ClassifyIdiomDaoImpl().findIdByClassifyName(classifyName);
	}
	
}
