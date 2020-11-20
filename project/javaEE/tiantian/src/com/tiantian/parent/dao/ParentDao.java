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
		try {
			preparedStatement=connection.prepareStatement("insert into parents('phone','nickname','password') values (?,?,?)");
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, nickname);
			preparedStatement.setString(3, password);
			System.out.println("3.ע��ɹ�");
			return preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * �жϸ��ֻ�����ĸ�ĸ�Ƿ����
	 * @param phone �ֻ�����
	 * @return �����Ƿ����
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
