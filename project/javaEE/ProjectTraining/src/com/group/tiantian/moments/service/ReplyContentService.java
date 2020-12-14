package com.group.tiantian.moments.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.entity.moments.ReplyContent;
import com.group.tiantian.moments.dao.CommentsDao;
import com.group.tiantian.moments.dao.ReplyContentDao;
import com.group.tiantian.util.ConfigUtil;

public class ReplyContentService {
	private static ReplyContentService replyContentService;
	private static ReplyContentDao replyContentDao;
	/**
	 * �õ�һ��ReplyContentServiceʵ��
	 * 
	 * @return
	 */
	public static ReplyContentService getInstance() {
		if (null == replyContentService) {
			replyContentService = new ReplyContentService();
		}
		if (null == replyContentDao) {
			replyContentDao = ReplyContentDao.getInstance();
		}
		return replyContentService;
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
		PersonalInfo personalInfo=replyContentDao.getPersonalInfo(phoneNum);
		return personalInfo;
	}
	
	/**
	 * �����ݿ��в���˵˵id,�ظ����ݺͻظ��˵��ֻ���,�ظ����ǳ�,�ظ���ͷ��
	 * 
	 * @param start
	 * @param end
	 * @param article
	 * @param content
	 * @return
	 */
	public boolean insertreplyContent(int momentsId,int position,String replyContent, String personPhone,String PersonName,String PersonHead) {
		boolean b = replyContentDao.insertreplyContent(momentsId,position, replyContent, personPhone, PersonName, PersonHead);
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
	public List<ReplyContent> replyContentInfo(int momentsId){
		List<ReplyContent> replyContentInfos = replyContentDao.replyContentInfo(momentsId);	
		return replyContentInfos;
	}


}
