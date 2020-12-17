package com.group.tiantian.server.idiom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.Book;
import com.group.tiantian.server.entity.Idiom;
import com.group.tiantian.server.entity.IdiomType;
import com.group.tiantian.util.DBUtil;

public class IdiomDao {
	
	/**
	 * �޸ĳ�������
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
			//�жϸ������Ƿ�Ϊ��
			if(idiomType.getParentType().equals("��")) {
				pstamt.setInt(3, 0);
			}else {
				//��ѯ�����͵�id
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
	 * ����id�ҵ������Ͷ���
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
					idiomType=new IdiomType(id, "��", rs.getString("classifyName"));
				}else {
					//������ѯ����������
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
	 * ���ݳ������������ѯ�÷���ĳ���
	 * @param classification ������������
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
	 * ��ȡ����������
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
			pstamt.setInt(1,(pageNum-1)*pageSize);  //�ӵڼ�����ʼ
			pstamt.setInt(2, pageSize);         //����
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
	 * ����id�ҵ����������ƣ��ӻ򸸣�
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
	 * ��ȡ�����ղ�������
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
	 * ͨ��classifyidiom��ѯ��classifyidiom�Ƿ�����ڱ���
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
	 * ���û�и����ͣ����������ƴ����
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
			pstamt.setString(1,classifyName);  //��������
			pstamt.setInt(2,0);         //������id
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
	 * ����и����ͣ�ͨ���������Ʋ�ѯ�÷������Ƶ�id
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
	 * ����������ӵ����ݿ�
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
			pstamt.setString(1,classifyName);  //��������
			pstamt.setInt(2,parentId);         //������id
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
	 * ����idɾ���鼮
	 * @param name
	 * @return ���ڷ���true
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

	public static boolean deleteIdiom(int id) {
		boolean b=false;
		String sql="delete from idiom where id=?";
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

	public static int getCount(String searchInfo) {
		int count=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select count(*) from idiom where idiom like ?";
		try {
			pstamt=conn.prepareStatement(sql);
			String str="%"+searchInfo+"%";
			pstamt.setString(1, str);
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

	public static List<Idiom> getIdioms(int pageNum, int pageSize, String searchInfo) {
		List<Idiom> list=new ArrayList<>();
		String sql="select * from idiom where idiom like ? limit ?,?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		try {
			pstamt=conn.prepareStatement(sql);
			String str="%"+searchInfo+"%";
			pstamt.setString(1, str);
			pstamt.setInt(2,(pageNum-1)*pageSize);  //�ӵڼ�����ʼ
			pstamt.setInt(3, pageSize);         //����
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
}
