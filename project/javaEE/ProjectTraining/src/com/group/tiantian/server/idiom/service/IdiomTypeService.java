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
	 * 修改成语类型
	 * @param id
	 * @param idiomType
	 * @return
	 */
	public static boolean updateIdiomType(IdiomType idiomType) {
		boolean b=IdiomDao.updateIdiomType(idiomType);
		return b;
	}
	
	/**
	 * 根据id找到该类型对象
	 * @param id
	 * @return
	 */
	public IdiomType getIdiomTypeObjById(int id) {
		IdiomType idiomType=IdiomDao.getIdiomTypeObjById(id);
		return idiomType;
	}
	
	/**
	 * 从数据库中查找所有成语的类型
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
	            IdiomType idiomParentType=new IdiomType(classifyIdiomDaoImpl.findIdByClassifyName(entry.getKey()), entry.getKey(), "空");
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
			if((pageNum-1)*pageSize<=i && i<(pageNum-1)*pageSize+pageSize) {
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
	public boolean idiomIfExist(String classifyName) {
		boolean temp = IdiomDao.idiomIfExist(classifyName);
		return temp;
	}
	
	/**
	 * 如果没有父类型，将分类名称存进表
	 * @param book
	 * @return
	 */
	public boolean addIdiomParentType(String classifyName) {
		boolean b=IdiomDao.addIdiomParentType(classifyName);
		return b;
	}
	
	/**
	 * 如果有父类型，通过分类名称查询该分类名称的id
	 * @param book
	 * @return
	 */
	public int getIdiomId(String classifyName) {
		int id=IdiomDao.getIdiomId(classifyName);
		return id;
	}
	
	
	/**
	 * 将子类型添加到数据库
	 * @param book
	 * @return
	 */
	public boolean addIdiomChildType(String classifyName,int parentId) {
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
	 * 添加成语
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
