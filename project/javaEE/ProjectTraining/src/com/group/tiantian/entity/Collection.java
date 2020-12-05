package com.group.tiantian.entity;

public class Collection {

	private String phoneNum; //��¼���ֻ���
	private String childName; //���ֻ��Ŷ�Ӧ�ĺ��ӵ����֣���ĸ�����Ĳ�ͬ����������ͬ������Ϊ20��
	private String collectionType; //�ղص����ݵ����ͣ�����Ϊ20��
	private String collectionContent; //�ղص����ݣ��ղ�����Դ���������֣��ղس�����Դ�����ﺺ�֣�����Ϊ100��
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
