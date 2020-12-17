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
	 * 得到一个CommentsService实例
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
	 * 通过手机号查询昵称和头像
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 返回个人信息对象
	 */
	public PersonalInfo getPersonalInfo(String phoneNum){
		PersonalInfo personalInfo=commentsDao.getPersonalInfo(phoneNum);
		return personalInfo;
	}
	
	/**
	 * 向数据库中插入说说id,评论内容和评论人的手机号,评论人昵称,评论人头像
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
	 * 通过说说id查询给该说说评论的人的昵称和评论内容
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
