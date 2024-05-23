package com.example.JavaProject.SNS;

import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.example.JavaProject.JDBC.MemDAO;
import com.example.JavaProject.JDBC.MemDTO;

@SuppressWarnings("serial")
public class Login_Login extends JFrame {
	Font myFont = new Font("굴림", Font.PLAIN, 16);
	private JPanel loginPanel = new JPanel((null));
	private JLabel idLabel = new JLabel("아이디 ");
	private JLabel pwLabel = new JLabel("비밀번호 ");
	private JTextField IDText = new JTextField();
	private JPasswordField pwText = new JPasswordField();
	private JButton loginBt = new JButton("로그인");
	private JButton CtAccountBt = new JButton("회원가입");
	private JButton idpwSearchBt = new JButton("PW/ID 찾기");
	private String IDset = "false";
	
	public Login_Login() {
		
		setContentPane(loginPanel);
		setSize(440, 185);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("로그인");
		
		idLabel.setBounds( 27, 10, 125, 40);  
		IDText.setBounds (77, 10, 250, 40);
		pwLabel.setBounds( 10, 55, 125, 40);
		pwText.setBounds (77, 55, 250, 40);
		loginBt.setBounds	  (332, 10, 88, 85);
		CtAccountBt.setBounds (77, 100, 120, 40);
		idpwSearchBt.setBounds(202, 100, 120, 40);
		
		loginPanel.add(idLabel).setFont(myFont);
		loginPanel.add(IDText).setFont(myFont);
		loginPanel.add(pwLabel).setFont(myFont);
		loginPanel.add(pwText).setFont(myFont);
		loginPanel.add(loginBt).setFont(myFont);
		loginPanel.add(CtAccountBt).setFont(myFont);
		loginPanel.add(idpwSearchBt).setFont(myFont);
		
		loginBt.addActionListener(new ActionListener() { // 로그인 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				
				searchViewID();
				if(IDset == "true") {
					JOptionPane.showMessageDialog(null, 
							"로그인 성공", "로그인 확인!",JOptionPane.DEFAULT_OPTION);
					setVisible(false);
					dispose();
					new Note_Main(IDText.getText());
					return;
				}
			}
		});
		
		CtAccountBt.addActionListener(new ActionListener() { //회원가입
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login_SingUp();
			}
		});
		
		idpwSearchBt.addActionListener(new ActionListener() { //ID/PW 찾기
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login_Find_IDPW();
			}
		});
		
		
		
	}//Login_Login_End
	
	public String getID() {
		return IDText.getText();
	}
	
	private void searchViewID() {
		int a = 0;
		String searchText = IDText.getText();
		String saveTextPW = "";
		String pw = new String(pwText.getPassword());
		
		if (!searchText.equals("")&& !pw.equals("")) { // 검색어 입력 필드가 비어있지 않은 경우에만 검색을 진행합니다.
			MemDAO dao = MemDAO.getInstance();
			ArrayList<MemDTO> aList = dao.searchProID(searchText);
			for (MemDTO dto : aList) {
				a++;
				saveTextPW = dto.getPW();
			}
			
			if(a == 0) {
				JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호를 디시 입력해주십시오.");
			}else {
				if(pw.equals(saveTextPW)) {
					IDset = "true";
				}else {
					JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호를 디시 입력해주십시오.");
				}
			}
		}else {
			JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호를 입력하셔야 됩니다");
		}
	}//serchViewID End
	
	public static void main(String[] args) {
		new Login_Login();
	}//main End
}//class End