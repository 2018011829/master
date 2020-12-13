package com.group.tiantian.server.entity;

public class Admin {
	private String userName;
	private String userPwd;
	private String headPicture;
	public Admin() {
		super();
	}
	public Admin(String userName, String userPwd) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
	}
	public Admin(String userName, String userPwd, String headPicture) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
		this.headPicture = headPicture;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getHeadPicture() {
		return headPicture;
	}
	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}
	@Override
	public String toString() {
		return "Admin [userName=" + userName + ", userPwd=" + userPwd + ", headPicture=" + headPicture + "]";
	}
	
}
