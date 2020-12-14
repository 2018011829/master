package com.group.tiantian.moments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.moments.Comment;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.util.ConfigUtil;
import com.group.tiantian.util.DBUtil;

public class CommentsDao {
	public static Connection connection;
	public static CommentsDao commentsDao;
	public static PreparedStatement preparedStatement;
	/**
	 * 获取CommentsDao实例
	 * 
	 * @return BookDao
	 */
	public static CommentsDao getInstance() {
		if (null == commentsDao) {
			commentsDao = new CommentsDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return commentsDao;
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
	 * 向数据库中插入说说id,评论内容和评论人的手机号,评论人昵称,评论人头像
	 * 
	 * @param start
	 * @param end
	 * @param article
	 * @param content
	 * @return
	 */
	public boolean insertMoments(int momentsId,String comments, String personPhone,String PersonName,String PersonHead) {
		boolean b = false;
		String sql = "insert into moments_comments(momentsId,comments,PersonPhone,PersonName,PersonHead) values(?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
			preparedStatement.setString(2,comments);
			preparedStatement.setString(3,personPhone);
			preparedStatement.setString(4,PersonName);
			preparedStatement.setString(5,PersonHead);
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
	public List<Comment> commentsInfo(int momentsId){
		List<Comment> commentsInfos = new ArrayList<>();
		String sql="select * from moments_comments where momentsId = ?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Comment comment = new Comment(rs.getInt("id"),
							rs.getInt("momentsId"),
							rs.getString("comments"),
							rs.getString("PersonPhone"),
							rs.getString("PersonName"),
							ConfigUtil.SERVICE_ADDRESS + "avatar/"+rs.getString("PersonHead")
							);
					commentsInfos.add(comment);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return commentsInfos;
	}

}
