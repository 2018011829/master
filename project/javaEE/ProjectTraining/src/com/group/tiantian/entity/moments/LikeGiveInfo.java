package com.group.tiantian.entity.moments;

import java.util.List;

public class LikeGiveInfo {
	private int id;//������Ϣid
	private int momentsId;//������˵˵id
	private String likegivePersonName;//�������ǳ�(gson����)
	private String likegivePersonPhone;//�������ֻ���
	private int likegiveNumber;//��������
	public LikeGiveInfo() {
		super();
	}
	
	
	public LikeGiveInfo(int momentsId,String likegivePersonName, int likegiveNumber) {
		super();
		this.momentsId = momentsId;
		this.likegivePersonName = likegivePersonName;
		this.likegiveNumber = likegiveNumber;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMomentsId() {
		return momentsId;
	}
	public void setMomentsId(int momentsId) {
		this.momentsId = momentsId;
	}
	public String getLikegivePersonName() {
		return likegivePersonName;
	}
	public void setLikegivePersonName(String likegivePersonName) {
		this.likegivePersonName = likegivePersonName;
	}
	public String getLikegivePersonPhone() {
		return likegivePersonPhone;
	}
	public void setLikegivePersonPhone(String likegivePersonPhone) {
		this.likegivePersonPhone = likegivePersonPhone;
	}
	public int getLikegiveNumber() {
		return likegiveNumber;
	}
	public void setLikegiveNumber(int likegiveNumber) {
		this.likegiveNumber = likegiveNumber;
	}
	
	
	
	
}
