package com.example.projecttraining.idiom.entity;

/**
 * 2020-11-30
 * @author lrf
 */
public class IdiomInfoJuHe {
    private int error_code;
    private String reason;
    private IdiomInfoResultJuHe idiomInfoResultJuHe;

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

    public IdiomInfoResultJuHe getIdiomInfoResultJuHe() {
        return idiomInfoResultJuHe;
    }

    public void setIdiomInfoResultJuHe(IdiomInfoResultJuHe idiomInfoResultJuHe) {
        this.idiomInfoResultJuHe = idiomInfoResultJuHe;
    }

    public IdiomInfoJuHe() {
    }

    public IdiomInfoJuHe(int error_code, String reason, IdiomInfoResultJuHe idiomInfoResultJuHe) {
        this.error_code = error_code;
        this.reason = reason;
        this.idiomInfoResultJuHe = idiomInfoResultJuHe;
    }

    @Override
    public String toString() {
        return "IdiomInfoJuHe{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", idiomInfoResultJuHe=" + idiomInfoResultJuHe +
                '}';
    }
}
