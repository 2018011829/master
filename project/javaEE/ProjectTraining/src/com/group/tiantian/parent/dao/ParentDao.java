package com.group.tiantian.parent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.group.tiantian.entity.ContactsStatus;
import com.group.tiantian.entity.Parent;
import com.group.tiantian.entity.ParentMessage;
import com.group.tiantian.util.DBUtil;

public class ParentDao {
	public static Connection connection;
	public static ParentDao parentDao;
	public static PreparedStatement preparedStatement;

	/**
	 * 获取ParentDao实例
	 * 
	 * @return ParentDao
	 */
	public static ParentDao getInstance() {
		if (null == parentDao) {
			parentDao = new ParentDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return parentDao;
	}

	/**
	 * 向数据库添加一个父母信息
	 * 
	 * @param phone    电话号码
	 * @param nickname 昵称
	 * @param password 密码
	 * @return 添加是否成功
	 */
	public boolean addParent(String phone, String nickname, String password) {
		boolean b = false;
		try {
			preparedStatement = connection
					.prepareStatement("insert into parents(phone,nickname,password) values (?,?,?)");
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, nickname);
			preparedStatement.setString(3, password);
			int rows = preparedStatement.executeUpdate();
			if (rows > 0) {
				b = true;
				System.out.println("3.注册成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 判断该手机号码的父母是否存在
	 * 
	 * @param phone 手机号码
	 * @return 号码是否存在
	 */
	public boolean isExist(String phone) {
		String sql = "select * from parents where phone = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断用户名和密码是否匹配 即用户是否存在
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	public boolean isExistUser(String phone, String password) {
		boolean b = false;
		String sql = "select * from parents where phone = ? and password = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return b;
	}

	/**
	 * 返回某个Parent的信息
	 * 
	 * @param phone 根据号码查找该父母
	 * @return Parent对象
	 */
	public Parent selectOneParent(String phone) {
		Parent parent = new Parent();
		String sql = "select * from parents where phone=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			parent.setId(resultSet.getInt(1));
			parent.setPhone(resultSet.getString(2));
			parent.setPassword(resultSet.getString(3));
			parent.setNickname(resultSet.getString(4));
			parent.setAvator(resultSet.getString(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parent;

	}

	public List<Parent> queryParentsByPhone(String query) {
		String sql = "select * from parents where phone=?";
		List<Parent> parents = new ArrayList<Parent>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Parent parent = new Parent();
				parent.setId(resultSet.getInt(1));
				parent.setPhone(resultSet.getString(2));
				parent.setPassword(resultSet.getString(3));
				parent.setNickname(resultSet.getString(4));
				parent.setAvator(resultSet.getString(5));
				parents.add(parent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parents;
	}

	/**
	 * 查询指定Parent的信息
	 * 
	 * @return Parent对象
	 */
	public ParentMessage selectParentByPhone(String phone) {
		ParentMessage parentMessage = new ParentMessage();
		String sql = "select * from parentmessage where phone = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				parentMessage.setId(resultSet.getInt(1));
				parentMessage.setHeadPortrait(resultSet.getString(2));
				parentMessage.setPhone(resultSet.getString(3));
				parentMessage.setNickName(resultSet.getString(4));
				parentMessage.setSex(resultSet.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parentMessage;
	}

	/**
	 * 根据手机号更新指定家长信息
	 * 
	 * @return 更新是否成功
	 */
	public Boolean updateParentMessage(String phone,String nickName,String headName) {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("update parents set avatar=?,nickname=? where phone=?");
			preparedStatement.setString(1, headName);
			preparedStatement.setString(2, nickName);
			preparedStatement.setString(3, phone);
			int rows=preparedStatement.executeUpdate();
			if(rows>0) {
				b=true;
				System.out.println("更新成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;

	}

	public List<Parent> selectAllContact(List<String> usernamesList) {
		List<Parent> parents=new ArrayList<Parent>();
		try {
			preparedStatement = connection.prepareStatement("select * from parents where phone=?");
			for (String username : usernamesList) {
				preparedStatement.setString(1, username);
				ResultSet resultSet=preparedStatement.executeQuery();
				resultSet.next();
				Parent parent = new Parent();
				parent.setId(resultSet.getInt(1));
				parent.setPhone(resultSet.getString(2));
				parent.setPassword(resultSet.getString(3));
				parent.setNickname(resultSet.getString(4));
				parent.setAvator(resultSet.getString(5));
				parents.add(parent);	
			}
			//开始自定义比较器
	        Collections.sort(parents, new Comparator<Parent>() {  
	            @Override  
	            public int compare(Parent o1, Parent o2) {  
	               //  重要部分
	                Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);  
	                return com.compare(o1.getNickname(), o2.getNickname());  

	            }  
	        }); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parents;
	}

	public List<ContactsStatus> selectContactsStatus(String username) {
		List<ContactsStatus> contactsStatusList=new ArrayList<ContactsStatus>();
		//首先查询我邀请的人
		String sqlSelectIInvited="select parents.*,contacts_status.id,contacts_status from parents,contacts_status\r\n" + 
				"where contacts_status.from_phone=?\r\n" + 
				"and contacts_status.to_phone=parents.phone";
		try {
			preparedStatement=connection.prepareStatement(sqlSelectIInvited);
			preparedStatement.setString(1, username);
			System.out.println("正在查看"+username+"的好友状态");
			ResultSet resultSet1=preparedStatement.executeQuery();
			while(resultSet1.next()) {
				ContactsStatus contactsStatus=new ContactsStatus();
				contactsStatus.setFrom(null);
				Parent to=new Parent();
				to.setId(resultSet1.getInt(1));
				to.setPhone(resultSet1.getString(2));
				to.setPassword(resultSet1.getString(3));
				to.setNickname(resultSet1.getString(4));
				to.setAvator(resultSet1.getString(5));
				contactsStatus.setTo(to);
				contactsStatus.setId(resultSet1.getInt(6));
				contactsStatus.setStatus(resultSet1.getInt(7));
				contactsStatusList.add(contactsStatus);
			}
			//再查询邀请我的人
			String sqlSelectInvitedMe="select parents.*,contacts_status.id,contacts_status from parents,contacts_status\r\n" + 
					"where contacts_status.to_phone=?\r\n" + 
					"and contacts_status.from_phone=parents.phone";
			preparedStatement=connection.prepareStatement(sqlSelectInvitedMe);
			preparedStatement.setString(1, username);
			ResultSet resultSet2=preparedStatement.executeQuery();
			while(resultSet2.next()) {
				ContactsStatus contactsStatus=new ContactsStatus();
				Parent from=new Parent();
				from.setId(resultSet2.getInt(1));
				from.setPhone(resultSet2.getString(2));
				from.setPassword(resultSet2.getString(3));
				from.setNickname(resultSet2.getString(4));
				from.setAvator(resultSet2.getString(5));
				contactsStatus.setFrom(from);;
				contactsStatus.setTo(null);
				contactsStatus.setId(resultSet2.getInt(6));
				contactsStatus.setStatus(resultSet2.getInt(7));
				contactsStatusList.add(contactsStatus);		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//进行按照id，同时也是时间进行排序
        Collections.sort(contactsStatusList, new Comparator<ContactsStatus>() {  
            @Override  
            public int compare(ContactsStatus o1, ContactsStatus o2) {
				return o2.getId()-o1.getId();  
        

            }  
        }); 
		System.out.println("我邀请的人，邀请我的人"+contactsStatusList);
		return contactsStatusList;
	}

	/**
	 * 同意邀请，将该id的邀请元组的contacts_status字段设置为1
	 * @param id
	 * @return
	 */
	public int agreeUpdate(int id) {
		int isSuccess=0;
		String sql="update contacts_status set contacts_status=1 where id=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			isSuccess=preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}

	//在contacts_status表中插入一条数据
	public void insertInvitaion(String fromPhone, String toPhone) {
		String sql="insert into contacts_status(from_phone,to_phone) values (?,?)";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, fromPhone);
			preparedStatement.setString(2, toPhone);
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 拒绝邀请
	 * @param id
	 * @return
	 */
	public int rejectUpdate(int id) {
		int isSuccess=0;
		String sql="update contacts_status set contacts_status=2 where id=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			isSuccess=preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}
}
