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
	 * 得到一个IdiomTypeService实例
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
	 * 从数据库中查找所有图书的类型
	 * @return 返回所有图书类型的集合
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
	 * 根据当前页获取成语的类型
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
	 * 根据当前页获取成语
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
	 * 成语的所有类型
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
	 * 成语所有的类型数
	 * @return
	 */
	public int getCount() {
		int count=0;
		count=getAllTypes().size();
		return count;
	}
	
	/**
	 * 获取成语收藏总数量
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int getSaveIdiomCount() {
		int count=IdiomDao.getSaveIdiomCount();
		return count;
	}
	
	/**
	 * 成语所有的类型数
	 * @return
	 */
	public int getIdiomCount() {
		int count=0;
		count=IdiomDao.getCount();
		return count;
	}
	
	/**
	 * 通过classifyidiom查询该classifyidiom是否存在于表中
	 * @param book
	 * @return
	 */
	public static boolean idiomIfExist(String classifyName) {
		boolean temp = IdiomDao.idiomIfExist(classifyName);
		return temp;
	}
	
	/**
	 * 如果没有父类型，将分类名称存进表
	 * @param book
	 * @return
	 */
	public static boolean addIdiomParentType(String classifyName) {
		boolean b=IdiomDao.addIdiomParentType(classifyName);
		return b;
	}
	
	/**
	 * 如果有父类型，通过分类名称查询该分类名称的id
	 * @param book
	 * @return
	 */
	public static int getIdiomId(String classifyName) {
		int id=IdiomDao.getIdiomId(classifyName);
		return id;
	}
	
	
	/**
	 * 将子类型添加到数据库
	 * @param book
	 * @return
	 */
	public static boolean addIdiomChildType(String classifyName,int parentId) {
		boolean b=IdiomDao.addIdiomChildType(classifyName, parentId);
		return b;
	}

	/**
	 * 得到父类型的所有子类型
	 * @param parentId
	 * @return
	 */
	public String getChildType(int parentId) {
		return IdiomDao.querryChildTypeByParentId(parentId);
	}
	
	
}
