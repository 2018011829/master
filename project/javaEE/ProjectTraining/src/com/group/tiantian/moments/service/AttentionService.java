package com.group.tiantian.moments.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.moments.Attention;
import com.group.tiantian.moments.dao.AttentionDao;
import com.group.tiantian.moments.dao.ReplyContentDao;

public class AttentionService {
	private static AttentionService attentionService;
	private static AttentionDao attentionDao;
	/**
	 * 得到一个AttentionService实例
	 * 
	 * @return
	 */
	public static AttentionService getInstance() {
		if (null == attentionService) {
			attentionService = new AttentionService();
		}
		if (null == attentionDao) {
			attentionDao = AttentionDao.getInstance();
		}
		return attentionService;
	}
	
	/**
	 * 通过当前用户手机号和发表说说用户的手机号查询表中是否有这一条记录
	 * 
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return
	 */
	public boolean selectAttention(String personPhone, String momentsPhone) {
		boolean b = attentionDao.selectAttention(personPhone, momentsPhone);
		return b;
	}

	/**
	 * 向数据库中插入当前用户手机号和发表说说用户的手机号
	 * 
	 * @param start
	 * @param end
	 * @param article
	 * @param content
	 * @return
	 */
	public boolean insertAttention(String personPhone, String momentsPhone) {
		boolean b = attentionDao.insertAttention(personPhone, momentsPhone);
		return b;
	}
	
	/**
	 * 通过当前用户手机号和发表说说用户的手机号删除该条记录
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public boolean deleteAttention(String personPhone, String momentsPhone) {
		boolean b = attentionDao.deleteAttention(personPhone, momentsPhone);
		return b;
	}

	/**
	 * 通过当前用户手机号查询关注列表
	 * 
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return
	 */
	public List<Attention> getAttentionList(String personPhone) {
		List<Attention> attentions = attentionDao.getAttentionList(personPhone);
		return attentions;
	}

}
