package com.example.projecttraining.contact;

public class Parent {
    private int id;
    private String phone;
    private String password;
    private String nickname;
    //用户头像的名称
    private String avator;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getAvator() {
        return avator;
    }
    public void setAvator(String avator) {
        this.avator = avator;
    }
    @Override
    public String toString() {
        return "Parent [id=" + id + ", phone=" + phone + ", password=" + password + ", nickname=" + nickname
                + ", avator=" + avator + "]";
    }

}

