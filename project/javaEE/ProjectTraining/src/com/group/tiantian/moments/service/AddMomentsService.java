package com.group.tiantian.moments.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.books.dao.BookContentsDao;
import com.group.tiantian.books.service.BookContentsService;
import com.group.tiantian.entity.moments.Moments;
import com.group.tiantian.entity.moments.MomentsPicture;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.moments.dao.AddMomentsDao;

public class AddMomentsService {
	private static AddMomentsService addMomentsService;
	public static AddMomentsDao addMomentsDao;
	
	/**
	 * �õ�һ��AddMomentsServiceʵ��
	 * 
	 * @return
	 */
	public static AddMomentsService getInstance() {
		if (null == addMomentsService) {
			addMomentsService = new AddMomentsService();
		}
		if (null == addMomentsDao) {
			addMomentsDao = AddMomentsDao.getInstance();
		}
		return addMomentsService;
	}
	/**
	 * ����˵˵ͼƬ
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return ����ɹ� true
	 */
	public boolean insertPictureUrl(String pictureName, int momentsId,String time,String personalPhone) {
		boolean b=addMomentsDao.insertPictures(pictureName,momentsId,time,personalPhone);
		return b;
	}
	/**
	 * ����˵˵����
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return ����ɹ� true
	 */
	public boolean insertContent(String content, int momentsId,String time,String personalPhone) {
		boolean b=addMomentsDao.insertContents(content,momentsId,time,personalPhone);
		return b;
	}
	
	/**
	 * ��һ��˵˵��Ϣ�в���ʱ��Ϊ����һ��id
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return ����ɹ� true
	 */
	public boolean insertPersonalInfo(String phoneNumber,String time) {
		boolean b=addMomentsDao.insertPersonalInfo(phoneNumber,time);
		return b;
	}
	/**
	 * ͨ��ʱ����ֻ��Ų�ѯ˵˵id
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return ����һ��˵˵id
	 */
	public int serchMomentsId(String personalPhone,String time) {
		int momentsId = addMomentsDao.serchMomentsId(personalPhone, time);
		return momentsId;
	}

	/**
	 * ��ѯ˵˵�б��ȡ����˵˵��Ϣ
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public List<Moments> getMoments(){
		List<Moments> moments = addMomentsDao.getMoments();
		return moments;
	}
	/**
	 * ����˵˵id��ѯ����˵˵ͼƬ
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public List<MomentsPicture> getMomentsPicture(int momentsId){
		List<MomentsPicture> momentsPictures=addMomentsDao.getMomentsPicture(momentsId);
		return momentsPictures;
	}
	/**
	 * ����˵˵id��ѯ����˵˵�İ�
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public String getMomentsContent(int momentsId){
		String momentsContent=addMomentsDao.getMomentsContent(momentsId);
		return momentsContent;
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
		PersonalInfo personalInfo=addMomentsDao.getPersonalInfo(phoneNum);
		return personalInfo;
	}
}
