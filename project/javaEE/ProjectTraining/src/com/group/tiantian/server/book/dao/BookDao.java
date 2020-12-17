package com.group.tiantian.server.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.group.tiantian.entity.Book;
import com.group.tiantian.util.DBUtil;

public class BookDao {
	
	/**
	 * ����idɾ���鼮
	 * @param name
	 * @return ���ڷ���true
	 */
	public static boolean deleteBookType(int id) {
		boolean b=false;
		String sql="delete from types where id=?";
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
	
	/**
	 * �޸�ͼ����Ϣ
	 * @param book
	 * @return
	 */
	public static boolean updateBookType(int id,String type) {
		boolean b=false;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="update types set type=? where id=?";
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setString(1, type);
			pstamt.setInt(2, id);
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
	public static boolean deleteBook(int id) {
		boolean b=false;
		String sql="delete from books where id=?";
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

	/**
	 * �����������Ƿ��Ѿ�����
	 * @param name
	 * @return ���ڷ���true
	 */
	public static boolean searchBookType(String type) {
		boolean b=false;
		String sql="select * from types where type=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setString(1, type);
			ResultSet rs=pstamt.executeQuery();
			if(rs.next()) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * �������Ϣ��ӵ����ݿ�
	 * @param book
	 * @return
	 */
	public static boolean addBookType(String type) {
		boolean b=false;
		String sql="insert into types(type) values(?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setString(1, type);  //�ӵڼ�����ʼ
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
	 * �޸�ͼ����Ϣ
	 * @param book
	 * @return
	 */
	public static boolean updateBook(Book book) {
		boolean b=false;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="update books set name=?,introduce=?,type=?,img=?,content=?,author=?,grades=? where id=?";
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setString(1, book.getName());  //�ӵڼ�����ʼ
			pstamt.setString(2, book.getIntroduce());         //����
			pstamt.setString(3, book.getType());
			pstamt.setString(4, book.getImg());
			pstamt.setString(5, book.getContent());
			pstamt.setString(6, book.getAuthor());
			pstamt.setString(7, book.getGrades());
			pstamt.setInt(8, book.getId());
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
	 * ����id���������Ϣ
	 * @param 
	 * @return 
	 */
	public static Book searchBookInfo(int id) {
		Book book=null;
		String sql="select * from books where id=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setInt(1, id);
			ResultSet rs=pstamt.executeQuery();
			if(rs.next()) {
				book=new Book();
				book.setId(rs.getInt("id"));
				book.setAuthor(rs.getString("author"));
				book.setName(rs.getString("name"));
				book.setContent(rs.getString("content"));
				book.setIntroduce(rs.getString("introduce"));
				book.setImg(rs.getString("img"));
				book.setType(rs.getString("type"));
				if(rs.getString("grades").equals("small")) {
					book.setGrades("1-3�꼶");
				}else {
					book.setGrades("4-6�꼶");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	/**
	 * ���������Ƿ��Ѿ�����
	 * @param name
	 * @return ���ڷ���true
	 */
	public static boolean searchBook(String name) {
		boolean b=false;
		String sql="select * from books where name=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setString(1, name);
			ResultSet rs=pstamt.executeQuery();
			if(rs.next()) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * �������Ϣ��ӵ����ݿ�
	 * @param book
	 * @return
	 */
	public static boolean addBook(Book book) {
		boolean b=false;
		String sql="insert into books(name,introduce,type,img,content,author,grades) values(?,?,?,?,?,?,?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setString(1, book.getName());  //�ӵڼ�����ʼ
			pstamt.setString(2, book.getIntroduce());         //����
			pstamt.setString(3, book.getType());
			pstamt.setString(4, book.getImg());
			pstamt.setString(5, book.getContent());
			pstamt.setString(6, book.getAuthor());
			pstamt.setString(7, book.getGrades());
			int row=pstamt.executeUpdate();
			if(row>0) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	
	public static List<Book> getBooksByPage(int pageNum, int pageSize) {
		List<Book> list=new ArrayList<>();
		String sql="select * from books limit ?,?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setInt(1,(pageNum-1)*pageSize);  //�ӵڼ�����ʼ
			pstamt.setInt(2, pageSize);         //����
			ResultSet rs=pstamt.executeQuery();
			while(rs.next()) {
				Book book=new Book();
				book.setId(rs.getInt("id"));
				book.setAuthor(rs.getString("author"));
				book.setName(rs.getString("name"));
				book.setContent(rs.getString("content"));
				book.setIntroduce(rs.getString("introduce"));
				book.setImg(rs.getString("img"));
				book.setType(rs.getString("type"));
				if(rs.getString("grades").equals("small")) {
					book.setGrades("1-3�꼶");
				}else {
					book.setGrades("4-6�꼶");
				}
				
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * ��ȡ�鼮��������
	 * @return
	 */
	public static int getCount() {
		int count=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select count(*) from books";
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
	 * ��ȡ�ղ��鼮��������
	 * @return
	 */
	public static int getSaveBookCount() {
		int count=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select count(*) from collections where collection_type=?";
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setString(1, "book");
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
	 * ��ȡ�鼮��������
	 * @return
	 */
	public static int getBookshelfCount() {
		int count=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select count(*) from bookshelf";
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
	 * ģ������
	 * @param pageNum
	 * @param pageSize
	 * @param searchInfo
	 * @return
	 */
	public static List<Book> getBooksByPage(int pageNum, int pageSize, String searchInfo) {
		List<Book> list=new ArrayList<>();
		String sql="select * from books where name like ? limit ?,?";
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
				Book book=new Book();
				book.setId(rs.getInt("id"));
				book.setAuthor(rs.getString("author"));
				book.setName(rs.getString("name"));
				book.setContent(rs.getString("content"));
				book.setIntroduce(rs.getString("introduce"));
				book.setImg(rs.getString("img"));
				book.setType(rs.getString("type"));
				if(rs.getString("grades").equals("small")) {
					book.setGrades("1-3�꼶");
				}else {
					book.setGrades("4-6�꼶");
				}
				
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * ģ�������鼮��������
	 * @param searchInfo
	 * @return
	 */
	public static int getCount(String searchInfo) {
		int count=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select count(*) from books where name like ?";
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
}
