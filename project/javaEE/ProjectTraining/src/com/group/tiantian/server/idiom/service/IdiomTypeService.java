package com.group.tiantian.server.idiom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import com.group.tiantian.classifyidiom.dao.ClassifyIdiomDaoImpl;
import com.group.tiantian.server.entity.Idiom;
import com.group.tiantian.server.entity.IdiomType;
import com.group.tiantian.server.entity.Page;
import com.group.tiantian.server.idiom.dao.IdiomDao;
import com.group.tiantian.util.DBUtil;

public class IdiomTypeService {

	private static IdiomTypeService bookTypeService;
	
	/**
	 * �õ�һ��IdiomTypeServiceʵ��
	 * 
	 * @return
	 */
	public static IdiomTypeService getInstance() {
		if (null == bookTypeService) {
			bookTypeService = new IdiomTypeService();
		}
		return bookTypeService;
	}
	
	/**
	 * �����ݿ��в�������ͼ�������
	 * @return ��������ͼ�����͵ļ���
	 */
	public List<IdiomType> getAllTypes(){
		List<IdiomType> list=null;
		ClassifyIdiomDaoImpl classifyIdiomDaoImpl=new ClassifyIdiomDaoImpl();
		LinkedHashMap<String, List<String>> link=classifyIdiomDaoImpl.findAllIdiomType();
		if(link!=null && link.size()!=0) {
			list=new ArrayList<IdiomType>();
			for(Entry<String, List<String>> entry : link.entrySet()) {
	            System.out.println("key:" + entry.getKey() + "   value:" + entry.getValue());
	            for(String idiom:entry.getValue()) {
	            	IdiomType idiomType=new IdiomType(classifyIdiomDaoImpl.findIdByClassifyName(idiom), entry.getKey(), idiom);
	            	list.add(idiomType);
	            }
	        }
		}
		
		return list;
	}
	
	/**
	 * ���ݵ�ǰҳ��ȡ���������
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<IdiomType> listByPage(int pageNum, int pageSize){
		Page<IdiomType> page = new Page<IdiomType>(pageNum, pageSize);
		int count = getCount();
		List<IdiomType> list = getTypes(pageNum, pageSize);
		System.out.println(list.toString());
		page.setList(list);
		page.setTotalCount(count);
		
		return page;
	}
	
	/**
	 * ���ݵ�ǰҳ��ȡ����
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<Idiom> idiomListByPage(int pageNum, int pageSize){
		Page<Idiom> page = new Page<Idiom>(pageNum, pageSize);
		int count = IdiomDao.getCount();
		List<Idiom> list = IdiomDao.getIdioms(pageNum, pageSize);
		System.out.println(list.toString());
		page.setList(list);
		page.setTotalCount(count);
		
		return page;
	}

	/**
	 * �������������
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	private List<IdiomType> getTypes(int pageNum, int pageSize) {
		List<IdiomType> list=new ArrayList<IdiomType>();
		int i=0;
		for(IdiomType idiom:getAllTypes()) {
			if((pageNum-1)*pageSize<=i && i<=(pageNum-1)*pageSize+pageSize) {
				list.add(idiom);
			}
			i++;
		}
		
		return list;
	}

	/**
	 * �������е�������
	 * @return
	 */
	public int getCount() {
		int count=0;
		count=getAllTypes().size();
		return count;
	}
	
	/**
	 * ��ȡ�����ղ�������
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int getSaveIdiomCount() {
		int count=IdiomDao.getSaveIdiomCount();
		return count;
	}
	
	/**
	 * �������е�������
	 * @return
	 */
	public int getIdiomCount() {
		int count=0;
		count=IdiomDao.getCount();
		return count;
	}
	
	/**
	 * ͨ��classifyidiom��ѯ��classifyidiom�Ƿ�����ڱ���
	 * @param book
	 * @return
	 */
	public static boolean idiomIfExist(String classifyName) {
		boolean temp = IdiomDao.idiomIfExist(classifyName);
		return temp;
	}
	
	/**
	 * ���û�и����ͣ����������ƴ����
	 * @param book
	 * @return
	 */
	public static boolean addIdiomParentType(String classifyName) {
		boolean b=IdiomDao.addIdiomParentType(classifyName);
		return b;
	}
	
	/**
	 * ����и����ͣ�ͨ���������Ʋ�ѯ�÷������Ƶ�id
	 * @param book
	 * @return
	 */
	public static int getIdiomId(String classifyName) {
		int id=IdiomDao.getIdiomId(classifyName);
		return id;
	}
	
	
	/**
	 * ����������ӵ����ݿ�
	 * @param book
	 * @return
	 */
	public static boolean addIdiomChildType(String classifyName,int parentId) {
		boolean b=IdiomDao.addIdiomChildType(classifyName, parentId);
		return b;
	}

	/**
	 * �õ������͵�����������
	 * @param parentId
	 * @return
	 */
	public String getChildType(int parentId) {
		return IdiomDao.querryChildTypeByParentId(parentId);
	}
	
	
}
