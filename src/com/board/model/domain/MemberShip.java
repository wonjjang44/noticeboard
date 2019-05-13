package com.board.model.domain;

public class MemberShip {
	private int membership_id;
	private String id;
	private String password;
	private String name;
	private String gender;
	
	public int getMembership_id() {
		return membership_id;
	}
	public void setMembership_id(int membership_id) {
		this.membership_id = membership_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
