package com.group.tiantian.idiom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.Idiom;
import com.group.tiantian.util.DBUtil;

/**
 * 2020-11-25
 * @author lrf
 */
public class IdiomDaoImpl {
	
	/**
	 * 根据成语所属分类查询该分类的成语
	 * @param classification 成语所属分类
	 * @return
	 */
	public List<Idiom> findByClassification(int classification) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Idiom> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from idiom where classification=" + classification + "");
			rs = pstm.executeQuery();
			while(rs.next()) {
				Idiom idiom = new Idiom();
				idiom.setId(rs.getInt(1));
				idiom.setIdiom(rs.getString(2));
				idiom.setClassification(rs.getInt(3));
				list.add(idiom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}
	
	
	
	
}
