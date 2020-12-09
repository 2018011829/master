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
	 * 得到一个LikeGiveService实例
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
	 * 通过手机号查询昵称和头像
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 返回个人信息对象
	 */
	public PersonalInfo getPersonalInfo(String phoneNum){
		PersonalInfo personalInfo=likeGiveDao.getPersonalInfo(phoneNum);
		return personalInfo;
	}
	/**
	 * 将说说id，点赞人昵称，点赞人手机号存入点赞表中，点赞人数通过说说id查询点赞人的个数再写入点赞表
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 返回说说对象
	 */
	public boolean insertLikeGiveInfo(int momentsId,String likegivePersonName,String likegivePersonPhone) {
		boolean b = likeGiveDao.insertLikeGiveInfo(momentsId, likegivePersonName, likegivePersonPhone);
		return b;
	}
	/**
	 * 通过说说id查询给该说说点赞的人，获得结果集的个数即为点赞人数
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

}
