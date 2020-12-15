package com.group.tiantian.entity;

public class IdiomSearchHistory {
	private int id;
	private String phone;
	private String childName;
	private String searchStr;
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public IdiomSearchHistory() {
		super();
	}

	public IdiomSearchHistory(int id, String phone, String childName, String searchStr, int status) {
		super();
		this.id = id;
		this.phone = phone;
		this.childName = childName;
		this.searchStr = searchStr;
		this.status = status;
	}

	@Override
	public String toString() {
		return "IdiomSearchHistory [id=" + id + ", phone=" + phone + ", childName=" + childName + ", searchStr="
				+ searchStr + ", status=" + status + "]";
	}

}
