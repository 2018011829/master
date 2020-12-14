package com.example.projecttraining.home.fragments.MomentsFragment.Beans;

/**
 * Created by moos on 2018/4/20.
 */

public class ReplyDetailBean {
    private int id;// id
    private int momentsId;// 被评论的说说id
    private int position;// 被回复的评论id
    private String replyContent;// 评论内容
    private String personPhone; // 评论者手机号
    private String personName;// 评论者昵称
    private String personHead;// 评论者头像

//    private String nickName;//昵称
//    private String userLogo;//头像
//    private int id;//id
//    private String commentId;//评论的id
//    private String content;//回复的内容
//    private String status;//地位
//    private String createDate;//创建时间

    public ReplyDetailBean(String replyContent, String personName) {
        this.replyContent = replyContent;
        this.personName = personName;
    }

    public ReplyDetailBean(String replyContent, String personName,int momentsId,int position) {
        this.replyContent = replyContent;
        this.personName = personName;
        this.momentsId = momentsId;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
