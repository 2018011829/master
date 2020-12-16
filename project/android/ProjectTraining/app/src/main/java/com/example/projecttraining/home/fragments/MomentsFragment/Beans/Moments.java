package com.example.projecttraining.home.fragments.MomentsFragment.Beans;

public class Moments {//动态类（每个说说）
    private int id;//id
    private String phoneNumber;//发表人手机号
    private String headPortraitUrl;//用户头像地址
    private String name;//用户昵称
    private String remark;//用户备注
    private String content;//用户发表的文字内容
    private String pictureUrl;//用户发表的图片路径(多张图片)
    private int praiseNumber;//点赞数
    private String likeGiveName;//点赞人的昵称
    private String comments;//用户评论
    private int likegiveboolen;//当前用户是否已经点赞该说说
    private String replyContent;//回复信息
    private String attentionList;//关注列表

    public Moments() {
    }
    public Moments(String content) {
        this.content = content;
    }

    public Moments(String headPortraitUrl, String name, String content, String pictureUrl) {
        this.headPortraitUrl = headPortraitUrl;
        this.name = name;
        this.content = content;
        this.pictureUrl = pictureUrl;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getLikegiveboolen() {
        return likegiveboolen;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public void setLikegiveboolen(int likegiveboolen) {
        this.likegiveboolen = likegiveboolen;
    }

    public String getAttentionList() {
        return attentionList;
    }
    public void setAttentionList(String attentionList) {
        this.attentionList = attentionList;
    }

    @Override
    public String toString() {
        return "Moments{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", headPortraitUrl='" + headPortraitUrl + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", content='" + content + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", praiseNumber=" + praiseNumber +
                ", likeGiveName='" + likeGiveName + '\'' +
                ", comments='" + comments + '\'' +
                ", likegiveboolen=" + likegiveboolen +
                ", replyContent='" + replyContent + '\'' +
                ", attentionList='" + attentionList + '\'' +
                '}';
    }
}
