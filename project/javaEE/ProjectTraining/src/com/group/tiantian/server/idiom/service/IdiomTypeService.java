package com.group.tiantian.server.idiom.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import com.group.tiantian.classifyidiom.dao.ClassifyIdiomDaoImpl;
import com.group.tiantian.entity.Book;
import com.group.tiantian.server.book.dao.BookDao;
import com.group.tiantian.server.entity.Idiom;
import com.group.tiantian.server.entity.IdiomType;
import com.group.tiantian.server.entity.Page;
import com.group.tiantian.server.idiom.dao.IdiomDao;

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
	 * �޸ĳ�������
	 * @param id
	 * @param idiomType
	 * @return
	 */
	public static boolean updateIdiomType(IdiomType idiomType) {
		boolean b=IdiomDao.updateIdiomType(idiomType);
		return b;
	}
	
	/**
	 * ����id�ҵ������Ͷ���
	 * @param id
	 * @return
	 */
	public IdiomType getIdiomTypeObjById(int id) {
		IdiomType idiomType=IdiomDao.getIdiomTypeObjById(id);
		return idiomType;
	}
	
	/**
	 * �����ݿ��в������г��������
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
	            IdiomType idiomParentType=new IdiomType(classifyIdiomDaoImpl.findIdByClassifyName(entry.getKey()), entry.getKey(), "��");
            	list.add(idiomParentType);
	            for(String idiom:entry.getValue()) {
	            	IdiomType idiomChildType=new IdiomType(classifyIdiomDaoImpl.findIdByClassifyName(idiom), entry.getKey(), idiom);
	            	list.add(idiomChildType);
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
			if((pageNum-1)*pageSize<=i && i<(pageNum-1)*pageSize+pageSize) {
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
	public boolean idiomIfExist(String classifyName) {
		boolean temp = IdiomDao.idiomIfExist(classifyName);
		return temp;
	}
	
	/**
	 * ���û�и����ͣ����������ƴ����
	 * @param book
	 * @return
	 */
	public boolean addIdiomParentType(String classifyName) {
		boolean b=IdiomDao.addIdiomParentType(classifyName);
		return b;
	}
	
	/**
	 * ����и����ͣ�ͨ���������Ʋ�ѯ�÷������Ƶ�id
	 * @param book
	 * @return
	 */
	public int getIdiomId(String classifyName) {
		int id=IdiomDao.getIdiomId(classifyName);
		return id;
	}
	
	
	/**
	 * ����������ӵ����ݿ�
	 * @param book
	 * @return
	 */
	public boolean addIdiomChildType(String classifyName,int parentId) {
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

	public boolean deleteIdiomType(int id) {
		boolean b=IdiomDao.deleteIdiomType(id);
		
		return b;
	}

	public boolean deleteIdiom(int id) {
		boolean b=IdiomDao.deleteIdiom(id);
		
		return b;
	}

	public Page<Idiom> idiomListByPage(int pageNum, int pageSize, String searchInfo) {
		Page<Idiom> page = new Page<Idiom>(pageNum, pageSize);
		int count = IdiomDao.getCount(searchInfo);
		List<Idiom> list = IdiomDao.getIdioms(pageNum, pageSize,searchInfo);
		System.out.println(list.toString());
		page.setList(list);
		page.setTotalCount(count);
		
		return page;
	}

	public Page<IdiomType> listByPage(int pageNum, int pageSize, String searchInfo) {
		Page<IdiomType> page = new Page<IdiomType>(pageNum, pageSize);
		int count = getCount(searchInfo);
		List<IdiomType> list = getTypes(pageNum, pageSize,searchInfo);
		System.out.println(list.toString());
		page.setList(list);
		page.setTotalCount(count);
		
		return page;
	}

	private List<IdiomType> getTypes(int pageNum, int pageSize, String searchInfo) {
		List<IdiomType> list1=new ArrayList<IdiomType>();
		List<IdiomType> list=new ArrayList<IdiomType>();
		int i=0;
		for(IdiomType idiom:getAllTypes()) {
			if(idiom.getChildType().contains(searchInfo) || idiom.getParentType().contains(searchInfo)) {
				list1.add(idiom);
			}
		}
		for(IdiomType idiom:list1) {
			if((pageNum-1)*pageSize<=i && i<(pageNum-1)*pageSize+pageSize) {
				list.add(idiom);
			}
			i++;
		}
		
		return list;
	}

	private int getCount(String searchInfo) {
		int count=0;
		for(IdiomType idiomType:getAllTypes()) {
			if(idiomType.getChildType().contains(searchInfo) || idiomType.getParentType().contains(searchInfo)) {
				count+=1;
			}
		}
		return count;
	}
	
	/**
	 * ��ӳ���
	 * @param idiom
	 * @param type
	 */
	public boolean addIdiom(String idiom, int type) {
		// TODO Auto-generated method stub
		if(IdiomDao.insertIdiom(idiom,type)>0) {
			return true;
		}else {
			return false;
		}
	}

	
	
}
