package com.board.model.domain;

public class MyBoard {
	private int myboard_id;
	private int memberShip_id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	
	public int getMyboard_id() {
		return myboard_id;
	}
	public void setMyboard_id(int myboard_id) {
		this.myboard_id = myboard_id;
	}
	public int getMemberShip_id() {
		return memberShip_id;
	}
	public void setMemberShip_id(int memberShip_id) {
		this.memberShip_id = memberShip_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
