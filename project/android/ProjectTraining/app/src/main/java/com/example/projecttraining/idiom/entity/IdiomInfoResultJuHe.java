package com.example.projecttraining.idiom.entity;

import java.util.List;

/**
 * 2020-11-30
 * @author lrf
 */
public class IdiomInfoResultJuHe {
    private String bushou;
    private String head;
    private String pinyin;
    private String chengyujs;
    private String from_;
    private String example;
    private String yufa;
    private String ciyujs;
    private String yinzhengjs;
    private List<String> tongyi;
    private List<String> fanyi;

    public String getBushou() {
        return bushou;
    }

    public void setBushou(String bushou) {
        this.bushou = bushou;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getChengyujs() {
        return chengyujs;
    }

    public void setChengyujs(String chengyujs) {
        this.chengyujs = chengyujs;
    }

    public String getFrom_() {
        return from_;
    }

    public void setFrom_(String from_) {
        this.from_ = from_;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getYufa() {
        return yufa;
    }

    public void setYufa(String yufa) {
        this.yufa = yufa;
    }

    public String getCiyujs() {
        return ciyujs;
    }

    public void setCiyujs(String ciyujs) {
        this.ciyujs = ciyujs;
    }

    public String getYinzhengjs() {
        return yinzhengjs;
    }

    public void setYinzhengjs(String yinzhengjs) {
        this.yinzhengjs = yinzhengjs;
    }

    public List<String> getTongyi() {
        return tongyi;
    }

    public void setTongyi(List<String> tongyi) {
        this.tongyi = tongyi;
    }

    public List<String> getFanyi() {
        return fanyi;
    }

    public void setFanyi(List<String> fanyi) {
        this.fanyi = fanyi;
    }

    public IdiomInfoResultJuHe() {
    }

    public IdiomInfoResultJuHe(String bushou, String head, String pinyin, String chengyujs, String from_, String example, String yufa, String ciyujs, String yinzhengjs, List<String> tongyi, List<String> fanyi) {
        this.bushou = bushou;
        this.head = head;
        this.pinyin = pinyin;
        this.chengyujs = chengyujs;
        this.from_ = from_;
        this.example = example;
        this.yufa = yufa;
        this.ciyujs = ciyujs;
        this.yinzhengjs = yinzhengjs;
        this.tongyi = tongyi;
        this.fanyi = fanyi;
    }

    @Override
    public String toString() {
        return "IdiomInfoResultJuHe{" +
                "bushou='" + bushou + '\'' +
                ", head='" + head + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", chengyujs='" + chengyujs + '\'' +
                ", from_='" + from_ + '\'' +
                ", example='" + example + '\'' +
                ", yufa='" + yufa + '\'' +
                ", ciyujs='" + ciyujs + '\'' +
                ", yinzhengjs='" + yinzhengjs + '\'' +
                ", tongyi=" + tongyi +
                ", fanyi=" + fanyi +
                '}';
    }
}
