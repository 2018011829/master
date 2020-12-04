package com.group.tiantian.moments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.books.dao.BookDao;
import com.group.tiantian.entity.Book;
import com.group.tiantian.entity.moments.Moments;
import com.group.tiantian.entity.moments.MomentsPicture;
import com.group.tiantian.util.DBUtil;

public class AddMomentsDao {
	public static Connection connection;
	public static AddMomentsDao addMomentsDao;
	public static PreparedStatement preparedStatement;

	/**
	 * ��ȡAddMomentsDaoʵ��
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
	 * �����ݿ��в���˵˵��ͼƬ
	 * 
	 * @param start
	 * @param end
	 * @param article
	 * @param content
	 * @return
	 */
	public boolean insertPictures(String pictureName, int momentsId,String time,String personalPhone) {
		boolean b = false;
		String sql = "insert into moments_pictureUrl(momentsPictureUrl,momentsId,time,personalPhone) values(?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,pictureName);
			preparedStatement.setInt(2,momentsId);
			preparedStatement.setString(3,time);
			preparedStatement.setString(4,personalPhone);
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
	 * �����ݿ��в���˵˵������
	 * 
	 * @param start
	 * @param end
	 * @param article
	 * @param content
	 * @return
	 */
	public boolean insertContents(String content, int momentsId,String time,String personalPhone) {
		boolean b = false;
		String sql = "insert into moments_content(momentsContent,momentsId,time,personalPhone) values(?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,content);
			preparedStatement.setInt(2,momentsId);
			preparedStatement.setString(3,time);
			preparedStatement.setString(4,personalPhone);
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
	 * ��һ��˵˵��Ϣ�в���ʱ��͸�����ϢΪ����һ��id
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return ����ɹ� true
	 */
	public boolean insertPersonalInfo(String phoneNumber,String time) {
		boolean b = false;
		String sql = "insert into moments(phoneNumber,moments_time) values(?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,phoneNumber);
			preparedStatement.setString(2,time);
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
	 * ͨ��ʱ����ֻ��Ų�ѯ˵˵id
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return ����һ��˵˵id
	 */
	public int serchMomentsId(String personalPhone,String time) {
		int momentsId = 0;
		String sql="select id from moments where phoneNumber=? and moments_time=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, personalPhone);
			preparedStatement.setString(2, time);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				momentsId = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return momentsId;
	}
	/**
	 * ��ѯ˵˵�б��ȡ����˵˵id
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public List<Moments> getMoments(){
		List<Moments> moments=null;
		String sql="select * from moments";
		try {
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
//				System.out.println(rs.getString("article_name"));
				moments=new ArrayList<>();
				while(rs.next()) {
					Moments moment=new Moments(rs.getInt("id"));
					moments.add(moment);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return moments;
	}
	/**
	 * ����˵˵id��ѯ����˵˵ͼƬ
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public List<MomentsPicture> getMomentsPicture(int momentsId){
		List<MomentsPicture> momentsPictures=null;
		String sql="select * from moments_pictureurl where momentsId=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, momentsId);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
				momentsPictures=new ArrayList<>();
				while(rs.next()) {
					MomentsPicture momentsPicture=new MomentsPicture(rs.getInt("id")
							,rs.getString("momentsPictureUrl")
							,rs.getInt("momentsId")
							,rs.getString("time")
							,rs.getString("personalPhone"));
					momentsPictures.add(momentsPicture);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return momentsPictures;
	}
	
	/**
	 * ͨ��ʱ����ֻ��Ų�ѯ˵˵id
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return ����һ��˵˵id
	 */


}
