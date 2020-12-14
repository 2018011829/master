package com.group.tiantian.moments.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.moments.LikeGiveInfo;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.moments.dao.LikeGiveDao;

public class LikeGiveService {
	private static LikeGiveService likeGiveService;
	private static LikeGiveDao likeGiveDao;
	
	/**
	 * �õ�һ��LikeGiveServiceʵ��
	 * 
	 * @return
	 */
	public static LikeGiveService getInstance() {
		if (null == likeGiveService) {
			likeGiveService = new LikeGiveService();
		}
		if (null == likeGiveDao) {
			likeGiveDao = LikeGiveDao.getInstance();
		}
		return likeGiveService;
	}
	/**
	 * ͨ���ֻ��Ų�ѯ�ǳƺ�ͷ��
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return ���ظ�����Ϣ����
	 */
	public PersonalInfo getPersonalInfo(String phoneNum){
		PersonalInfo personalInfo=likeGiveDao.getPersonalInfo(phoneNum);
		return personalInfo;
	}
	
	/**
	 * ͨ��˵˵id�͵������ֻ��Ų�ѯ�����Ƿ�����һ����¼
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return ����˵˵����
	 */
	public boolean selectLikeGive(int momentsId,String likegivePersonPhone) {
		boolean b =  likeGiveDao.selectLikeGive(momentsId, likegivePersonPhone);
		return b;
	}
	

	/**
	 * ͨ��˵˵id�͵������ֻ��Ų�ѯ���Ƿ����
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return ����˵˵����
	 */
	public int selectLikeGiveboolean(int momentsId,String likegivePersonPhone) {
		int temp = likeGiveDao.selectLikeGiveboolean(momentsId, likegivePersonPhone);
		return temp;
	}
	
	/**
	 * ��˵˵id���������ǳƣ��������ֻ��Ŵ�����ޱ���
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return ����˵˵����
	 */
	public boolean insertLikeGiveInfo(int momentsId,String likegivePersonName,String likegivePersonPhone) {
		boolean b = likeGiveDao.insertLikeGiveInfo(momentsId, likegivePersonName, likegivePersonPhone);
		return b;
	}
	/**
	 * ͨ��˵˵id��ѯ����˵˵���޵��ˣ���ý�����ĸ�����Ϊ��������
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public List<String> likeGiveNames(int momentsId){
		List<String> likeGiveNames = likeGiveDao.likeGiveNames(momentsId);
		return likeGiveNames;
	}
	/**
	 * ͨ��˵˵id,�������ֻ���ɾ��������¼
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public boolean deleteLikeGiveInfo(int momentsId,String likegivePersonPhone) {
		boolean b = likeGiveDao.deleteLikeGiveInfo(momentsId,likegivePersonPhone);
		return b;
	}

}
