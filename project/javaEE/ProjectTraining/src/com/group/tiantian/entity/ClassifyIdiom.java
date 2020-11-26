package com.group.tiantian.entity;

/**
 * 2020-11-25
 * @author lrf
 */
public class ClassifyIdiom {
	private int id;
	private String classifyName;
	private int parentId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public ClassifyIdiom() {
		super();
	}

	public ClassifyIdiom(int id, String classifyName, int parentId) {
		super();
		this.id = id;
		this.classifyName = classifyName;
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "ClassifyIdiom [id=" + id + ", classifyName=" + classifyName + ", parentId=" + parentId + "]";
	}

}
