package com.example.projecttraining.home.fragments.MomentsFragment.Beans;

public class Attention {
	private int id;
	private String personPhone;
	private String momentsPhone;
	private int whetherAttention;
	public Attention() {
		super();
	}
	
	
	
	public Attention(String personPhone, int whetherAttention) {
		super();
		this.personPhone = personPhone;
		this.whetherAttention = whetherAttention;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPersonPhone() {
		return personPhone;
	}
	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}
	public String getMomentsPhone() {
		return momentsPhone;
	}
	public void setMomentsPhone(String momentsPhone) {
		this.momentsPhone = momentsPhone;
	}
	public int getWhetherAttention() {
		return whetherAttention;
	}
	public void setWhetherAttention(int whetherAttention) {
		this.whetherAttention = whetherAttention;
	}
	
	

}