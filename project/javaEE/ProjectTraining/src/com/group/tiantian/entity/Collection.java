package com.group.tiantian.entity;

public class Collection {

	private String phoneNum; //登录的手机号
	private String childName; //该手机号对应的孩子的名字（父母给他的不同孩子起名不同，长度为20）
	private String collectionType; //收藏的数据的类型（长度为20）
	private String collectionContent; //收藏的内容：收藏书可以存入书的名字，收藏成语可以存入成语汉字（长度为100）
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}
	public String getCollectionContent() {
		return collectionContent;
	}
	public void setCollectionContent(String collectionContent) {
		this.collectionContent = collectionContent;
	}
	public Collection(String phoneNum, String childName, String collectionType, String collectionContent) {
		super();
		this.phoneNum = phoneNum;
		this.childName = childName;
		this.collectionType = collectionType;
		this.collectionContent = collectionContent;
	}
	@Override
	public String toString() {
		return "Collection [phoneNum=" + phoneNum + ", childName=" + childName + ", collectionType=" + collectionType
				+ ", collectionContent=" + collectionContent + "]";
	}
	
	
}
