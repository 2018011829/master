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
	 * 获取LikeGiveDao实例
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
	 * 通过手机号查询昵称和头像
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 返回个人信息对象
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
	 * 通过说说id和点赞人手机号查询表中是否有这一条记录
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 返回说说对象
	 */
	public boolean selectLikeGive(int momentsId,String likegivePersonPhone) {
		boolean b = false;
		String sql = "select * from moments_likegivePerson where momentsId = ? and likegivePersonPhone = ?";		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
			preparedStatement.setString(2,likegivePersonPhone);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				b = true;
			}else {
				b = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 通过说说id和点赞人手机号查询表是否点赞
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 返回说说对象
	 */
	public int selectLikeGiveboolean(int momentsId,String likegivePersonPhone) {
		int temp = 0;
		String sql = "select likegiveboolen from moments_likegivePerson  where momentsId = ? and likegivePersonPhone = ?";		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
			preparedStatement.setString(2,likegivePersonPhone);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				temp = rs.getInt("likegiveboolen");
			}else {
				temp = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	/**
	 * 将说说id，点赞人昵称，点赞人手机号存入点赞表中
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 返回说说对象
	 */
	public boolean insertLikeGiveInfo(int momentsId,String likegivePersonName,String likegivePersonPhone) {
		boolean b = false;
		//先从表中查询点赞信息，如果有，直接修改likegiveboolen,如果没有，插入数据
		System.out.println(selectLikeGive(momentsId,likegivePersonPhone));
		if(!selectLikeGive(momentsId,likegivePersonPhone)) {
			String sql = "insert into moments_likegivePerson(momentsId,likegivePersonName,likegivePersonPhone,likegiveboolen) values(?,?,?,?)";
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1,momentsId);
				preparedStatement.setString(2,likegivePersonName);
				preparedStatement.setString(3,likegivePersonPhone);
				preparedStatement.setInt(4,1);
				int row=preparedStatement.executeUpdate();
				if(row>0) {
					b=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			String sql = "update moments_likegivePerson set likegiveboolen = ?";
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1,1);
				int row=preparedStatement.executeUpdate();
				if(row>0) {
					b=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return b;
	}
	/**
	 * 通过说说id查询给该说说点赞的人，获得结果集的个数即为点赞人数
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public List<String> likeGiveNames(int momentsId){
		List<String> likeGiveNames = new ArrayList<>();
		String sql="select * from moments_likegivePerson where momentsId = ? and likegiveboolen = ?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
			preparedStatement.setInt(2,1);
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
	 * 通过说说id,点赞人手机号删除该条记录
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public boolean deleteLikeGiveInfo(int momentsId,String likegivePersonPhone) {
		boolean b = false;
		String sql = "delete from moments_likegivePerson where momentsId = ? and likegivePersonPhone = ?";
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
