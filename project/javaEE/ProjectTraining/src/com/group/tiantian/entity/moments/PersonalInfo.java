package com.group.tiantian.entity.moments;

public class PersonalInfo {
	private String name;//�����ǳ�
    private String photoUrl;//����ͷ���ַ
    private String personalPhone;//���˵绰����

    public PersonalInfo() {
    }

    public PersonalInfo(String name, String photoUrl, String personalPhone) {
        this.name = name;
        this.photoUrl = photoUrl;
        this.personalPhone = personalPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPersonalPhone() {
        return personalPhone;
    }

    public void setPersonalPhone(String personalPhone) {
        this.personalPhone = personalPhone;
    }

}
