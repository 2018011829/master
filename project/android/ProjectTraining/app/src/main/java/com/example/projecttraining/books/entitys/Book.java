package com.example.projecttraining.books.entitys;

import java.io.Serializable;

public class Book implements Serializable {
	private int id;
	private String name;
	private String introduce;
	private String type;
	private String img;
	private String content;
	private String author;
	private String grades;

	public String getGrades() {
		return grades;
	}

	public void setGrades(String grades) {
		this.grades = grades;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", name='" + name + '\'' +
				", introduce='" + introduce + '\'' +
				", type='" + type + '\'' +
				", img='" + img + '\'' +
				", content='" + content + '\'' +
				", author='" + author + '\'' +
				'}';
	}
}
