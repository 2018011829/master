package com.tiantian.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @clsName DBUtil
 * @description ���ݿ⹤���࣬���ڴ򿪻�ر�����
 * @author shenyayu
 *
 */
public class DBUtil {
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ���ݿ�����
	 * @return ���ݿ����Ӷ���
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tiantian?useUnicode=true&characterEncoding=utf-8", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * �ر����ݿ�����
	 * @param rs
	 * @param pstm
	 * @param con
	 */
	public static void close(ResultSet rs, PreparedStatement pstm, Connection con) {
		try {
			if(rs!=null)
				rs.close();
			if(pstm!=null)
				pstm.close();
			if(con!=null)
				con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
