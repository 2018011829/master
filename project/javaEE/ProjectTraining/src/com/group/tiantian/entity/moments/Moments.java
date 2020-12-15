package com.group.tiantian.entity.moments;

import java.util.List;

import com.google.gson.Gson;

public class Moments {
	private int id;//id
	private String phoneNumber;//发表人手机号
    private String headPortraitUrl;//用户头像地址
    private String time;//发表说说的时间
    private String name;//用户昵称
    private String remark;//用户备注
    private String content;//用户发表的文字内容
    private String pictureUrl;//用户发表的图片路径(多张图片)
    private int praiseNumber;//点赞数
    private String likeGiveName;//点赞人的昵称
    private int likegiveboolen;//当前用户是否已经点赞该说说
    private String comments;//用户评论
    private String replyContent;//回复信息
    private String attentionList;//关注列表
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
