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
	 * ��ȡBookDaoʵ��
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
	 * �����ݿ����ҵ�6�������͵��鼮
	 * 
	 * @param type �������
	 * @return ����6���������鼮�ļ���
	 */
	public List<Book> getSixBooksByType(String type, String grades) {
		List<Book> list = null;
		String sql = "select * from books where type=? and grades=? limit 6";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, type);
			preparedStatement.setString(2, grades);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs != null) {
				list = new ArrayList<Book>();
				while (rs.next()) {
					Book book = new Book();
					book.setId(rs.getInt("id"));
					book.setName(rs.getString("name"));
					book.setType(rs.getString("type"));
					book.setIntroduce(rs.getString("introduce"));
					book.setImg(rs.getString("img"));
					book.setContent(rs.getString("content"));
					book.setAuthor(rs.getString("author"));
					book.setGrades(rs.getString("grades"));
					list.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * �����ݿ����ҵ������͵������鼮
	 * 
	 * @param type �������
	 * @return ���ظ����������鼮�ļ���
	 */
	public List<Book> getBooksByType(String type, String grades, int bookIndex, int size) {
		List<Book> list = null;
		String sql = "select * from books where type=? and grades=? limit ?,?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, type);
			preparedStatement.setString(2, grades);
			preparedStatement.setInt(3, bookIndex);
			preparedStatement.setInt(4, size);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs != null) {
				list = new ArrayList<Book>();
				while (rs.next()) {
					Book book = new Book();
					book.setId(rs.getInt("id"));
					book.setName(rs.getString("name"));
					book.setType(rs.getString("type"));
					book.setIntroduce(rs.getString("introduce"));
					book.setImg(rs.getString("img"));
					book.setContent(rs.getString("content"));
					book.setAuthor(rs.getString("author"));
					book.setGrades(rs.getString("grades"));
					list.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ����������ȡ�鼮��������Ϣ
	 * @param bookName
	 * @return
	 */
	public Book getBookByName(String bookName) {
		Book book = null;
		String sql = "select * from books where name=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bookName);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs != null) {
				book=new Book();
				while(rs.next()) {
					book.setId(rs.getInt("id"));
					book.setName(rs.getString("name"));
					book.setType(rs.getString("type"));
					book.setIntroduce(rs.getString("introduce"));
					book.setImg(rs.getString("img"));
					book.setContent(rs.getString("content"));
					book.setAuthor(rs.getString("author"));
					book.setGrades(rs.getString("grades"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
}
