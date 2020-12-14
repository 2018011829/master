package com.group.tiantian.moments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.moments.Comment;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.entity.moments.ReplyContent;
import com.group.tiantian.util.ConfigUtil;
import com.group.tiantian.util.DBUtil;

public class ReplyContentDao {
	public static Connection connection;
	public static ReplyContentDao replyContentDao;
	public static PreparedStatement preparedStatement;
	/**
	 * 获取ReplyContentDao实例
	 * 
	 * @return
	 */
	public static ReplyContentDao getInstance() {
		if (null == replyContentDao) {
			replyContentDao = new ReplyContentDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return replyContentDao;
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
	 * 向数据库中插入说说id,回复内容和回复人的手机号,回复人昵称,回复人头像
	 * 
	 * @param start
	 * @param end
	 * @param article
	 * @param content
	 * @return
	 */
	public boolean insertreplyContent(int momentsId,int position,String replyContent, String personPhone,String PersonName,String PersonHead) {
		boolean b = false;
		String sql = "insert into moments_reply(momentsId,position,replyContent,PersonPhone,PersonName,PersonHead) values(?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
			preparedStatement.setInt(2,position);
			preparedStatement.setString(3,replyContent);
			preparedStatement.setString(4,personPhone);
			preparedStatement.setString(5,PersonName);
			preparedStatement.setString(6,PersonHead);
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
	 * 通过说说id查询给该说说评论的人的昵称和评论内容
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public List<ReplyContent> replyContentInfo(int momentsId){
		List<ReplyContent> replyContentInfos = new ArrayList<>();
		String sql="select * from moments_reply where momentsId = ?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					ReplyContent replyContentInfo = new ReplyContent(rs.getInt("momentsId"),
							rs.getInt("position"),
							rs.getString("replyContent"),
							rs.getString("PersonPhone"),
							rs.getString("PersonName"),
							ConfigUtil.SERVICE_ADDRESS + "avatar/"+rs.getString("PersonHead")
							);
					replyContentInfos.add(replyContentInfo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return replyContentInfos;
	}
}
