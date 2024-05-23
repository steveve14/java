package com.example.JavaProject.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemDAO_Chat {

	Connection conn;
	ResultSet rs;
	PreparedStatement pstmt;
	
	//싱글톤 패턴 --- 생성 시작
	private static MemDAO_Chat dao = new MemDAO_Chat();
	
	private MemDAO_Chat() {}

	public static MemDAO_Chat getInstance() {
		return dao;
	}
	//싱글톤 패턴 --- 생성 끝
	
	@SuppressWarnings("deprecation")
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
	
	public ArrayList<MemDTO_Chat> selectPro()
	{
		ArrayList<MemDTO_Chat> aList = new ArrayList<MemDTO_Chat>(); //db에서 넘겨온값을 저장.
		try {
			
			conn = getCon();
			
			//3. PreparedStatement 객체생성
			String sql = "select * from sns order by num";
			pstmt = conn.prepareStatement(sql);
			
			//4. 쿼리문을 실행
			rs = pstmt.executeQuery();
			
			//레코드 커서 이동
			while(rs.next())
			{
				// 각 멤버변수에 값을 저장
				MemDTO_Chat dto = new MemDTO_Chat();
				dto.setTitle(rs.getString("Title")); 
				dto.setUserID(rs.getString("UserID")); 
				dto.setDescription(rs.getString("Description")); 
				
				
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
	
	public void insertPro(MemDTO_Chat dto) {
		
		try {
			
			conn = getCon();

			//insert into mem(num, name, age, loc) values(mem_num_seq_nextval, '이만복', 40, '제주')
			String sql = "insert into chat(Titel, UserId, Description) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getUserID());
			pstmt.setString(3, dto.getDescription());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();
		}
		
	} //end insertPro()

	
	public ArrayList<MemDTO_Chat> searchPro(String Title)
	{
		ArrayList<MemDTO_Chat> aList = new ArrayList<MemDTO_Chat>();
		
		try {
			
			conn = getCon();
			
			//select * from mem where name like '%삼%';
			String sql = "select * from chat where Titel like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+Title+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				MemDTO_Chat dto = new MemDTO_Chat();
				dto.setTitle(rs.getString("Titel")); 
				dto.setUserID(rs.getString("UserID")); 
				dto.setDescription(rs.getString("Description")); 
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
	
	
} //end class

