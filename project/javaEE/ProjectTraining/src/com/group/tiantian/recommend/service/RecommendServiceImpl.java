package com.group.tiantian.recommend.service;

import java.util.List;

import com.group.tiantian.entity.Recommend;
import com.group.tiantian.recommend.dao.RecommendDaoImpl;

public class RecommendServiceImpl {
	
	/**
	 * ��ѯ�Ƽ������������Ϣ
	 * @return
	 */
	public List<Recommend> listRecommend(){
		return new RecommendDaoImpl().findRecommend();
	}
}
