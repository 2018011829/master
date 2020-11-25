package com.group.tiantian.parent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.Parent;
import com.group.tiantian.util.DBUtil;

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
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("insert into parents(phone,nickname,password) values (?,?,?)");
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, nickname);
			preparedStatement.setString(3, password);
			int rows=preparedStatement.executeUpdate();
			if(rows>0) {
				b=true;
				System.out.println("3.注册成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 判断该手机号码的父母是否存在
	 * @param phone 手机号码
	 * @return 号码是否存在
	 */
	public boolean isExist(String phone) {
		String sql="select * from parents where phone = ?";
		try {
			preparedStatement=connection.prepareStatement(sql);
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
	
	/**
	 * 判断用户名和密码是否匹配 即用户是否存在
	 * @param phone
	 * @param password
	 * @return
	 */
	public boolean isExistUser(String phone,String password) {
		boolean b=false;
		String sql="select * from parents where phone = ? and password = ?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2,password);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 *查询所有Parent的信息
	 * @return Parent对象的列表
	 */
	public List<Parent> selectAllParents(){
		List<Parent> parents=new ArrayList<Parent>();
		String sql="select * from parents";
		try {
			preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Parent parent=new Parent();
				parent.setId(resultSet.getInt(1));
				parent.setPhone(resultSet.getString(2));
				parent.setPassword(resultSet.getString(3));
				parent.setNickname(resultSet.getString(4));
				parent.setAvator(resultSet.getString(5));
				parents.add(parent);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parents;
	}
}
