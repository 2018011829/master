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
	 * 得到一个ReplyContentService实例
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
	 * 通过手机号查询昵称和头像
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 返回个人信息对象
	 */
	public PersonalInfo getPersonalInfo(String phoneNum){
		PersonalInfo personalInfo=replyContentDao.getPersonalInfo(phoneNum);
		return personalInfo;
	}
	
	/**
	 * 向数据库中插入说说id,回复内容和回复人的手机号,回复人昵称,回复人头像
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
	 * 通过说说id查询给该说说评论的人的昵称和评论内容
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
