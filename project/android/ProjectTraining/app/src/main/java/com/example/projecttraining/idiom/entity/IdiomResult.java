package com.example.projecttraining.idiom.entity;

import java.util.List;

/**
 * 2020-11-25
 * @author lrf
 */
public class IdiomResult {
    private int status;
    private String msg;
    private List<Result> result;

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

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "IdiomResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
