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
	 * 得到一个AddMomentsService实例
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
	 * 插入说说图片
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 插入成功 true
	 */
	public boolean insertPictureUrl(String pictureName, int momentsId,String time,String personalPhone) {
		boolean b=addMomentsDao.insertPictures(pictureName,momentsId,time,personalPhone);
		return b;
	}
	/**
	 * 插入说说文字
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 插入成功 true
	 */
	public boolean insertContent(String content, int momentsId,String time,String personalPhone) {
		boolean b=addMomentsDao.insertContents(content,momentsId,time,personalPhone);
		return b;
	}
	
	/**
	 * 向一条说说信息中插入时间为生成一个id
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 插入成功 true
	 */
	public boolean insertPersonalInfo(String phoneNumber,String time) {
		boolean b=addMomentsDao.insertPersonalInfo(phoneNumber,time);
		return b;
	}
	/**
	 * 通过时间和手机号查询说说id
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 返回一个说说id
	 */
	public int serchMomentsId(String personalPhone,String time) {
		int momentsId = addMomentsDao.serchMomentsId(personalPhone, time);
		return momentsId;
	}

	/**
	 * 查询说说列表获取所有说说信息
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
	 * 根据说说id查询所有说说图片
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
	 * 根据说说id查询所有说说文案
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
	 * 通过手机号查询昵称和头像
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 返回个人信息对象
	 */
	public PersonalInfo getPersonalInfo(String phoneNum){
		PersonalInfo personalInfo=addMomentsDao.getPersonalInfo(phoneNum);
		return personalInfo;
	}
}
