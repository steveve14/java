package com.example.JavaProject.SNS;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import com.example.JavaProject.JDBC.MemDAO;
import com.example.JavaProject.JDBC.MemDTO;

@SuppressWarnings("serial")
public class Login_Find_IDPW extends JFrame {

	Font myFont = new Font("굴림", Font.PLAIN, 16);
	
	private String IDset = "";
	private String PWset = "";
	
	private JPanel loginPanel = new JPanel(null);
	
	private JLabel ID = new JLabel("아이디 찾기");
	private JButton IDBt = new JButton("확인");
	private JLabel NameLabel_ID = new JLabel("이름");
	private JTextField NameText_ID = new JTextField();
	private JLabel EmailLabel_ID = new JLabel("이메일");
	private JTextField EmailText_ID = new JTextField();
	
	private JLabel PW = new JLabel("비밀번호 찾기");
	private JButton PWBt = new JButton("확인");
	private JLabel NameLabel_PW = new JLabel("이름");
	private JTextField NameText_PW = new JTextField();
	private JLabel IdLabel_PW = new JLabel("아이디");
	private JTextField IdText_PW = new JTextField();
	private JLabel EmailLabel_PW = new JLabel("이메일");
	private JTextField EmailText_PW = new JTextField();
	
	public Login_Find_IDPW() {
		setContentPane(loginPanel);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("ID/PW찾기");
		
		ID.setBounds(100, 10, 200, 35);
		IDBt.setBounds(285, 50, 80, 75);
		NameLabel_ID.setBounds(40, 50, 80, 35);
		NameText_ID.setBounds(80, 50, 200, 35);
		EmailLabel_ID.setBounds(25, 90, 80, 35);
		EmailText_ID.setBounds(80, 90, 200, 35);
		
		PW.setBounds(100, 160, 200, 35);
		PWBt.setBounds(285, 200, 80, 75);
		NameLabel_PW.setBounds(40, 200, 80, 35);
		NameText_PW.setBounds(80, 200, 200, 35);
		IdLabel_PW.setBounds(25, 240, 80, 35);
		IdText_PW.setBounds(80, 240, 200, 35);
		EmailLabel_PW.setBounds(25, 280, 80, 35);
		EmailText_PW.setBounds(80, 280, 200, 35);
		
		loginPanel.add(ID).setFont(myFont);
		loginPanel.add(IDBt).setFont(myFont);
		loginPanel.add(NameLabel_ID).setFont(myFont);
		loginPanel.add(NameText_ID).setFont(myFont);
		loginPanel.add(EmailLabel_ID).setFont(myFont);
		loginPanel.add(EmailText_ID).setFont(myFont);
		
		loginPanel.add(PW).setFont(myFont);
		loginPanel.add(PWBt).setFont(myFont);
		loginPanel.add(NameLabel_PW).setFont(myFont);
		loginPanel.add(NameText_PW).setFont(myFont);
		loginPanel.add(IdLabel_PW).setFont(myFont);
		loginPanel.add(IdText_PW).setFont(myFont);
		loginPanel.add(EmailLabel_PW).setFont(myFont);
		loginPanel.add(EmailText_PW).setFont(myFont);
		
		
		IDBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchView_ID();
				if(IDset.equals("")) {
					
				}else {
					JOptionPane.showMessageDialog(null, "아이디: " + IDset, "아이디 찾기", JOptionPane.DEFAULT_OPTION);
					IDset = "";
				}
				
			}
		});
		
		PWBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchView_PW();
				if(PWset.equals("")) {
					
				}else {
					JOptionPane.showMessageDialog(null, "PW: " + PWset, "아이디 찾기", JOptionPane.DEFAULT_OPTION);
					PWset = "";
				}
			}
		});
	}
	private void searchView_ID() {
		int a = 0;
		String searchText = EmailText_ID.getText().trim();
		String Name = NameText_ID.getText().trim();
		String saveTextID = "";
		if (!searchText.equals("")&& !Name.equals("")) { 
			MemDAO dao = MemDAO.getInstance();
			ArrayList<MemDTO> aList = dao.searchProEmail(searchText);
			for (MemDTO dto : aList) {
				if(searchText.equals(dto.getEmail()) && Name.equals(dto.getName().trim())) {
					saveTextID = dto.getID();
					a++;
				}
			}
			if(a == 0) {
				JOptionPane.showMessageDialog(this, "이름 또는 이메일를 디시 입력해주십시오2.");
			}else {IDset = saveTextID;}
		}else {
			JOptionPane.showMessageDialog(this, "이름 또는 이메일를 입력하셔야 됩니다");
		}
	}
	private void searchView_PW() {
		int a = 0;
		String searchText = EmailText_PW.getText().trim();
		String Name = NameText_PW.getText().trim();
		String ID = IdText_PW.getText().trim();
		String saveText = "";
		
		if (!searchText.equals("")&& !Name.equals("")&& !ID.equals("")) { // 검색어 입력 필드가 비어있지 않은 경우에만 검색을 진행합니다.
			MemDAO dao = MemDAO.getInstance();
			ArrayList<MemDTO> aList = dao.searchProEmail(searchText);
			for (MemDTO dto : aList) {
				if(searchText.equals(dto.getEmail()) && Name.equals(dto.getName().trim()) && ID.equals(dto.getID().trim())) {
					saveText = dto.getPW();
					a++;
				}
			}
			
			if(a == 0) {
				JOptionPane.showMessageDialog(this, "이름/아이디 또는 이메일를 입력하셔야 됩니다2.");
			}else {
				PWset = saveText;
			}
		}else {
			JOptionPane.showMessageDialog(this, "이름/아이디 또는 이메일를 입력하셔야 됩니다.");
		}
	}
	
	
}
