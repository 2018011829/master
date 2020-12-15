package com.group.tiantian.entity.moments;

import java.util.List;

import com.google.gson.Gson;

public class Moments {
	private int id;//id
	private String phoneNumber;//�������ֻ���
    private String headPortraitUrl;//�û�ͷ���ַ
    private String time;//����˵˵��ʱ��
    private String name;//�û��ǳ�
    private String remark;//�û���ע
    private String content;//�û��������������
    private String pictureUrl;//�û������ͼƬ·��(����ͼƬ)
    private int praiseNumber;//������
    private String likeGiveName;//�����˵��ǳ�
    private int likegiveboolen;//��ǰ�û��Ƿ��Ѿ����޸�˵˵
    private String comments;//�û�����
    private String replyContent;//�ظ���Ϣ
    private String attentionList;//��ע�б�
    public Moments() {
    }
    

    public Moments(int id,String phoneNumber,String time) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.time = time;
	}

	public Moments(String headPortraitUrl, String name, String content, String pictureUrl,String likeGiveName,int likegiveboolen,String comments,String replyContent) {
        this.headPortraitUrl = headPortraitUrl;
        this.name = name;
        this.content = content;
        this.pictureUrl = pictureUrl;
        this.likeGiveName = likeGiveName;
        this.likegiveboolen = likegiveboolen;
        this.comments = comments;
        this.replyContent = replyContent;
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
	

    public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
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


	public String getPictureUrl() {
		return pictureUrl;
	}


	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}


	public int getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(int praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    

    public String getLikeGiveName() {
		return likeGiveName;
	}


	public void setLikeGiveName(String likeGiveName) {
		this.likeGiveName = likeGiveName;
	}


	
	public int getLikegiveboolen() {
		return likegiveboolen;
	}


	public void setLikegiveboolen(int likegiveboolen) {
		this.likegiveboolen = likegiveboolen;
	}


	public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


	public String getReplyContent() {
		return replyContent;
	}


	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}


	public String getAttentionList() {
		return attentionList;
	}


	public void setAttentionList(String attentionList) {
		this.attentionList = attentionList;
	}
   
}
