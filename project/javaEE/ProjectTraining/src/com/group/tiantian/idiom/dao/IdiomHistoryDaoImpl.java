package com.group.tiantian.idiom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.IdiomSearchHistory;
import com.group.tiantian.util.DBUtil;

public class IdiomHistoryDaoImpl {

	/**
	 * 根据登录的家长手机号以及对应的孩子姓名，查询该用户所有的搜索历史
	 * 
	 * @param phone
	 * @param childName
	 * @return
	 */
	public List<String> findHistoryByInfo(String phone, String childName) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from idiom_search_history where phone='" + phone
					+ "' and childName='" + childName + "' and status!=0");
			rs = pstm.executeQuery();
			while (rs.next()) {
				String searchStr = rs.getString("searchStr");
				list.add(searchStr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}

	/**
	 * 保存搜索记录
	 * 
	 * @param idiomSearchHistory
	 * @return
	 */
	public boolean saveIdiomHistory(IdiomSearchHistory idiomSearchHistory) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// 获取成语搜索相关信息
		String phone = idiomSearchHistory.getPhone();
		String childName = idiomSearchHistory.getChildName();
		String searchStr = idiomSearchHistory.getSearchStr();
		int status = idiomSearchHistory.getStatus();
		int n = -1;// 存储插入的记录数
		try {
			conn = DBUtil.getConnection();
			String select = "select status from idiom_search_history where phone='" + phone + "' and childName='"
					+ childName + "' and searchStr='" + searchStr + "'";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (!isExist) {
				String sql = "insert into idiom_search_history(phone,childName,searchStr,status) values('" + phone
						+ "','" + childName + "','" + searchStr + "', " + status + ")";
				// 将成语搜索信息插入到数据库表中
				n = pstm.executeUpdate(sql);
			} else {
				if (rs.getInt("status") == 0) {
					String sql = "update idiom_search_history set status=" + status + "where phone='" + phone
							+ "' and childName='" + childName + "' and searchStr='" + searchStr + "'";
					// 将成语搜索信息插入到数据库表中
					n = pstm.executeUpdate(sql);
				}else {
					n = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return n > 0 ? true : false;
	}

	/**
	 * 删除搜索记录
	 * 
	 * @param phone
	 * @param childName
	 * @param searchStr
	 * @return
	 */
	public boolean deleteIdiomHistory(String phone, String childName, String searchStr) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int n = -1;// 存储修改的记录数
		try {
			conn = DBUtil.getConnection();
			String select = "select * from idiom_search_history where phone='" + phone + "' and childName='" + childName
					+ "' and searchStr='" + searchStr + "'";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
				String sql = "update idiom_search_history set status=" + 0 + "where phone='" + phone
						+ "' and childName='" + childName + "' and searchStr='" + searchStr + "'";
				// 将成语搜索信息插入到数据库表中
				n = pstm.executeUpdate(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return n > 0 ? true : false;
	}

}
