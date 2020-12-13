package com.example.projecttraining.contact.dao;
public class ParentInfo {
    private String phone;
    private String nickname;
    private String avatar;
    private String remark;
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Override
    public String toString() {
        return "ParentInfo [phone=" + phone + ", nickname=" + nickname + ", avatar=" + avatar + ", remark=" + remark
                + "]";
    }

}
