package com.group.tiantian.idiom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.Idiom;
import com.group.tiantian.entity.IdiomSave;
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
	public List<String> findByClassification(int classification) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from idiom where classification=" + classification + "");
			rs = pstm.executeQuery();
			while(rs.next()) {
				Idiom idiom = new Idiom();
				idiom.setId(rs.getInt(1));
				idiom.setIdiom(rs.getString(2));
				idiom.setClassification(rs.getInt(3));
				list.add(idiom.getIdiom());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}
	
	/**
	 * 收藏成语
	 * @param idiomSave
	 * @return
	 */
	public boolean saveIdiom(IdiomSave idiomSave) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// 获取成语收藏信息
		String phone = idiomSave.getPhone();
		String childName = idiomSave.getChildName();
		String idiomName = idiomSave.getIdiomName();
		int n = -1;// 存储插入的记录数
		try {
			conn = DBUtil.getConnection();
			String select = "select * from idiom_save where phone='"+phone+"' and childName='"+childName+"' and idiomName='"+idiomName+"'";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (!isExist) {
				String sql = "insert into idiom_save(phone,childName,idiomName) values('"+phone+"','"+childName+"','"+idiomName+"')";
				// 将成语收藏信息插入到数据库表中
				n = pstm.executeUpdate(sql);
			System.out.println("收藏成语的SQL语句：" + sql);
			}
			System.out.println("收藏成语前的sql语句：" + select);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return n > 0 ? true : false;
	}
	
	/**
	 * 根据id取消成语收藏
	 * @param id
	 * @return
	 */
	public boolean cancelSaveIdiom(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int n = -1;// 存储修改的记录
		try {
			conn = DBUtil.getConnection();
			String select = "select * from idiom_save where id=" + id + "";
			System.out.println("取消收藏前的查询SQL语句：" + select);
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
				String sql = "delete from idiom_save where id=" + id + "";
				// 将成语收藏信息从数据库表中删除
				n = pstm.executeUpdate(sql);
				System.out.println("取消收藏的SQL语句：" + sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return n > 0 ? true : false;
	}
	
	/**
	 * 根据成语收藏信息查找该条信息的id
	 * @param phone
	 * @param childName
	 * @param idiomName
	 * @return
	 */
	public int findSaveIdiomId(String phone, String childName, String idiomName) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int n = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "select id from idiom_save where phone='"+phone+"' and childName='"+childName+"' and idiomName='"+idiomName+"'";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				n = rs.getInt(1);
			}
			System.out.println("根据成语相关信息查询id的SQL语句：" + sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return n;
	}
	
	/**
	 * 根据成语收藏信息判断该成语是否被某用户的某个孩子收藏
	 * @param phone
	 * @param childName
	 * @param idiomName
	 * @return
	 */
	public boolean idiomIsSaved(String phone, String childName, String idiomName) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from idiom_save where phone='"+phone+"' and childName='"+childName+"' and idiomName='"+idiomName+"'";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if(rs.next()) {
				return true;
			}
			System.out.println("判断是否已被收藏的SQL语句：" + sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return false;
	}

}