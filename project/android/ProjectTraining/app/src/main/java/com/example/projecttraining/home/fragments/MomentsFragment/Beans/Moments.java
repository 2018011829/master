package com.example.projecttraining.home.fragments.MomentsFragment.Beans;

import java.util.List;

public class Moments {//动态类（每个说说）
    private String phoneNumber;//发表人手机号
    private String headPortraitUrl;//用户头像地址
    private String name;//用户昵称
    private String remark;//用户备注
    private String content;//用户发表的文字内容
    private List<String> pictureUrl;//用户发表的图片路径(多张图片)
    private int praiseNumber;//点赞数
    private List<String> friendName;//点赞人的昵称
    private List<Comment> comments;//用户评论

    public Moments() {
    }

    public Moments(String headPortraitUrl, String name, String content, List<String> pictureUrl) {
        this.headPortraitUrl = headPortraitUrl;
        this.name = name;
        this.content = content;
        this.pictureUrl = pictureUrl;
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
