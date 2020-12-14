package com.group.tiantian.moments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.tiantian.entity.moments.Attention;
import com.group.tiantian.entity.moments.PersonalInfo;
import com.group.tiantian.util.DBUtil;

public class AttentionDao {
	public static Connection connection;
	public static AttentionDao attentionDao;
	public static PreparedStatement preparedStatement;

	/**
	 * ��ȡReplyContentDaoʵ��
	 * 
	 * @return
	 */
	public static AttentionDao getInstance() {
		if (null == attentionDao) {
			attentionDao = new AttentionDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return attentionDao;
	}

	/**
	 * ͨ����ǰ�û��ֻ��źͷ���˵˵�û����ֻ��Ų�ѯ�����Ƿ�����һ����¼
	 * 
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return
	 */
	public boolean selectAttention(String personPhone, String momentsPhone) {
		boolean b = false;
		String sql = "select * from moments_attention where personPhone = ? and momentsPhone = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, personPhone);
			preparedStatement.setString(2, momentsPhone);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				b = true;
			} else {
				b = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * �����ݿ��в��뵱ǰ�û��ֻ��źͷ���˵˵�û����ֻ���
	 * 
	 * @param start
	 * @param end
	 * @param article
	 * @param content
	 * @return
	 */
	public boolean insertAttention(String personPhone, String momentsPhone) {
		boolean b = false;
		// �ȴӱ��в�ѯ��ע��Ϣ������У�ֱ���޸��Ƿ��ע,���û�У���������
		if (!selectAttention(personPhone, momentsPhone)) {
			String sql = "insert into moments_attention(personPhone,momentsPhone,whetherAttention) values(?,?,?)";
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, personPhone);
				preparedStatement.setString(2, momentsPhone);
				preparedStatement.setInt(3, 1);
				int row = preparedStatement.executeUpdate();
				if (row > 0) {
					b = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String sql = "update moments_attention set whetherAttention = ?";
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, 1);
				int row = preparedStatement.executeUpdate();
				if (row > 0) {
					b = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return b;
	}
	/**
	 * ͨ����ǰ�û��ֻ��źͷ���˵˵�û����ֻ���ɾ��������¼
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return 
	 */
	public boolean deleteAttention(String personPhone, String momentsPhone) {
		boolean b = false;
		String sql = "delete from moments_attention where personPhone = ? and momentsPhone = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,personPhone);
			preparedStatement.setString(2,momentsPhone);
			int row=preparedStatement.executeUpdate();
			if(row>0) {
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * ͨ����ǰ�û��ֻ��Ų�ѯ��ע�б�
	 * 
	 * @param start
	 * @param end
	 * @param articleName
	 * @param contentName
	 * @return
	 */
	public List<Attention> getAttentionList(String personPhone) {
		List<Attention> attentions = new ArrayList<>();
		String sql = "select * from moments_attention where personPhone=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, personPhone);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				Attention attention = new Attention(rs.getString("momentsPhone"),rs.getInt("whetherAttention"));
				attentions.add(attention);
			}else {
				Attention attention = new Attention();
				attention.setWhetherAttention(0);
				attentions.add(attention);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return attentions;
	}

}
