package com.group.tiantian.parent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.group.tiantian.entity.Parent;
import com.group.tiantian.util.DBUtil;
public class ParentDao {
	public static Connection connection;
	public static ParentDao parentDao;
	public static PreparedStatement preparedStatement;
	/**
	 * ��ȡParentDaoʵ��
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
	 * �����ݿ����һ����ĸ��Ϣ
	 * @param phone �绰����
	 * @param nickname �ǳ�
	 * @param password ����
	 * @return ����Ƿ�ɹ�
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
				System.out.println("3.ע��ɹ�");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * �жϸ��ֻ�����ĸ�ĸ�Ƿ����
	 * @param phone �ֻ�����
	 * @return �����Ƿ����
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
	 * �ж��û����������Ƿ�ƥ�� ���û��Ƿ����
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
	 * ����ĳ��Parent����Ϣ
	 * @param phone ���ݺ�����Ҹø�ĸ
	 * @return Parent����
	 */
	public Parent selectOneParent(String phone) {
		Parent parent=new Parent();
		String sql="select * from parents where phone=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			parent.setId(resultSet.getInt(1));
			parent.setPhone(resultSet.getString(2));
			parent.setPassword(resultSet.getString(3));
			parent.setNickname(resultSet.getString(4));
			parent.setAvator(resultSet.getString(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parent;
		
	}


	public List<Parent> queryParentsByPhone(String query) {
		String sql="select * from parents where phone=?";
		List<Parent> parents=new ArrayList<Parent>();
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, query);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parents;
	}
}
