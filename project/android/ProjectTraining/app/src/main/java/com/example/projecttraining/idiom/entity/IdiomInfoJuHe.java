package com.example.projecttraining.idiom.entity;

/**
 * 2020-12-2
 * @author lrf
 */
public class IdiomInfo {
    private int status;
    private String msg;
    private IdiomInfoResult idiomInfoResult;

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

    public IdiomInfoResult getIdiomInfoResult() {
        return idiomInfoResult;
    }

    public void setIdiomInfoResult(IdiomInfoResult idiomInfoResult) {
        this.idiomInfoResult = idiomInfoResult;
    }

    public IdiomInfo() {
    }

    public IdiomInfo(int status, String msg, IdiomInfoResult idiomInfoResult) {
        this.status = status;
        this.msg = msg;
        this.idiomInfoResult = idiomInfoResult;
    }

    @Override
    public String toString() {
        return "IdiomInfo{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", idiomInfoResult=" + idiomInfoResult +
                '}';
    }
}
