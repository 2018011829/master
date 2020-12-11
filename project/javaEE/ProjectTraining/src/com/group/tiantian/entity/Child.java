package com.group.tiantian.entity;

/**
 * 2020-12-09
 * @author lsq
 * */
public class Child {
	private int id;
	private String name;
	private String grade;
	private String sex;
	private String parentPhone;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getParentPhone() {
		return parentPhone;
	}
	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}
	@Override
	public String toString() {
		return "Child [id=" + id + ", name=" + name + ", grade=" + grade + ", sex=" + sex + ", parentPhone="
				+ parentPhone + "]";
	}
	
	
	
	
	
	
}
