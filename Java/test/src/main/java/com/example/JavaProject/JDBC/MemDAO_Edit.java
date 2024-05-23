package com.example.JavaProject.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.io.*;

public class MemDAO_Edit {

	Connection conn;
	ResultSet rs;
	PreparedStatement pstmt;
	
	//싱글톤 패턴 --- 생성 시작
	private static MemDAO_Edit dao = new MemDAO_Edit();
	
	private MemDAO_Edit() {}

	public static MemDAO_Edit getInstance() {
		return dao;
	}
	//싱글톤 패턴 --- 생성 끝
	
	private Connection getCon() throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
		//1. 드라이버 연결
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			//2. 서버연결
			String url = "jdbc:mysql://localhost:3306/sakila";
			String user = "root";
			String password = "0410";
			conn = DriverManager.getConnection(url, user, password);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	} //end getCon()
	
	private void exit()
	{
		try {
			//5. 연결종료
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //end exit()
	
	public void deletePro(MemDTO_Edit dto)
	{
		try {
			
			conn = getCon();

			//delete from mem where num=2;
			String sql = "delete from board where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();
		}
	}
	
	public ArrayList<MemDTO_Edit> selectPro()
	{
		ArrayList<MemDTO_Edit> aList = new ArrayList<MemDTO_Edit>(); //db에서 넘겨온값을 저장.
		try {
			
			conn = getCon();
			
			//3. PreparedStatement 객체생성
			String sql = "select * from board order by num";
			pstmt = conn.prepareStatement(sql);
			
			//4. 쿼리문을 실행
			rs = pstmt.executeQuery();
			
			//레코드 커서 이동
			while(rs.next())
			{
				// 각 멤버변수에 값을 저장
				MemDTO_Edit dto = new MemDTO_Edit();
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("Title")); 
				dto.setUserID(rs.getString("UserID")); 
				dto.setTime(rs.getString("uptime"));
				
				//각 멤버변수에 값을 저장한 값을 ArrayList로 넘겨줌
				aList.add(dto);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();
		}
		return aList;
	} //end selectPro()
	
	public void insertPro(MemDTO_Edit dto) throws FileNotFoundException {
		
		try {
			
			conn = getCon();

			//insert into mem(num, name, age, loc) values(mem_num_seq_nextval, '이만복', 40, '제주')
			String sql = "insert into board(Title, UserId) values(?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getUserID());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();
		}
		//출처: https://hks003.tistory.com/11 [황규석 I'm third rate programmer:티스토리]

		
	} //end insertPro()

	
	public ArrayList<MemDTO_Edit> searchPro(String Title)
	{
		ArrayList<MemDTO_Edit> aList = new ArrayList<MemDTO_Edit>();
		
		try {
			
			conn = getCon();
			
			//select * from mem where name like '%삼%';
			String sql = "select * from board where UserID like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+Title+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				MemDTO_Edit dto = new MemDTO_Edit();
				dto.setNum(rs.getInt("num")); 
				dto.setTitle(rs.getString("Title")); 
				dto.setUserID(rs.getString("UserID")); 
				dto.setTime(rs.getString("uptime")); 
				aList.add(dto);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();	
		}

		return aList;
		
	} //end searchPro
	
	public ArrayList<MemDTO_Edit> searchProNum(int num)
	{
		ArrayList<MemDTO_Edit> aList = new ArrayList<MemDTO_Edit>();
		
		try {
			
			conn = getCon();
			
			//select * from mem where name like '%삼%';
			String sql = "select * from board where num like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+num+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				MemDTO_Edit dto = new MemDTO_Edit();
				dto.setNum(rs.getInt("num")); 
				dto.setTitle(rs.getString("Title")); 
				dto.setUserID(rs.getString("UserID")); 
				dto.setTime(rs.getString("uptime")); 
				aList.add(dto);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();	
		}

		return aList;
		
	} //end searchPro
	
	public ArrayList<MemDTO_Edit> searchProID(String name)
	{
		ArrayList<MemDTO_Edit> aList = new ArrayList<MemDTO_Edit>();
		
		try {
			
			conn = getCon();
			
			//select * from mem where name like '%삼%';
			String sql = "select * from board where Title like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				MemDTO_Edit dto = new MemDTO_Edit();
				dto.setTitle(rs.getString("Title"));
				dto.setUserID(rs.getString("UserID"));
				dto.setTime(rs.getString("uptime"));
				aList.add(dto);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();	
		}

		return aList;
		
	} //end searchPro2
	
	
} //end class

