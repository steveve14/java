package com.example.JavaProject.JDBC;

public class MemDTO {
	private int num;
	private String name;
	private int YYMMDD;
	private int Pnum1;
	private int Pnum2;
	private int Pnum3;
	private String gender;
	private String ID;
	private String PW;
	private String Email;
	public MemDTO() {
		
	}
	public MemDTO(int num,  String name, int YYMMDD, int Pnum1, int Pnum2, int Pnum3, String gender, String ID, String PW, String Email) {
		this.name = name;
		this.YYMMDD = YYMMDD;
		this.Pnum1 = Pnum1;
		this.Pnum2 = Pnum2;
		this.Pnum3 = Pnum3;
		this.gender = gender;
		this.ID = ID;
		this.PW = PW;
		this.Email = Email;	
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYYMMDD() {
		return YYMMDD;
	}
	public void setYYMMDD(int yYMMDD) {
		YYMMDD = yYMMDD;
	}
	public int getPnum1() {
		return Pnum1;
	}
	public void setPnum1(int pnum1) {
		Pnum1 = pnum1;
	}
	public int getPnum2() {
		return Pnum2;
	}
	public void setPnum2(int pnum2) {
		Pnum2 = pnum2;
	}
	public int getPnum3() {
		return Pnum3;
	}
	public void setPnum3(int pnum3) {
		Pnum3 = pnum3;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}

} // end class
