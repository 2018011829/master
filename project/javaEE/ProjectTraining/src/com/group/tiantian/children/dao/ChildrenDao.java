package com.group.tiantian.children.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.group.tiantian.util.DBUtil;

public class ChildrenDao {
	public static Connection connection;
	public static ChildrenDao childrenDao;
	public static PreparedStatement preparedStatement;
	
	/**
	 * 获取ChildrenDao实例
	 * @return ChildrentDao
	 */
	public static ChildrenDao getInstance() {
		if(null==childrenDao) {
			childrenDao=new ChildrenDao();
		}
		if(null==connection) {
			connection= DBUtil.getConnection();
		}
		return childrenDao;
	}
	
	/**
	 * 向数据库添加一个孩子信息
	 * @param name 姓名
	 * @param grade 年级
	 * @param sex 性别
	 * @return 添加是否成功
	 */
	public boolean addChild(String name,String grade,String sex,String parentPhone) {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("insert into child(cname,grade,sex,parentPhone) values (?,?,?,?)");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, grade);
			preparedStatement.setString(3, sex);
			preparedStatement.setString(4, parentPhone);
			int rows=preparedStatement.executeUpdate();
			if(rows>0) {
				b=true;
				System.out.println("成功添加孩子");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
}
