package com.group.tiantian.entity;

/**
 * 2020-11-25
 * @author lrf
 */
public class Idiom {
	private int id;
	private String idiom;
	private int classification;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdiom() {
		return idiom;
	}

	public void setIdiom(String idiom) {
		this.idiom = idiom;
	}

	public int getClassification() {
		return classification;
	}

	public void setClassification(int classification) {
		this.classification = classification;
	}

	public Idiom() {
		super();
	}

	public Idiom(int id, String idiom, int classification) {
		super();
		this.id = id;
		this.idiom = idiom;
		this.classification = classification;
	}

	@Override
	public String toString() {
		return "Idiom [id=" + id + ", idiom=" + idiom + ", classification=" + classification + "]";
	}

}
