package com.group.tiantian.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.BookType;
import com.group.tiantian.util.DBUtil;


public class TypeDao {

	public static Connection connection;
	public static TypeDao typeDao;
	public static PreparedStatement preparedStatement;

	/**
	 * ��ȡTypeDaoʵ��
	 * 
	 * @return TypeDao
	 */
	public static TypeDao getInstance() {
		if (null == typeDao) {
			typeDao = new TypeDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return typeDao;
	}
	
	/**
	 * �����ݿ��в�������ͼ�������
	 * @return ��������ͼ�����͵ļ���
	 */
	public List<BookType> getAllTypes(){
		List<BookType> list=null;
		String sql="select * from types";
		try {
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
				list=new ArrayList<BookType>();
				while(rs.next()) {
					BookType bookType=new BookType();
					bookType.setId(rs.getInt("id"));
					bookType.setType(rs.getString("type"));
					list.add(bookType);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
