package com.group.tiantian.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.group.tiantian.entity.Bookshelf;
import com.group.tiantian.util.DBUtil;

public class BookshelfDao {

	public static Connection connection;
	public static PreparedStatement preparedStatement;
	public static BookshelfDao bookshelfDao;
	
	/**
	 * 获取BookshelfDao实例
	 * 
	 * @return BookshelfDao
	 */
	public static BookshelfDao getInstance() {
		if (null == bookshelfDao) {
			bookshelfDao = new BookshelfDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return bookshelfDao;
	}
	
	/**
	 * 从数据库中查找该收藏数据是否存在
	 * @param collection
	 * @return 存在返回true
	 */
	public boolean searchBookFromBookshelf(Bookshelf bookshelf) {
		boolean b=false;
		String sql="select * from bookshelf where phone_num=? and child_name=? and book_name=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, bookshelf.getPhoneNum());
			preparedStatement.setString(2, bookshelf.getChildName());
			preparedStatement.setString(3, bookshelf.getBookName());
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
	 * 将加入书架的数据保存到数据库
	 * @param collection
	 * @return
	 */
	public boolean addBookToBookshelf(Bookshelf bookshelf) {
		boolean b=false;
		String sql="insert into bookshelf(phone_num,child_name,book_name) values(?,?,?)";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, bookshelf.getPhoneNum());
			preparedStatement.setString(2, bookshelf.getChildName());
			preparedStatement.setString(3, bookshelf.getBookName());
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
	 * 将书的数据从数据库的书架中删除
	 * @param collection
	 * @return
	 */
	public boolean deleteBookFromBookshelf(Bookshelf bookshelf) {
		boolean b=false;
		String sql="delete from bookshelf where phone_num=? and child_name=? and book_name=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, bookshelf.getPhoneNum());
			preparedStatement.setString(2, bookshelf.getChildName());
			preparedStatement.setString(3, bookshelf.getBookName());
			int row=preparedStatement.executeUpdate();
			if (row>0) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
}
