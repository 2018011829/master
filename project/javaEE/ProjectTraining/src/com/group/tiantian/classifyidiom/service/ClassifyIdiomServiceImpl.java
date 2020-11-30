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
	 * ��ȡ���з��ุ�˵������Ӧ���Ӳ˵�
	 * @return
	 */
	public LinkedHashMap<String, List<String>> getAllClassifyIdiom(){
		return new ClassifyIdiomDaoImpl().findAllIdiomType();
	}
	
	
	/**
	 * ���ݳ����ӷ������Ʋ�ѯ�÷����id
	 * @param classifyName
	 * @return
	 */
	public int getIdByClassifyName(String classifyName) {
		return new ClassifyIdiomDaoImpl().findIdByClassifyName(classifyName);
	}
	
}