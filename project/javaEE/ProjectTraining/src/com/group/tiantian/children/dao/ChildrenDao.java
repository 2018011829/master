package com.group.tiantian.children.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.Child;
import com.group.tiantian.util.DBUtil;

public class ChildrenDao {
	public static Connection connection;
	public static ChildrenDao childrenDao;
	public static PreparedStatement preparedStatement;
	
	/**
	 * ��ȡChildrenDaoʵ��
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
	 * �����ݿ����һ��������Ϣ
	 * @param name ����
	 * @param grade �꼶
	 * @param sex �Ա�
	 * @return ����Ƿ�ɹ�
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
				System.out.println("�ɹ���Ӻ���");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * ���ݼҳ��绰��ѯ������Ϣ
	 * */
	public List<Child> queryChildrenByPhone(String query) {
		String sql="select * from child where parentPhone=?";
		List<Child> children=new ArrayList<Child>();
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, query);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Child child=new Child();
				child.setId(resultSet.getInt(1));
				child.setName(resultSet.getString(2));
				child.setGrade(resultSet.getString(3));
				child.setSex(resultSet.getString(4));
				child.setParentPhone(resultSet.getString(5));
				children.add(child);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return children;
	}
}
