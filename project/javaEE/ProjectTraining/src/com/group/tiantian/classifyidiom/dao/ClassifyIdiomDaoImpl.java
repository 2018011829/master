package com.group.tiantian.classifyidiom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.group.tiantian.entity.ClassifyIdiom;
import com.group.tiantian.util.DBUtil;

/**
 * 2020-11-25
 * @author lrf
 */
public class ClassifyIdiomDaoImpl {

	/**
	 * 获取所有分类父菜单和其对应的子菜单
	 * @return
	 */
	public LinkedHashMap<ClassifyIdiom,List<ClassifyIdiom>> findAllClassifyIdiom(){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		LinkedHashMap<ClassifyIdiom,List<ClassifyIdiom>> map = new LinkedHashMap<>();
		conn = DBUtil.getConnection();
		//查询父菜单
		try {
			pstm = conn.prepareStatement("select * from classifyidiom where parentId = 0");
			rs = pstm.executeQuery();
			while(rs.next()) {
				ClassifyIdiom classifyIdiom = new ClassifyIdiom();
				classifyIdiom.setId(rs.getInt(1));
				classifyIdiom.setClassifyName(rs.getString(2));
				//查询子菜单
				pstm = conn.prepareStatement("select * from classifyidiom where parentId = ?");
				pstm.setInt(1, classifyIdiom.getId());
				ResultSet rsChild = pstm.executeQuery();
				List<ClassifyIdiom> list = new ArrayList<>();
				while(rsChild.next()) {
					ClassifyIdiom cIdiom = new ClassifyIdiom();
					cIdiom.setId(rsChild.getInt(1));
					cIdiom.setClassifyName(rsChild.getString(2));
					cIdiom.setParentId(rs.getInt(3));
					list.add(cIdiom);
				}
				map.put(classifyIdiom, list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs,pstm,conn);
		}
		return map;
	}
	
	
}
