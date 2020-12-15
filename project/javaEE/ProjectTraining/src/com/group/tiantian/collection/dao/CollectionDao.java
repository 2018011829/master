package com.group.tiantian.collection.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.Collection;
import com.group.tiantian.util.DBUtil;

public class CollectionDao {

	public static Connection connection;
	public static PreparedStatement preparedStatement;
	public static CollectionDao collectionDao;
	
	/**
	 * ��ȡCollectionDaoʵ��
	 * 
	 * @return CollectionDao
	 */
	public static CollectionDao getInstance() {
		if (null == collectionDao) {
			collectionDao = new CollectionDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return collectionDao;
	}
	
	/**
	 * �����ݿ��в��Ҹ��ղ������Ƿ����
	 * @param collection
	 * @return ���ڷ���true
	 */
	public boolean searchCollection(Collection collection) {
		boolean b=false;
		String sql="select * from collections where phone_num=? and child_name=? and collection_type=? and collection_content=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, collection.getPhoneNum());
			preparedStatement.setString(2, collection.getChildName());
			preparedStatement.setString(3, collection.getCollectionType());
			preparedStatement.setString(4, collection.getCollectionContent());
			ResultSet rs=preparedStatement.executeQuery();
			if (!rs.next()) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * ���ղص����ݱ��浽���ݿ�
	 * @param collection
	 * @return
	 */
	public boolean addCollection(Collection collection) {
		boolean b=false;
		String sql="insert into collections(phone_num,child_name,collection_type,collection_content) values(?,?,?,?)";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, collection.getPhoneNum());
			preparedStatement.setString(2, collection.getChildName());
			preparedStatement.setString(3, collection.getCollectionType());
			preparedStatement.setString(4, collection.getCollectionContent());
			int row=preparedStatement.executeUpdate();
			if (row>0) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * ���ղص����ݴ����ݿ�ɾ��
	 * @param collection
	 * @return
	 */
	public boolean deleteCollection(Collection collection) {
		boolean b=false;
		String sql="delete from collections where phone_num=? and child_name=? and collection_type=? and collection_content=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			String phoneNum=collection.getPhoneNum();
			System.out.println(phoneNum);
			preparedStatement.setString(1, phoneNum);
			preparedStatement.setString(2, collection.getChildName());
			preparedStatement.setString(3, collection.getCollectionType());
			preparedStatement.setString(4, collection.getCollectionContent());
			int row=preparedStatement.executeUpdate();
			if (row>0) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * �����ݿ��в������ղص�ͼ������
	 * @return collection_content
	 */
	public List<String> searchBookFromCollection(String phone,String cname,String type) {
		List<String> bookNames = new ArrayList<>();
		String sql="select collection_content from collections where phone_num=? and child_name=? and collection_type=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, cname);
			preparedStatement.setString(3, type);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				System.out.println("getbookName:"+rs.getString(1));
				bookNames.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bookNames;
	}
}
