package com.group.tiantian.recommend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.Recommend;
import com.group.tiantian.util.DBUtil;

public class RecommendDaoImpl {
	
	/**
	 *  查询推荐的文章相关信息
	 * @return
	 */
	public List<Recommend> findRecommend(){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Recommend> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from daily_recommend");
			rs = pstm.executeQuery();
			while (rs.next()) {
				Recommend recommend = new Recommend();
				recommend.setId(rs.getInt(1));
				recommend.setSynopsis(rs.getString(2));
				recommend.setImg(rs.getString(3));
				recommend.setArticle(rs.getString(4));
				list.add(recommend);
			}
			System.out.println("每日推荐数量：" + list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}
}
