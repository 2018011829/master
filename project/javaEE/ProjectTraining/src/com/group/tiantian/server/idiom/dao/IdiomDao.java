package com.group.tiantian.server.idiom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.group.tiantian.entity.Book;
import com.group.tiantian.server.entity.ChildType;
import com.group.tiantian.server.entity.Idiom;
import com.group.tiantian.server.entity.IdiomType;
import com.group.tiantian.util.DBUtil;


public class IdiomDao {
	
	/**
	 * 修改成语类型
	 * @param id
	 * @param idiomType
	 * @return
	 */
	public static boolean updateIdiomType(IdiomType idiomType) {
		boolean b=false;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="update classifyidiom set classifyName=?,parentId=? where id=?";
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setString(1, idiomType.getChildType());
			pstamt.setInt(2, idiomType.getId());
			//判断父类型是否为空
			if(idiomType.getParentType().equals("空")) {
				pstamt.setInt(3, 0);
			}else {
				//查询父类型的id
				int id=getIdiomId(idiomType.getParentType());
				pstamt.setInt(3, id);
			}
			int row=pstamt.executeUpdate();
			if(row>0) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return b;
	}
		
	/**
	 * 根据id找到该类型对象
	 * @param id
	 * @return
	 */
	public static IdiomType getIdiomTypeObjById(int id) {
		IdiomType idiomType=null;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select * from classifyidiom where id=?";
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setInt(1, id);
			ResultSet rs=pstamt.executeQuery();
			if(rs.next()) {
				if(rs.getInt("parentId")==0) {
					idiomType=new IdiomType(id, "空", rs.getString("classifyName"));
				}else {
					//继续查询父类型名称
					int parentId=rs.getInt("parentId");
					String sql1="select * from classifyidiom where id=?";
					pstamt=conn.prepareStatement(sql1);
					pstamt.setInt(1, parentId);
					ResultSet rs1=pstamt.executeQuery();
					if(rs1.next()) {
						idiomType=new IdiomType(id, rs1.getString("classifyName"), rs.getString("classifyName"));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return idiomType;
	}

	/**
	 * 根据成语所属分类查询该分类的成语
	 * @param classification 成语所属分类
	 * @return
	 */
	public List<String> findByClassification(int classification) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from idiom");
			rs = pstm.executeQuery();
			while(rs.next()) {
				Idiom idiom = new Idiom();
				idiom.setId(rs.getInt(1));
				idiom.setIdiom(rs.getString(2));
				idiom.setIdiomType(getIdiomTypeById(rs.getInt("classification")));
				list.add(idiom.getIdiom());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}

	/**
	 * 获取成语总数量
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static int getCount() {
		int count=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select count(*) from idiom";
		try {
			pstamt=conn.prepareStatement(sql);
			ResultSet rs=pstamt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
				System.out.println(count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	public static List<Idiom> getIdioms(int pageNum, int pageSize) {
		List<Idiom> list=new ArrayList<>();
		String sql="select * from idiom limit ?,?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setInt(1,(pageNum-1)*pageSize);  //从第几条开始
			pstamt.setInt(2, pageSize);         //数量
			ResultSet rs=pstamt.executeQuery();
			while(rs.next()) {
				Idiom idiom=new Idiom();
				idiom.setId(rs.getInt("id"));
				idiom.setIdiom(rs.getString("idiom"));
				idiom.setIdiomType(getIdiomTypeById(rs.getInt("classification")));
				list.add(idiom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 根据id找到该类型名称（子或父）
	 * @param id
	 * @return
	 */
	public static String getIdiomTypeById(int id) {
		String type=null;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select * from classifyidiom where id=?";
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setInt(1, id);
			ResultSet rs=pstamt.executeQuery();
			if(rs.next()) {
				type=rs.getString("classifyName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return type;
	}
	
	/**
	 * 获取成语收藏总数量
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static int getSaveIdiomCount() {
		int count=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select count(*) from idiom_save";
		try {
			pstamt=conn.prepareStatement(sql);
			ResultSet rs=pstamt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
				System.out.println(count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	/**
	 * 通过classifyidiom查询该classifyidiom是否存在于表中
	 * @param book
	 * @return
	 */
	public static boolean idiomIfExist(String classifyName) {
		boolean temp = false;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select * from classifyidiom where classifyName = ?";
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setString(1, classifyName);
			ResultSet rs=pstamt.executeQuery();
			if(rs.next()) {
				temp = true;
			}else {
				temp = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
	
	/**
	 * 如果没有父类型，将分类名称存进表
	 * @param book
	 * @return
	 */
	public static boolean addIdiomParentType(String classifyName) {
		boolean b=false;
		String sql="insert into classifyidiom(classifyName,parentId) values(?,?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setString(1,classifyName);  //分类名称
			pstamt.setInt(2,0);         //父类型id
			int row=pstamt.executeUpdate();
			if(row>0) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * 如果有父类型，通过分类名称查询该分类名称的id
	 * @param book
	 * @return
	 */
	public static int getIdiomId(String classifyName) {
		int id=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select id from classifyidiom where classifyName = ?";
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setString(1, classifyName);
			ResultSet rs=pstamt.executeQuery();
			if(rs.next()) {
				id=rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	
	/**
	 * 将子类型添加到数据库
	 * @param book
	 * @return
	 */
	public static boolean addIdiomChildType(String classifyName,int parentId) {
		boolean b=false;
		String sql="insert into classifyidiom(classifyName,parentId) values(?,?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setString(1,classifyName);  //分类名称
			pstamt.setInt(2,parentId);         //父类型id
			int row=pstamt.executeUpdate();
			if(row>0) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}

	
	/**
	 * 得到一个成语父类型的所有子类型
	 * @param parentId
	 * @return 所有子类型entity的集合json串
	 */
	
	public static String querryChildTypeByParentId(int parentId) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String json="";
		try {
			pstamt=conn.prepareStatement("select id,classifyName from classifyidiom where parentId=? and parentId != 0");
			pstamt.setInt(1, parentId);
			ResultSet resultSet=pstamt.executeQuery();
			List<ChildType> childTypes=new ArrayList<ChildType>();
			while(resultSet.next()) {
				ChildType childType=new ChildType();
				childType.setId(resultSet.getInt(1));
				childType.setClassifyName(resultSet.getString(2));
				childTypes.add(childType);
			}
			Gson gson=new Gson();
			json=gson.toJson(childTypes);
			System.out.println("得到子类型的json："+json);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}


	/**
	 * 根据id删除书籍
	 * @param name
	 * @return 存在返回true
	 */
	public static boolean deleteIdiomType(int id) {
		boolean b=false;
		String sql="delete from classifyidiom where id=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setInt(1, id);
			int row=pstamt.executeUpdate();
			if(row>0) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;

	}

	public static int insertIdiom(String idiom, int type) {
		// TODO Auto-generated method stub
		int tag=0;
		String sql="insert into idiom(idiom,classification) values (?,?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setString(1, idiom);
			pstamt.setInt(2, type);
			tag=pstamt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tag;
	}
}

