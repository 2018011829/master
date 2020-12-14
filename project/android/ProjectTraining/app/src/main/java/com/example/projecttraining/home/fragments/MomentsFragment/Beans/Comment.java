package com.example.projecttraining.home.fragments.MomentsFragment.Beans;

public class Comment {
    private int id;//id
    private int momentsId;//被评论的说说id
    private String comment;//评论内容
    private String personPhone; //评论者手机号
    private String personName;//评论者昵称
    private String personHead;//评论者头像

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

    public String getPersonHead() {
        return personHead;
    }

    public void setPersonHead(String personHead) {
        this.personHead = personHead;
    }
}
