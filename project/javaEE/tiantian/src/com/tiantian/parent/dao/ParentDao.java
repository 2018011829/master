package com.tiantian.parent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tiantian.util.DBUtil;

public class ParentDao {
	public static Connection connection;
	public static ParentDao parentDao;
	public static PreparedStatement preparedStatement;
	/**
	 * 获取ParentDao实例
	 * @return ParentDao
	 */
	public static ParentDao getInstance() {
		if(null==parentDao) {
			parentDao=new ParentDao();
		}
		if(null==connection) {
			connection=DBUtil.getConnection();
		}
		return parentDao;
	}
	
	
	/**
	 * 向数据库添加一个父母信息
	 * @param phone 电话号码
	 * @param nickname 昵称
	 * @param password 密码
	 * @return 添加是否成功
	 */
	public boolean addParent(String phone,String nickname,String password ) {
		try {
			preparedStatement=connection.prepareStatement("insert into parents('phone','nickname','password') values (?,?,?)");
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, nickname);
			preparedStatement.setString(3, password);
			System.out.println("3.注册成功");
			return preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 判断该手机号码的父母是否存在
	 * @param phone 手机号码
	 * @return 号码是否存在
	 */
	public boolean isExist(String phone) {
		try {
			preparedStatement=connection.prepareStatement("select 1 from parents where phone = ? limit 1");
			preparedStatement.setString(1, phone);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
