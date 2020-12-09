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
	 * ��ȡParentDaoʵ��
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
	 * �����ݿ����һ����ĸ��Ϣ
	 * 
	 * @param phone    �绰����
	 * @param nickname �ǳ�
	 * @param password ����
	 * @return ����Ƿ�ɹ�
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
				System.out.println("3.ע��ɹ�");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * �жϸ��ֻ�����ĸ�ĸ�Ƿ����
	 * 
	 * @param phone �ֻ�����
	 * @return �����Ƿ����
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
	 * �ж��û����������Ƿ�ƥ�� ���û��Ƿ����
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
	 * ����ĳ��Parent����Ϣ
	 * 
	 * @param phone ���ݺ�����Ҹø�ĸ
	 * @return Parent����
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
	 * ��ѯָ��Parent����Ϣ
	 * 
	 * @return Parent����
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
	 * �����ֻ��Ÿ���ָ���ҳ���Ϣ
	 * 
	 * @return �����Ƿ�ɹ�
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
				System.out.println("���³ɹ�");
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
			//��ʼ�Զ���Ƚ���
	        Collections.sort(parents, new Comparator<Parent>() {  
	            @Override  
	            public int compare(Parent o1, Parent o2) {  
	               //  ��Ҫ����
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
		//���Ȳ�ѯ���������
		String sqlSelectIInvited="select parents.*,contacts_status.id,contacts_status from parents,contacts_status\r\n" + 
				"where contacts_status.from_phone=?\r\n" + 
				"and contacts_status.to_phone=parents.phone";
		try {
			preparedStatement=connection.prepareStatement(sqlSelectIInvited);
			preparedStatement.setString(1, username);
			System.out.println("���ڲ鿴"+username+"�ĺ���״̬");
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
			//�ٲ�ѯ�����ҵ���
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
		//���а���id��ͬʱҲ��ʱ���������
        Collections.sort(contactsStatusList, new Comparator<ContactsStatus>() {  
            @Override  
            public int compare(ContactsStatus o1, ContactsStatus o2) {
				return o2.getId()-o1.getId();  
        

            }  
        }); 
		System.out.println("��������ˣ������ҵ���"+contactsStatusList);
		return contactsStatusList;
	}

	/**
	 * ͬ�����룬����id������Ԫ���contacts_status�ֶ�����Ϊ1
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

	//��contacts_status���в���һ������
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
	 * �ܾ�����
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
