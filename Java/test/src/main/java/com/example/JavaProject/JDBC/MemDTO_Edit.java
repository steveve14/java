package com.example.JavaProject.JDBC;

public class MemDTO_Edit {
	private int num;
	private String Title;
	private String UserID;
	private String time;
	public MemDTO_Edit() {
		
	}
	public MemDTO_Edit(int num, String title,  String userID, String time) {
		this.num = num;
		this.Title = title;
		this.UserID = userID;
		this.time = time;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String Title) {
		this.Title = Title;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String UserID) {
		this.UserID = UserID;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	

} // end class
