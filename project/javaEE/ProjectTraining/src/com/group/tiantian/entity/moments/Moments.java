package com.group.tiantian.entity.moments;

import java.util.List;

public class Moments {
	private int id;//id
	private String phoneNumber;//�������ֻ���
    private String headPortraitUrl;//�û�ͷ���ַ
    private String name;//�û��ǳ�
    private String remark;//�û���ע
    private String content;//�û��������������
    private List<String> pictureUrl;//�û������ͼƬ·��(����ͼƬ)
    private int praiseNumber;//������
    private List<String> friendName;//�����˵��ǳ�
    private List<Comment> comments;//�û�����
    
    public Moments() {
    }
    

    public Moments(String name, List<String> pictureUrl) {
		super();
		this.name = name;
		this.pictureUrl = pictureUrl;
	}


	public Moments(String headPortraitUrl, String name, String content, List<String> pictureUrl) {
        this.headPortraitUrl = headPortraitUrl;
        this.name = name;
        this.content = content;
        this.pictureUrl = pictureUrl;
    }
    

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(List<String> pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public int getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(int praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    public List<String> getFriendName() {
        return friendName;
    }

    public void setFriendName(List<String> friendName) {
        this.friendName = friendName;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
