package com.group.tiantian.entity.moments;

public class MomentsPicture {
	private int id;
	private String momentsPictureUrl;
	private int momentsId;
	private String time;
	private String personalPhone;
	
	public MomentsPicture() {
		super();
	}
	
	public MomentsPicture(int id, String momentsPictureUrl, int momentsId, String time, String personalPhone) {
		super();
		this.id = id;
		this.momentsPictureUrl = momentsPictureUrl;
		this.momentsId = momentsId;
		this.time = time;
		this.personalPhone = personalPhone;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMomentsPictureUrl() {
		return momentsPictureUrl;
	}
	public void setMomentsPictureUrl(String momentsPictureUrl) {
		this.momentsPictureUrl = momentsPictureUrl;
	}
	public int getMomentsId() {
		return momentsId;
	}
	public void setMomentsId(int momentsId) {
		this.momentsId = momentsId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPersonalPhone() {
		return personalPhone;
	}
	public void setPersonalPhone(String personalPhone) {
		this.personalPhone = personalPhone;
	}
	
	
	
	

}
