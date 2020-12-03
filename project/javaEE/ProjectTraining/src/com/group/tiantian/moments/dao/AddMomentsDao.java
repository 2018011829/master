package com.group.tiantian.moments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.books.dao.BookDao;
import com.group.tiantian.entity.Book;
import com.group.tiantian.util.DBUtil;

public class AddMomentsDao {
	public static Connection connection;
	public static AddMomentsDao addMomentsDao;
	public static PreparedStatement preparedStatement;

	/**
	 * 获取AddMomentsDao实例
	 * 
	 * @return BookDao
	 */
	public static AddMomentsDao getInstance() {
		if (null == addMomentsDao) {
			addMomentsDao = new AddMomentsDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return addMomentsDao;
	}

	/**
	 * 向数据库中插入动态的图片和文字
	 * 
	 * @param start
	 * @param end
	 * @param article
	 * @param content
	 * @return
	 */
	public boolean insertContents(String pictureUrl, String content, int momentsId) {
		boolean b = false;
		String sql = "insert into moments_info(pictureUrl,content,momentsId) values(?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(2,pictureUrl);
			preparedStatement.setString(3,content);
			preparedStatement.setInt(4,momentsId);
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

}
