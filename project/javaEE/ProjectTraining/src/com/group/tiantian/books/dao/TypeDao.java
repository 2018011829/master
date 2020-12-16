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
	 * 获取TypeDao实例
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
	 * 从数据库中查找所有图书的类型
	 * @return 返回所有图书类型的集合
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
	
	/**
	 * 获取类型总数量
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int getCount() {
		int count=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select count(*) from types";
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
	
	/**
	 * 获取数据库中某页的类型
	 * @return
	 */
	public List<BookType> getTypes(int pageNum,int pageSize){
		List<BookType> types=new ArrayList<>();
		String sql="select * from types limit ?,?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,(pageNum-1)*pageSize);  //从第几条开始
			preparedStatement.setInt(2, pageSize);         //数量
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				BookType type=new BookType();
				type.setId(rs.getInt("id"));
				type.setType(rs.getString("type"));
				types.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return types;
	}
}
