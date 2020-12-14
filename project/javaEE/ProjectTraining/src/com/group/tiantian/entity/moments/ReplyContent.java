package com.group.tiantian.entity.moments;

public class ReplyContent {
	private int id;// id
	private int momentsId;// �����۵�˵˵id
	private int position;// ���ظ�������id
	private String replyContent;// ��������
	private String personPhone; // �������ֻ���
	private String personName;// �������ǳ�
	private String personHead;// ������ͷ��

	public ReplyContent() {
		super();
	}

	public ReplyContent(int momentsId, int position, String replyContent, String personPhone, String personName,
			String personHead) {
		super();
		this.momentsId = momentsId;
		this.position = position;
		this.replyContent = replyContent;
		this.personPhone = personPhone;
		this.personName = personName;
		this.personHead = personHead;
	}

	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
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

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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

	public String getPersonHead() {
		return personHead;
	}

	public void setPersonHead(String personHead) {
		this.personHead = personHead;
	}

}
