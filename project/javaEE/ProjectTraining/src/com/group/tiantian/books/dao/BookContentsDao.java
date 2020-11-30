package com.group.tiantian.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.Content;
import com.group.tiantian.util.DBUtil;


public class BookContentsDao {

	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static BookContentsDao bookContentsDao;

	/**
	 * 获取BookContentsDao实例
	 * 
	 * @return BookContentsDao
	 */
	public static BookContentsDao getInstance() {
		if (null == bookContentsDao) {
			bookContentsDao = new BookContentsDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return bookContentsDao;
	}

	/**
	 * 向数据库中插入某本书的目录数据
	 * 
	 * @param start
	 * @param end
	 * @param article
	 * @param content
	 * @return
	 */
	public boolean insertContents(double id, int start, String articleName, String contentName) {
		boolean b = false;
		String sql = "insert into books_contents(id,start,article_name,content_name) values(?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, id);
			preparedStatement.setInt(2, start);
			preparedStatement.setString(3, articleName);
			preparedStatement.setString(4, contentName);
			int row=preparedStatement.executeUpdate();
			if(row>0) {
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 从数据库中查找某本书的所有目录
	 * @param articleName
	 * @return
	 */
	public List<Content> getBookContents(String articleName){
		List<Content> contents=null;
		String sql="select * from books_contents where article_name=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, articleName);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
//				System.out.println(rs.getString("article_name"));
				contents=new ArrayList<>();
				while(rs.next()) {
					Content content=new Content(rs.getString("article_name"),
							rs.getString("content_name"), rs.getInt("start"));
					contents.add(content);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return contents;
	}
}
