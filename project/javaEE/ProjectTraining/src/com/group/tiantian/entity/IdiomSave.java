package com.group.tiantian.entity;

public class IdiomSave {
	private int id;
	private String phone;
	private String childName;
	private String idiomName;

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

	public String getIdiomName() {
		return idiomName;
	}

	public void setIdiomName(String idiomName) {
		this.idiomName = idiomName;
	}

	public IdiomSave() {
		super();
	}

	public IdiomSave(int id, String phone, String childName, String idiomName) {
		super();
		this.id = id;
		this.phone = phone;
		this.childName = childName;
		this.idiomName = idiomName;
	}

	@Override
	public String toString() {
		return "IdiomSave [id=" + id + ", phone=" + phone + ", childName=" + childName + ", idiomName=" + idiomName
				+ "]";
	}

}
