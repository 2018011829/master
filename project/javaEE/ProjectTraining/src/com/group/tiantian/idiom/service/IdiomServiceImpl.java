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
	 * 根据成语所属分类查询该分类的所有成语
	 * @param classification
	 * @return
	 */
	public List<String> listIdiomByClassification(int classification){
		return new IdiomDaoImpl().findByClassification(classification);
	}
	
	/**
	 * 收藏成语
	 * @param idiomSave
	 * @return
	 */
	public boolean saveIdiomInfo(IdiomSave idiomSave) {
		return new IdiomDaoImpl().saveIdiom(idiomSave);
	}
	
	/**
	 * 根据id取消成语收藏
	 * @param id
	 * @return
	 */
	public boolean cancelSaveIdiomInfo(int id) {
		return new IdiomDaoImpl().cancelSaveIdiom(id);
	}
	
	/**
	 * 根据成语收藏信息查找该条信息的id
	 * @param phone
	 * @param childName
	 * @param idiomName
	 * @return
	 */
	public int findSaveIdiomIdByInfo(String phone, String childName, String idiomName) {
		return new IdiomDaoImpl().findSaveIdiomId(phone, childName, idiomName);
	}
	
	/**
	 * 根据成语收藏信息判断该成语是否被某用户的某个孩子收藏
	 * @param phone
	 * @param childName
	 * @param idiomName
	 * @return
	 */
	public boolean findIdiomIsSavedByInfo(String phone, String childName, String idiomName) {
		return new IdiomDaoImpl().idiomIsSaved(phone, childName, idiomName);
	}
	
	/**
	 * 根据手机号和孩子名称查询收藏的成语
	 * @param phone
	 * @param childName
	 * @return
	 */
	public List<String> findSaveIdiomByInfo(String phone, String childName) {
		return new IdiomDaoImpl().findSaveIdiomByInfo(phone, childName);
	}
	
}
