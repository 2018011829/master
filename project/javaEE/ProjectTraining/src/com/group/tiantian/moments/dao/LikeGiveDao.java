package com.group.tiantian.moments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.moments.LikeGiveInfo;
import com.group.tiantian.entity.moments.Moments;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.util.DBUtil;

public class LikeGiveDao {
	public static Connection connection;
	public static LikeGiveDao likeGiveDao;
	public static PreparedStatement preparedStatement;
	
	/**
	 * ��ȡLikeGiveDaoʵ��
	 * 
	 * @return 
	 */
	public static LikeGiveDao getInstance() {
		if (null == likeGiveDao) {
			likeGiveDao = new LikeGiveDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return likeGiveDao;
	}
	/**
	 * ͨ���ֻ��Ų�ѯ�ǳƺ�ͷ��
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return ���ظ�����Ϣ����
	 */
	public PersonalInfo getPersonalInfo(String phoneNum){
		PersonalInfo personalInfo=null;
		String sql="select nickname,avatar from parents where phone=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,phoneNum);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
				if(rs.next()) {
					personalInfo= new PersonalInfo(rs.getString("nickname"),rs.getString("avatar"));	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personalInfo;
	}
	/**
	 * ��˵˵id���������ǳƣ��������ֻ��Ŵ�����ޱ��У���������ͨ��˵˵id��ѯ�����˵ĸ�����д����ޱ�
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return ����˵˵����
	 */
	public boolean insertLikeGiveInfo(int momentsId,String likegivePersonName,String likegivePersonPhone) {
		boolean b = false;
		String sql = "insert into moments_likegivePerson(momentsId,likegivePersonName,likegivePersonPhone) values(?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
			preparedStatement.setString(2,likegivePersonName);
			preparedStatement.setString(3,likegivePersonPhone);
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
	 * ͨ��˵˵id��ѯ����˵˵���޵��ˣ���ý�����ĸ�����Ϊ��������
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public List<String> likeGiveNames(int momentsId){
		List<String> likeGiveNames = new ArrayList<>();
		String sql="select * from moments_likegivePerson";
		try {
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					String likeGiveName = rs.getString("likegivePersonName");
					likeGiveNames.add(likeGiveName);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return likeGiveNames;
	}
	/**
	 * ͨ��˵˵id,�������ֻ���ɾ��������¼
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public boolean deleteLikeGiveInfo(int momentsId,String likegivePersonPhone) {
		boolean b = false;
		String sql = "delete from moments_likegiveperson where momentsId = ? and likegivePersonPhone = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
			preparedStatement.setString(2,likegivePersonPhone);
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
