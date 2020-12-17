package com.group.tiantian.parent.service;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.group.tiantian.entity.ContactsStatus;
import com.group.tiantian.entity.Parent;
import com.group.tiantian.entity.ParentMessage;
import com.group.tiantian.parent.dao.ParentDao;
import com.group.tiantian.util.Page;



public class ParentService {
	private static ParentService parentService;
	private static ParentDao parentDao;
	private static Gson gson;

	/**
	 * 得到一个parentService实例
	 * 
	 * @return
	 */
	public static ParentService getInstance() {
		if (null == parentService) {
			parentService = new ParentService();
		}
		if (null == parentDao) {
			parentDao = ParentDao.getInstance();
		}
		if(null==gson) {
			gson=new Gson();
		}
		return parentService;
	}

	/**
	 * 注册用户
	 * 
	 * @param phone
	 * @param nickname
	 * @param password
	 * @return true:手机号码注册成功; false:手机号码注册失败
	 */
	public boolean resigter(String phone, String nickname, String password) {
		boolean b = false;
		b=parentDao.addParent(phone, nickname, password);
		
		return b;
	}

	/**
	 * 查找用户的手机号是否已经注册
	 * 
	 * @param phone
	 * @return
	 */
	public boolean isExistPhone(String phone) {
		boolean b = false;
		if (parentDao.isExist(phone)) {
			b = true;
		}
		return b;
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
		if (parentDao.isExistUser(phone, password)) {
			b = true;
		}
		return b;
	}
	
	/**
	 * 返回某个父母信息的Json串
	 * @param phone
	 * @return json串
	 */
	public String getOneParentInfo(String phone) {
		return new Gson().toJson(parentDao.selectOneParent(phone));
	}

	public String searchParentsByPhone(String query) {
		return new Gson().toJson(parentDao.queryParentsByPhone(query));
		
	}
	
	/**
	 * 判断用户名和密码是否匹配 即用户是否存在
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	public ParentMessage selectParentByPhone(String phone) {
		return parentDao.selectParentByPhone(phone);
		
	}
	
	/**
	 *根据手机号更新指定家长信息
	 * @return 更新是否成功
	 */
	public Boolean updateParentMessage(String phone,String nickName,String headName) {
		
		return parentDao.updateParentMessage(phone,nickName, headName);
		
	}
	//得到所有联系人的信息
	public String getAllContacts(String currentUsername,String usernames) {
		Gson gson=new Gson();
		Type type=new TypeToken<List<String>>(){}.getType();
		List<String> usernamesList=gson.fromJson(usernames, type);
		return gson.toJson(parentDao.selectAllContact(currentUsername,usernamesList));
	}
	//得到邀请我的人，我邀请的人，以及邀请历史
	public String getContactsStatus(String username) {
		List<ContactsStatus> contactsStatusList=parentDao.selectContactsStatus(username);
		return gson.toJson(contactsStatusList);
	}
	
	/**
	 * 同意邀请
	 * @param idStr
	 * @return
	 */
	public int agreeInvitation(String idStr) {
		int id=Integer.parseInt(idStr);
		return parentDao.agreeUpdate(id);
		
	}

	/**
	 * 在数据库保存这个邀请，等待被邀请人同意
	 * @param fromPhone
	 * @param toPhone
	 */
	public void storeInvitation(String fromPhone, String toPhone) {
		parentDao.insertInvitaion(fromPhone,toPhone);
	}

	/**
	 * 拒绝邀请
	 * @param id
	 * @return
	 */
	public int rejectInvitation(String idStr) {
		int id=Integer.parseInt(idStr);
		return parentDao.rejectUpdate(id);
	}

	/**
	 * 修改fromPhone对toPhone的备注
	 * @param fromPhone
	 * @param toPhone
	 * @param remark
	 */
	public int changeRemark(String fromPhone, String toPhone, String remark) {
		// TODO Auto-generated method stub
		return parentDao.updateRemark(fromPhone,toPhone,remark);
	}

	public void addRemarks(String fromPhone, String fromPhoneNickname, String toPhone, String toPhoneNickname) {
		ParentDao.insertRemarks(fromPhone,fromPhoneNickname,toPhone,toPhoneNickname);
	}

	public void deleteRemarks(String fromPhone, String toPhone) {
		// TODO Auto-generated method stub
		ParentDao.deleteRemarks(fromPhone,toPhone);
		
	}

	/**
	 * 获取一页的用户信息，用于后台管理系统展示
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<Parent> getPage(int pageNum, int pageSize) {
		Page<Parent> page=new Page<Parent>(pageNum,pageSize);
		int count=ParentDao.countAll();
		List<Parent> list=ParentDao.selectPage(pageNum,pageSize);
		page.setList(list);
		page.setTotalCount(count);
		System.out.println("测试page中的count"+count);
		System.out.println("测试page中的list"+list.size());
		System.out.println(list.get(2).getNickname());
		return page;
	}

	public Page<Parent> getPage(int pageNum, int pageSize, String searchInfo) {
		Page<Parent> page=new Page<Parent>(pageNum,pageSize);
		int count=ParentDao.countAll(searchInfo);
		List<Parent> list=ParentDao.selectPage(pageNum,pageSize,searchInfo);
		page.setList(list);
		page.setTotalCount(count);
		System.out.println("测试page中的count"+count);
		System.out.println("测试page中的list"+list.size());
//		System.out.println(list.get(2).getNickname());
		return page;
	}
	
	
}
