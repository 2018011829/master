package com.group.tiantian.entity.moments;

public class Comment {
	private int id;//id
	private String name; //������
    private String content; //��������
    private int momentsId;
    public Comment(){

    }

    public Comment(String name, String content){
        this.name = name;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public int getMomentsId() {
		return momentsId;
	}

	public void setMomentsId(int momentsId) {
		this.momentsId = momentsId;
	}
    
    

}
