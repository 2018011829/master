package com.group.tiantian.server.idiom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.server.entity.Idiom;
import com.group.tiantian.util.DBUtil;

public class IdiomDao {

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
			pstm = conn.prepareStatement("select * from idiom");
			rs = pstm.executeQuery();
			while(rs.next()) {
				Idiom idiom = new Idiom();
				idiom.setId(rs.getInt(1));
				idiom.setIdiom(rs.getString(2));
				idiom.setIdiomType(getIdiomTypeById(rs.getInt("classification")));
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
	 * 获取成语总数量
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static int getCount() {
		int count=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select count(*) from idiom";
		try {
			pstamt=conn.prepareStatement(sql);
			ResultSet rs=pstamt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
				System.out.println(count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	public static List<Idiom> getIdioms(int pageNum, int pageSize) {
		List<Idiom> list=new ArrayList<>();
		String sql="select * from idiom limit ?,?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setInt(1,(pageNum-1)*pageSize);  //从第几条开始
			pstamt.setInt(2, pageSize);         //数量
			ResultSet rs=pstamt.executeQuery();
			while(rs.next()) {
				Idiom idiom=new Idiom();
				idiom.setId(rs.getInt("id"));
				idiom.setIdiom(rs.getString("idiom"));
				idiom.setIdiomType(getIdiomTypeById(rs.getInt("classification")));
				list.add(idiom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static String getIdiomTypeById(int id) {
		String type=null;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select * from classifyidiom where id=?";
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setInt(1, id);
			ResultSet rs=pstamt.executeQuery();
			if(rs.next()) {
				type=rs.getString("classifyName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return type;
	}
	
	/**
	 * 获取成语收藏总数量
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static int getSaveIdiomCount() {
		int count=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select count(*) from idiom_save";
		try {
			pstamt=conn.prepareStatement(sql);
			ResultSet rs=pstamt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
				System.out.println(count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
}
