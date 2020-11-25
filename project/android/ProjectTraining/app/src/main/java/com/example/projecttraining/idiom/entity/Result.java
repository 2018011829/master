package com.example.projecttraining.idiom.entity;

/**
 * 2020-11-25
 * @author lrf
 */
public class Result {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                '}';
    }
}
