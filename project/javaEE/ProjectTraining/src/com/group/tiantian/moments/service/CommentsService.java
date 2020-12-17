package com.group.tiantian.moments.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.moments.Comment;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.moments.dao.CommentsDao;
import com.group.tiantian.moments.dao.LikeGiveDao;

public class CommentsService {
	private static CommentsService commentsService;
	private static CommentsDao commentsDao;
	/**
	 * �õ�һ��CommentsServiceʵ��
	 * 
	 * @return
	 */
	public static CommentsService getInstance() {
		if (null == commentsService) {
			commentsService = new CommentsService();
		}
		if (null == commentsDao) {
			commentsDao = CommentsDao.getInstance();
		}
		return commentsService;
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
		PersonalInfo personalInfo=commentsDao.getPersonalInfo(phoneNum);
		return personalInfo;
	}
	
	/**
	 * �����ݿ��в���˵˵id,�������ݺ������˵��ֻ���,�������ǳ�,������ͷ��
	 * 
	 * @param start
	 * @param end
	 * @param article
	 * @param content
	 * @return
	 */
	public boolean insertMoments(int momentsId,String comments, String personalPhone,String PersonName,String PersonHead,String time) {
		boolean b = commentsDao.insertMoments(momentsId, comments, personalPhone, PersonName,PersonHead,time);
		return b;
	}
	/**
	 * ͨ��˵˵id��ѯ����˵˵���۵��˵��ǳƺ���������
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public List<Comment> commentsInfo(int momentsId){
		List<Comment> commentsInfos = commentsDao.commentsInfo(momentsId);
		return commentsInfos;
	}

}
