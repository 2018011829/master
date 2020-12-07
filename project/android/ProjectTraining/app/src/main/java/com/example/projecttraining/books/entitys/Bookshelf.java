package com.example.projecttraining.books.entitys;

public class Bookshelf {

    private String phoneNum;
    private String childName;
    private String bookName;
    public Bookshelf(String phoneNum, String childName, String bookName) {
        super();
        this.phoneNum = phoneNum;
        this.childName = childName;
        this.bookName = bookName;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getChildName() {
        return childName;
    }
    public void setChildName(String childName) {
        this.childName = childName;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    @Override
    public String toString() {
        return "Bookshelf [phoneNum=" + phoneNum + ", childName=" + childName + ", bookName=" + bookName + "]";
    }

}

