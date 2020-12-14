package com.group.tiantian.entity.moments;

public class ReplyContent {
	private int id;// id
	private int momentsId;// 被评论的说说id
	private int position;// 被回复的评论id
	private String replyContent;// 评论内容
	private String personPhone; // 评论者手机号
	private String personName;// 评论者昵称
	private String personHead;// 评论者头像

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
