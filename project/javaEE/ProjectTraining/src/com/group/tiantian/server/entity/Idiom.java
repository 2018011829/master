package com.group.tiantian.server.entity;

public class Idiom {

	private int id;
	private String idiom;
	private String idiomType;
	
	public Idiom() {
		super();
	}
	public Idiom(int id, String idiom, String idiomType) {
		super();
		this.id = id;
		this.idiom = idiom;
		this.idiomType = idiomType;
	}
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
	public String getIdiomType() {
		return idiomType;
	}
	public void setIdiomType(String idiomType) {
		this.idiomType = idiomType;
	}
	@Override
	public String toString() {
		return "Idiom [id=" + id + ", idiom=" + idiom + ", idiomType=" + idiomType + "]";
	}
	
}
