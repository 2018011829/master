package com.group.tiantian.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.Book;
import com.group.tiantian.util.DBUtil;


public class BookDao {
	public static Connection connection;
	public static BookDao bookDao;
	public static PreparedStatement preparedStatement;

	/**
	 * 获取BookDao实例
	 * 
	 * @return BookDao
	 */
	public static BookDao getInstance() {
		if (null == bookDao) {
			bookDao = new BookDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return bookDao;
	}

	/**
	 * 从数据库中找到6本该类型的书籍
	 * 
	 * @param type 书的类型
	 * @return 返回6本该类型书籍的集合
	 */
	public List<Book> getSixBooksByType(String type) {
		List<Book> list = null;
		String sql = "select * from books where type=? limit 6";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, type);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs != null) {
				list=new ArrayList<Book>();
				while (rs.next()) {
					Book book=new Book();
					book.setId(rs.getInt("id"));
					book.setName(rs.getString("name"));
					book.setType(rs.getString("type"));
					book.setIntroduce(rs.getString("introduce"));
					book.setImg(rs.getString("img"));
					book.setContent(rs.getString("content"));
					book.setAuthor(rs.getString("author"));
					list.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 从数据库中找到该类型的所有书籍
	 * 
	 * @param type 书的类型
	 * @return 返回该类型所有书籍的集合
	 */
	public List<Book> getBooksByType(String type) {
		List<Book> list = null;
		String sql = "select * from books where type=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, type);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs != null) {
				list=new ArrayList<Book>();
				while (rs.next()) {
					Book book=new Book();
					book.setId(rs.getInt("id"));
					book.setName(rs.getString("name"));
					book.setType(rs.getString("type"));
					book.setIntroduce(rs.getString("introduce"));
					book.setImg(rs.getString("img"));
					book.setContent(rs.getString("content"));
					book.setAuthor(rs.getString("author"));
					list.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
