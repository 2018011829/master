package com.example.projecttraining.contact.dao;

import com.example.projecttraining.contact.dao.Parent;

public class ContactsStatus {
    private int id;
    private Parent from;
    private Parent to;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Parent getFrom() {
        return from;
    }

    public void setFrom(Parent from) {
        this.from = from;
    }

    public Parent getTo() {
        return to;
    }

    public void setTo(Parent to) {
        this.to = to;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ContactsStatus [id=" + id + ", from=" + from + ", to=" + to + ", status=" + status + "]";
    }

}
