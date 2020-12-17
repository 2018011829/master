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
	 * �õ�һ��parentServiceʵ��
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
	 * ע���û�
	 * 
	 * @param phone
	 * @param nickname
	 * @param password
	 * @return true:�ֻ�����ע��ɹ�; false:�ֻ�����ע��ʧ��
	 */
	public boolean resigter(String phone, String nickname, String password) {
		boolean b = false;
		b=parentDao.addParent(phone, nickname, password);
		
		return b;
	}

	/**
	 * �����û����ֻ����Ƿ��Ѿ�ע��
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
	 * �ж��û����������Ƿ�ƥ�� ���û��Ƿ����
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
	 * ����ĳ����ĸ��Ϣ��Json��
	 * @param phone
	 * @return json��
	 */
	public String getOneParentInfo(String phone) {
		return new Gson().toJson(parentDao.selectOneParent(phone));
	}

	public String searchParentsByPhone(String query) {
		return new Gson().toJson(parentDao.queryParentsByPhone(query));
		
	}
	
	/**
	 * �ж��û����������Ƿ�ƥ�� ���û��Ƿ����
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	public ParentMessage selectParentByPhone(String phone) {
		return parentDao.selectParentByPhone(phone);
		
	}
	
	/**
	 *�����ֻ��Ÿ���ָ���ҳ���Ϣ
	 * @return �����Ƿ�ɹ�
	 */
	public Boolean updateParentMessage(String phone,String nickName,String headName) {
		
		return parentDao.updateParentMessage(phone,nickName, headName);
		
	}
	//�õ�������ϵ�˵���Ϣ
	public String getAllContacts(String currentUsername,String usernames) {
		Gson gson=new Gson();
		Type type=new TypeToken<List<String>>(){}.getType();
		List<String> usernamesList=gson.fromJson(usernames, type);
		return gson.toJson(parentDao.selectAllContact(currentUsername,usernamesList));
	}
	//�õ������ҵ��ˣ���������ˣ��Լ�������ʷ
	public String getContactsStatus(String username) {
		List<ContactsStatus> contactsStatusList=parentDao.selectContactsStatus(username);
		return gson.toJson(contactsStatusList);
	}
	
	/**
	 * ͬ������
	 * @param idStr
	 * @return
	 */
	public int agreeInvitation(String idStr) {
		int id=Integer.parseInt(idStr);
		return parentDao.agreeUpdate(id);
		
	}

	/**
	 * �����ݿⱣ��������룬�ȴ���������ͬ��
	 * @param fromPhone
	 * @param toPhone
	 */
	public void storeInvitation(String fromPhone, String toPhone) {
		parentDao.insertInvitaion(fromPhone,toPhone);
	}

	/**
	 * �ܾ�����
	 * @param id
	 * @return
	 */
	public int rejectInvitation(String idStr) {
		int id=Integer.parseInt(idStr);
		return parentDao.rejectUpdate(id);
	}

	/**
	 * �޸�fromPhone��toPhone�ı�ע
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
	 * ��ȡһҳ���û���Ϣ�����ں�̨����ϵͳչʾ
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
		System.out.println("����page�е�count"+count);
		System.out.println("����page�е�list"+list.size());
		System.out.println(list.get(2).getNickname());
		return page;
	}

	public Page<Parent> getPage(int pageNum, int pageSize, String searchInfo) {
		Page<Parent> page=new Page<Parent>(pageNum,pageSize);
		int count=ParentDao.countAll(searchInfo);
		List<Parent> list=ParentDao.selectPage(pageNum,pageSize,searchInfo);
		page.setList(list);
		page.setTotalCount(count);
		System.out.println("����page�е�count"+count);
		System.out.println("����page�е�list"+list.size());
//		System.out.println(list.get(2).getNickname());
		return page;
	}
	
	
}
