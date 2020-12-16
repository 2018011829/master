package com.group.tiantian.server.entity;

public class IdiomType {

	private int id;
	private String parentType;
	private String childType;
	
	public IdiomType(int id, String parentType, String childType) {
		super();
		this.id = id;
		this.parentType = parentType;
		this.childType = childType;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParentType() {
		return parentType;
	}
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	public String getChildType() {
		return childType;
	}
	public void setChildType(String childType) {
		this.childType = childType;
	}
	@Override
	public String toString() {
		return "IdiomType [parentType=" + parentType + ", childType=" + childType + "]";
	}
	
}
