package com.group.tiantian.entity.moments;

import java.util.List;

public class LikeGiveInfo {
	private int id;//点赞信息id
	private int momentsId;//被点赞说说id
	private String likegivePersonName;//点赞人昵称(gson对象)
	private String likegivePersonPhone;//点赞人手机号
	private int likegiveNumber;//点赞人数
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
