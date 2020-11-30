package com.group.tiantian.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.group.tiantian.util.DBUtil;


public class ReadBookDao {

	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ReadBookDao readBookDao;

	/**
	 * »ñÈ¡BookContentsDaoÊµÀý
	 * 
	 * @return BookContentsDao
	 */
	public static ReadBookDao getInstance() {
		if (null == readBookDao) {
			readBookDao = new ReadBookDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return readBookDao;
	}

}
