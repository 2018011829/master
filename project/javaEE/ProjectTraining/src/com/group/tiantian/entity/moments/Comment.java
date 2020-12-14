package com.group.tiantian.entity.moments;

public class Comment {
	private int id;//id
	private int momentsId;//�����۵�˵˵id
	private String comment;//��������
	private String personPhone; //�������ֻ���
	private String personName;//�������ǳ�
	private String personHead;//������ͷ��

    public Comment(){

    }
	public Comment(int id,int momentsId, String comment, String personPhone, String personName,String personHead) {
		super();
		this.id = id;
		this.momentsId = momentsId;
		this.comment = comment;
		this.personPhone = personPhone;
		this.personName = personName;
		this.personHead = personHead;
	}

	public String getPersonHead() {
		return personHead;
	}
	public void setPersonHead(String personHead) {
		this.personHead = personHead;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPersonPhone() {
		return personPhone;
	}

	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
}
