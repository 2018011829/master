package com.group.tiantian.moments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.books.dao.BookDao;
import com.group.tiantian.entity.moments.Moments;
import com.group.tiantian.entity.moments.MomentsPicture;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.util.DBUtil;

public class MomentsDao {
	public static Connection connection;
	public static MomentsDao addMomentsDao;
	public static PreparedStatement preparedStatement;

	/**
	 * 获取AddMomentsDao实例
	 * 
	 * @return BookDao
	 */
	public static MomentsDao getInstance() {
		if (null == addMomentsDao) {
			addMomentsDao = new MomentsDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return addMomentsDao;
	}

	/**
	 * 向数据库中插入说说的图片
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
	 * 向数据库中插入说说的文字
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
	 * 向一条说说信息中插入时间和个人信息为生成一个id
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 插入成功 true
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
	 * 通过时间和手机号查询说说id
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 返回一个说说id
	 */
	public int serchMomentsId(String personalPhone,String time) {
		int momentsId = 0;
		String sql="select id from moments where phoneNumber=? and moments_time=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, personalPhone);
			preparedStatement.setString(2, time);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
				if(rs.next()) {
					momentsId = rs.getInt("id");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return momentsId;
	}
	/**
	 * 查询说说列表获取所有说说信息
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
				moments=new ArrayList<>();
				while(rs.next()) {
					Moments moment=new Moments(rs.getInt("id"),rs.getString("phoneNumber"),rs.getString("moments_time"));
					moments.add(moment);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return moments;
	}
	/**
	 * 根据说说id查询所有说说图片
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
	 * 根据说说id查询所有说说文案
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public String getMomentsContent(int momentsId){
		String momentsContent=null;
		String sql="select momentsContent from moments_content where momentsId=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, momentsId);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
				if(rs.next()) {
					momentsContent=rs.getString("momentsContent");	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return momentsContent;
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
	 * 通过说说id查询手机号
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 返回一个说说id
	 */
	public Moments getMomentsInfo(int momentsId){
		Moments moment=null;
		String sql="select * from moments where id=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
				if(rs.next()) {
					moment= new Moments(rs.getInt("id"),rs.getString("phoneNumber"),rs.getString("moments_time"));	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return moment;
	}
	
	/**
	 * 通过手机号查询说说列表获取说说信息
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public List<Moments> getMomentsByPhone(String personPhone){
		List<Moments> moments=null;
		String sql="select * from moments where phoneNumber = ?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,personPhone);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
				moments=new ArrayList<>();
				while(rs.next()) {
					Moments moment=new Moments(rs.getInt("id"),rs.getString("phoneNumber"),rs.getString("moments_time"));
					moments.add(moment);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return moments;
	}
	/**
	 * 通过说说id,删除该条说说信息
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public boolean deleteMoment(int momentsId) {
		boolean b = false;
		String sql = "delete from moments where id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
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
	 * 通过说说id,删除该条说说圖片
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public boolean deleteMomentPictuire(int momentsId) {
		boolean b = false;
		String sql = "delete from moments_pictureurl where momentsId = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
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
	 * 通过说说id,删除该条说说文案
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public boolean deleteMomentContent(int momentsId) {
		boolean b = false;
		String sql = "delete from moments_content where momentsId = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
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
	 * 通过说说id,删除该条说说评论
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public boolean deleteMomentComment(int momentsId) {
		boolean b = false;
		String sql = "delete from moments_comments where momentsId = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
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
	 * 通过说说id,删除该条说说回复
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public boolean deleteMomentReply(int momentsId) {
		boolean b = false;
		String sql = "delete from moments_reply where momentsId = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
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
	 * 通过说说id,删除该条说说点赞
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public boolean deleteMomentLikeGive(int momentsId) {
		boolean b = false;
		String sql = "delete from moments_likegiveperson where momentsId = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,momentsId);
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
