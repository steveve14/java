package com.example.JavaProject.JDBC;

public class MemDTO_Chat {
	private String Title;
	private String UserID;
	private String Description;
	public MemDTO_Chat() {
		
	}
	public MemDTO_Chat(String title,  String userID, String description) {
		this.Title = title;
		this.UserID = userID;
		this.Description = description;
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
	public String getDescription() {
		return Description;
	}
	public void setDescription(String Description) {
		this.Description = Description;
	}
	
	

} // end class
