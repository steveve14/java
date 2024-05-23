package com.example.JavaProject.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemDAO {

	Connection conn;
	ResultSet rs;
	PreparedStatement pstmt;
	
	//싱글톤 패턴 --- 생성 시작
	private static MemDAO dao = new MemDAO();
	
	private MemDAO() {}

	public static MemDAO getInstance() {
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
	
	public ArrayList<MemDTO> selectPro()
	{
		ArrayList<MemDTO> aList = new ArrayList<MemDTO>(); //db에서 넘겨온값을 저장.
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
				MemDTO dto = new MemDTO();
				dto.setNum(rs.getInt("num")); 
				dto.setName(rs.getString("name")); 
				dto.setYYMMDD(rs.getInt("YYMMDD")); 
				dto.setPnum1(rs.getInt("Pnum1")); 
				dto.setPnum2(rs.getInt("Pnum2")); 
				dto.setPnum3(rs.getInt("Pnum3"));
				dto.setGender(rs.getString("gender"));
				dto.setID(rs.getString("ID"));
				dto.setPW(rs.getString("PW"));
				dto.setEmail(rs.getString("Email"));
				
				
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
	
	public void insertPro(MemDTO dto) {
		
		try {
			
			conn = getCon();

			//insert into mem(num, name, age, loc) values(mem_num_seq_nextval, '이만복', 40, '제주')
			String sql = "insert into sns(name,YYMMDD,Pnum1,Pnum2,Pnum3,gender,ID,PW,Email,num) values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getYYMMDD());
			pstmt.setInt(3, dto.getPnum1());
			pstmt.setInt(4, dto.getPnum2());
			pstmt.setInt(5, dto.getPnum3());
			pstmt.setString(6, dto.getGender());
			pstmt.setString(7, dto.getID());
			pstmt.setString(8, dto.getPW());
			pstmt.setString(9, dto.getEmail());
			pstmt.setInt(10, dto.getNum());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();
		}
		
	} //end insertPro()

	public void updatePro(MemDTO dto)
	{			
		try {

			conn = getCon();
			
			//update mem set name ='오달수' where num =2;
			String sql = "update sns set name = ?,YYMMDD= ?,Pnum1= ?,Pnum2= ?,Pnum3= ?,gender= ?,ID= ?,PW= ?,Email= ? where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getYYMMDD());
			pstmt.setInt(3, dto.getPnum1());
			pstmt.setInt(4, dto.getPnum2());
			pstmt.setInt(5, dto.getPnum3());
			pstmt.setString(6, dto.getGender());
			pstmt.setString(7, dto.getID());
			pstmt.setString(8, dto.getPW());
			pstmt.setString(9, dto.getEmail());
			pstmt.setInt(10, dto.getNum());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();
		}
		
	} //end updatePro()

	public void deletePro(MemDTO dto)
	{
		try {
			
			conn = getCon();

			//delete from mem where num=2;
			String sql = "delete from sns where num = ?";
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
	} //end deletePro()
	
	public ArrayList<MemDTO> searchPro(String name)
	{
		ArrayList<MemDTO> aList = new ArrayList<MemDTO>();
		
		try {
			
			conn = getCon();
			
			//select * from mem where name like '%삼%';
			String sql = "select * from sns where name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				MemDTO dto = new MemDTO();
				dto.setNum(rs.getInt("num")); 
				dto.setName(rs.getString("name")); 
				dto.setYYMMDD(rs.getInt("YYMMDD")); 
				dto.setPnum1(rs.getInt("Pnum1")); 
				dto.setPnum2(rs.getInt("Pnum2")); 
				dto.setPnum3(rs.getInt("Pnum3"));
				dto.setGender(rs.getString("gender"));
				dto.setID(rs.getString("ID"));
				dto.setPW(rs.getString("PW"));
				dto.setEmail(rs.getString("Email"));
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
	
	public ArrayList<MemDTO> searchProID(String name)
	{
		ArrayList<MemDTO> aList = new ArrayList<MemDTO>();
		
		try {
			
			conn = getCon();
			
			//select * from mem where name like '%삼%';
			String sql = "select * from sns where ID like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				MemDTO dto = new MemDTO();
				dto.setID(rs.getString("ID"));
				dto.setPW(rs.getString("PW"));
				dto.setEmail(rs.getString("Email"));
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
	
	public ArrayList<MemDTO> searchProEmail(String name)
	{
		ArrayList<MemDTO> aList = new ArrayList<MemDTO>();
		
		try {
			
			conn = getCon();
			
			//select * from mem where name like '%삼%';
			String sql = "select * from sns where Email like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				MemDTO dto = new MemDTO();
				dto.setNum(rs.getInt("num")); 
				dto.setName(rs.getString("name")); 
				dto.setYYMMDD(rs.getInt("YYMMDD")); 
				dto.setID(rs.getString("ID"));
				dto.setPW(rs.getString("PW"));
				dto.setEmail(rs.getString("Email"));
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
		
	}
	
	
} //end class

