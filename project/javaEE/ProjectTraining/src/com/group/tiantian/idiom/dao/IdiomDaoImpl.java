package com.group.tiantian.idiom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.Idiom;
import com.group.tiantian.entity.IdiomSave;
import com.group.tiantian.util.DBUtil;

/**
 * 2020-11-25
 * @author lrf
 */
public class IdiomDaoImpl {
	
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
			pstm = conn.prepareStatement("select * from idiom where classification=" + classification + "");
			rs = pstm.executeQuery();
			while(rs.next()) {
				Idiom idiom = new Idiom();
				idiom.setId(rs.getInt(1));
				idiom.setIdiom(rs.getString(2));
				idiom.setClassification(rs.getInt(3));
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
	 * �ղس���
	 * @param idiomSave
	 * @return
	 */
	public boolean saveIdiom(IdiomSave idiomSave) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// ��ȡ�����ղ���Ϣ
		String phone = idiomSave.getPhone();
		String childName = idiomSave.getChildName();
		String idiomName = idiomSave.getIdiomName();
		int n = -1;// �洢����ļ�¼��
		try {
			conn = DBUtil.getConnection();
			String select = "select * from idiom_save where phone='"+phone+"' and childName='"+childName+"' and idiomName='"+idiomName+"'";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (!isExist) {
				String sql = "insert into idiom_save(phone,childName,idiomName) values('"+phone+"','"+childName+"','"+idiomName+"')";
				// �������ղ���Ϣ���뵽���ݿ����
				n = pstm.executeUpdate(sql);
			System.out.println("�ղس����SQL��䣺" + sql);
			}
			System.out.println("�ղس���ǰ��sql��䣺" + select);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return n > 0 ? true : false;
	}
	
	/**
	 * ����idȡ�������ղ�
	 * @param id
	 * @return
	 */
	public boolean cancelSaveIdiom(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int n = -1;// �洢�޸ĵļ�¼
		try {
			conn = DBUtil.getConnection();
			String select = "select * from idiom_save where id=" + id + "";
			System.out.println("ȡ���ղ�ǰ�Ĳ�ѯSQL��䣺" + select);
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
				String sql = "delete from idiom_save where id=" + id + "";
				// �������ղ���Ϣ�����ݿ����ɾ��
				n = pstm.executeUpdate(sql);
				System.out.println("ȡ���ղص�SQL��䣺" + sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return n > 0 ? true : false;
	}
	
	/**
	 * ���ݳ����ղ���Ϣ���Ҹ�����Ϣ��id
	 * @param phone
	 * @param childName
	 * @param idiomName
	 * @return
	 */
	public int findSaveIdiomId(String phone, String childName, String idiomName) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int n = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "select id from idiom_save where phone='"+phone+"' and childName='"+childName+"' and idiomName='"+idiomName+"'";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				n = rs.getInt(1);
			}
			System.out.println("���ݳ��������Ϣ��ѯid��SQL��䣺" + sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return n;
	}
	
	/**
	 * ���ݳ����ղ���Ϣ�жϸó����Ƿ�ĳ�û���ĳ�������ղ�
	 * @param phone
	 * @param childName
	 * @param idiomName
	 * @return
	 */
	public boolean idiomIsSaved(String phone, String childName, String idiomName) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from idiom_save where phone='"+phone+"' and childName='"+childName+"' and idiomName='"+idiomName+"'";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if(rs.next()) {
				return true;
			}
			System.out.println("�ж��Ƿ��ѱ��ղص�SQL��䣺" + sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return false;
	}

}