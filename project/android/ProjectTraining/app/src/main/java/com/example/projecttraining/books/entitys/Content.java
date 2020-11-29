package com.example.projecttraining.books.entitys;

import java.io.Serializable;

public class Content implements Serializable {
	private String articleName;
	private String contentName;
	private int start;
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public Content(String articleName, String contentName, int start) {
		super();
		this.articleName = articleName;
		this.contentName = contentName;
		this.start = start;
	}
	@Override
	public String toString() {
		return "Content [articleName=" + articleName + ", contentName=" + contentName + ", start=" + start + "]";
	}

}
