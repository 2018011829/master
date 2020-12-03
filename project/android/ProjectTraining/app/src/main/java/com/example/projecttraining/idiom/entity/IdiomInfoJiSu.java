package com.example.projecttraining.idiom.entity;

/**
 * 2020-12-2
 * @author lrf
 */
public class IdiomInfoJiSu {
    private int status;
    private String msg;
    private IdiomInfoResultJiSu idiomInfoResultJiSu;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public IdiomInfoResultJiSu getIdiomInfoResultJiSu() {
        return idiomInfoResultJiSu;
    }

    public void setIdiomInfoResultJiSu(IdiomInfoResultJiSu idiomInfoResultJiSu) {
        this.idiomInfoResultJiSu = idiomInfoResultJiSu;
    }

    public IdiomInfoJiSu() {
    }

    public IdiomInfoJiSu(int status, String msg, IdiomInfoResultJiSu idiomInfoResultJiSu) {
        this.status = status;
        this.msg = msg;
        this.idiomInfoResultJiSu = idiomInfoResultJiSu;
    }

    @Override
    public String toString() {
        return "IdiomInfoJiSu{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", idiomInfoResultJiSu=" + idiomInfoResultJiSu +
                '}';
    }
}
