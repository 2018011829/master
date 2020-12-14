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
	 * �õ�һ��AttentionServiceʵ��
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
	 * ͨ����ǰ�û��ֻ��źͷ���˵˵�û����ֻ��Ų�ѯ�����Ƿ�����һ����¼
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
	 * �����ݿ��в��뵱ǰ�û��ֻ��źͷ���˵˵�û����ֻ���
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
	 * ͨ����ǰ�û��ֻ��źͷ���˵˵�û����ֻ���ɾ��������¼
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
	 * ͨ����ǰ�û��ֻ��Ų�ѯ��ע�б�
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
