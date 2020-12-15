package com.group.tiantian.idiom.service;

import java.util.List;

import com.group.tiantian.entity.IdiomSave;
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
	
	/**
	 * �ղس���
	 * @param idiomSave
	 * @return
	 */
	public boolean saveIdiomInfo(IdiomSave idiomSave) {
		return new IdiomDaoImpl().saveIdiom(idiomSave);
	}
	
	/**
	 * ����idȡ�������ղ�
	 * @param id
	 * @return
	 */
	public boolean cancelSaveIdiomInfo(int id) {
		return new IdiomDaoImpl().cancelSaveIdiom(id);
	}
	
	/**
	 * ���ݳ����ղ���Ϣ���Ҹ�����Ϣ��id
	 * @param phone
	 * @param childName
	 * @param idiomName
	 * @return
	 */
	public int findSaveIdiomIdByInfo(String phone, String childName, String idiomName) {
		return new IdiomDaoImpl().findSaveIdiomId(phone, childName, idiomName);
	}
	
	/**
	 * ���ݳ����ղ���Ϣ�жϸó����Ƿ�ĳ�û���ĳ�������ղ�
	 * @param phone
	 * @param childName
	 * @param idiomName
	 * @return
	 */
	public boolean findIdiomIsSavedByInfo(String phone, String childName, String idiomName) {
		return new IdiomDaoImpl().idiomIsSaved(phone, childName, idiomName);
	}
	
	/**
	 * �����ֻ��źͺ������Ʋ�ѯ�ղصĳ���
	 * @param phone
	 * @param childName
	 * @return
	 */
	public List<String> findSaveIdiomByInfo(String phone, String childName) {
		return new IdiomDaoImpl().findSaveIdiomByInfo(phone, childName);
	}
	
}
