package com.example.projecttraining.idiom.entity;

public class IdiomInfo {
    private int error_code;
    private String reason;
    private IdiomInfoResult idiomInfoResult;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public IdiomInfoResult getIdiomInfoResult() {
        return idiomInfoResult;
    }

    public void setIdiomInfoResult(IdiomInfoResult idiomInfoResult) {
        this.idiomInfoResult = idiomInfoResult;
    }

    public IdiomInfo() {
    }

    public IdiomInfo(int error_code, String reason, IdiomInfoResult idiomInfoResult) {
        this.error_code = error_code;
        this.reason = reason;
        this.idiomInfoResult = idiomInfoResult;
    }

    @Override
    public String toString() {
        return "IdiomInfo{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", idiomInfoResult=" + idiomInfoResult +
                '}';
    }
}
