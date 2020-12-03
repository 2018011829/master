package com.example.projecttraining.idiom.entity;

import java.util.List;

/**
 * 2020-12-2
 * @author lrf
 */
public class IdiomInfoResult {
    private String name;
    private String pronounce;
    private String content;
    private String comefrom;
    private List<String> antonym;
    private List<String> thesaurus;
    private String example;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComefrom() {
        return comefrom;
    }

    public void setComefrom(String comefrom) {
        this.comefrom = comefrom;
    }

    public List<String> getAntonym() {
        return antonym;
    }

    public void setAntonym(List<String> antonym) {
        this.antonym = antonym;
    }

    public List<String> getThesaurus() {
        return thesaurus;
    }

    public void setThesaurus(List<String> thesaurus) {
        this.thesaurus = thesaurus;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public IdiomInfoResult() {
    }

    public IdiomInfoResult(String name, String pronounce, String content, String comefrom, List<String> antonym, List<String> thesaurus, String example) {
        this.name = name;
        this.pronounce = pronounce;
        this.content = content;
        this.comefrom = comefrom;
        this.antonym = antonym;
        this.thesaurus = thesaurus;
        this.example = example;
    }

    @Override
    public String toString() {
        return "IdiomInfoResult{" +
                "name='" + name + '\'' +
                ", pronounce='" + pronounce + '\'' +
                ", content='" + content + '\'' +
                ", comefrom='" + comefrom + '\'' +
                ", antonym=" + antonym +
                ", thesaurus=" + thesaurus +
                ", example='" + example + '\'' +
                '}';
    }
}
