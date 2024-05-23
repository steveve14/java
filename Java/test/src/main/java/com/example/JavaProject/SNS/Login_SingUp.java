package com.example.JavaProject.SNS;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.example.JavaProject.JDBC.MemDAO;
import com.example.JavaProject.JDBC.MemDTO;

@SuppressWarnings("serial")
public class Login_SingUp extends JFrame {
	Font myFont = new Font("굴림", Font.PLAIN, 16);
	
	String Gender = "남";
	String IDset = "false";
	
	DefaultTableModel model;
	private JPanel loginPanel = new JPanel(null);
	
	private JLabel NameLabel = new JLabel("이름");
	private JTextField NameText = new JTextField();
	
	private JLabel YYMMDDLabel = new JLabel("생년월일");
	private JLabel YYMMDDLabel2 = new JLabel("6자리");
	private JTextField YYMMDDText = new JTextField();
	
	private JLabel Pnum1Label = new JLabel("전화번호");
	private JTextField  Pnum1Text = new JTextField();
	private JLabel Pnum2Label = new JLabel("-");
	private JTextField Pnum2Text = new JTextField();
	private JLabel Pnum3Label = new JLabel("-");
	private JTextField Pnum3Text = new JTextField();
	
	private JRadioButton manR = new JRadioButton("남", true);
	private JRadioButton womanR = new JRadioButton("여", false);
	private ButtonGroup group = new ButtonGroup();
	
	private JLabel IDLabel = new JLabel("아이디");
	private JTextField IDText = new JTextField();
	private JButton IDCkBt = new JButton("아이디 중복확인");
	
	private JLabel PWLabel1 = new JLabel("비밀번호");
	private JPasswordField PWText1 = new JPasswordField();
	private JLabel PWLabel2 = new JLabel("비밀번호");
	private JLabel PWLabel3 = new JLabel("확인");
	private JPasswordField PWText2 = new JPasswordField();
	
	private JLabel EmailLabel = new JLabel("이메일");
	private JTextField EmailText = new JTextField();
	
	private JButton AccountBt = new JButton("회원가입");
	
	private JTextArea NameAr = new JTextArea();
	private JTextArea YYMMDDAr = new JTextArea();
	private JTextArea  PnumAr = new JTextArea(); 
	private JTextArea IDAr = new JTextArea(); 
	private JTextArea PWAr = new JTextArea();
	private JTextArea EmailAr = new JTextArea();
	
	
	public Login_SingUp() {
		
		setContentPane(loginPanel);
		setSize(400, 460);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("회원가입");
		
		group.add(manR);
		group.add(womanR);
		
		NameAr.setBounds(340,10,40,35);
		NameLabel.setBounds(40, 10, 80, 35);
		NameText.setBounds(80, 10, 250, 35);
		
		YYMMDDAr.setBounds(340,50,40,35);
		YYMMDDLabel.setBounds(10, 45, 80, 35);
		YYMMDDLabel2.setBounds(30, 60, 80, 35);
		YYMMDDText.setBounds(80, 50, 250, 35);
		
		PnumAr.setBounds(340,90,40,35);
		Pnum1Label.setBounds(10, 90, 80, 35);
		Pnum1Text.setBounds(80, 90, 76, 35);
		Pnum2Label.setBounds(156, 90, 10, 35);
		Pnum2Text.setBounds(167, 90, 76, 35);
		Pnum3Label.setBounds(243, 90, 10, 35);
		Pnum3Text.setBounds(254, 90, 76, 35);
		
		manR.setBounds(80, 130, 125, 35);
		womanR.setBounds(205, 130, 125, 35);
		
		IDAr.setBounds(340,170,40,35);
		IDLabel.setBounds(25, 170, 80, 35);
		IDText.setBounds(80, 170, 250, 35);
		IDCkBt.setBounds(80, 210, 250, 35);
		
		PWAr.setBounds(340,250,40,35);
		PWLabel1.setBounds(10, 250, 80, 35);
		PWText1.setBounds(80, 250, 250, 35);
		PWLabel2.setBounds(10, 285, 80, 35);
		PWLabel3.setBounds(40, 300, 80, 35);
		PWText2.setBounds(80, 290, 250, 35);
		
		EmailAr.setBounds(340,330,40,35);
		EmailLabel.setBounds(25, 330, 80, 35);
		EmailText.setBounds(80, 330, 250, 35);
		
		AccountBt.setBounds(80, 370, 250, 35);
		
		
		NameAr.setEditable(false);
		NameAr.setBackground(Color.red);
		loginPanel.add(NameAr).setFont(myFont);
		loginPanel.add(NameLabel).setFont(myFont);
		loginPanel.add(NameText).setFont(myFont);
		
		YYMMDDAr.setEditable(false);
		YYMMDDAr.setBackground(Color.red);
		loginPanel.add(YYMMDDAr).setFont(myFont);
		loginPanel.add(YYMMDDLabel).setFont(myFont);
		loginPanel.add(YYMMDDLabel2).setFont(myFont);
		loginPanel.add(YYMMDDText).setFont(myFont);
		
		PnumAr.setEditable(false);
		PnumAr.setBackground(Color.red);
		loginPanel.add(PnumAr).setFont(myFont);
		loginPanel.add(Pnum1Label).setFont(myFont);
		loginPanel.add(Pnum1Text).setFont(myFont);
		loginPanel.add(Pnum2Label).setFont(myFont);
		loginPanel.add(Pnum2Text).setFont(myFont);
		loginPanel.add(Pnum3Label).setFont(myFont);
		loginPanel.add(Pnum3Text).setFont(myFont);
		
		loginPanel.add(manR).setFont(myFont);
		loginPanel.add(womanR).setFont(myFont);
		
		IDAr.setEditable(false);
		IDAr.setBackground(Color.red);
		loginPanel.add(IDAr).setFont(myFont);
		loginPanel.add(IDLabel).setFont(myFont);
		loginPanel.add(IDText).setFont(myFont);
		loginPanel.add(IDCkBt).setFont(myFont);
		
		PWAr.setEditable(false);
		PWAr.setBackground(Color.red);
		loginPanel.add(PWAr).setFont(myFont);
		loginPanel.add(PWLabel1).setFont(myFont);
		loginPanel.add(PWText1).setFont(myFont);
		loginPanel.add(PWLabel2).setFont(myFont);
		loginPanel.add(PWLabel3).setFont(myFont);
		loginPanel.add(PWText2).setFont(myFont);
		
		EmailAr.setEditable(false);
		EmailAr.setBackground(Color.red);
		loginPanel.add(EmailAr).setFont(myFont);
		loginPanel.add(EmailLabel).setFont(myFont);
		loginPanel.add(EmailText).setFont(myFont);
		
		loginPanel.add(AccountBt).setFont(myFont);
		
		manR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gender = "남";
			}
		});
		
		womanR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gender = "여";
			}
		});
		
		IDCkBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchViewID();
			}
		});

		AccountBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pw = new String(PWText1.getPassword());
				String pw2 = new String(PWText2.getPassword());
				
				if(IDset.equals("true") && pw.equals(pw2) && (YYMMDDText.getText()).length() == 6 &&
				  !NameText.getText().equals("") && !pw.equals("")&&((Pnum1Text.getText()).length())== 3 &&
				  (((Pnum2Text.getText()).length())== 3||((Pnum2Text.getText()).length())== 4) &&
				  ((Pnum3Text.getText()).length())== 4 && (EmailText.getText()).contains("@")== true
				  && EmailText.getText().length() > 3) {
					insertView();
					dispose();
					JOptionPane.showMessageDialog(null, "회원가입 완료", "회원가입 완료",JOptionPane.DEFAULT_OPTION);
				}else {
					if(NameText.getText().equals("")) {
						NameAr.setBackground(Color.red);
					}else {
						NameAr.setBackground(Color.green);
					}
					if(IDset.equals("false")) {	
						IDAr.setBackground(Color.red);
					}else {
						IDAr.setBackground(Color.green);
					}
					if(pw.equals(pw2) && !pw.equals("")) {
						PWAr.setBackground(Color.green);
					}else {
						PWAr.setBackground(Color.red);
					}
					if((YYMMDDText.getText()).length() == 6) {
						YYMMDDAr.setBackground(Color.green);
					}else {
						YYMMDDAr.setBackground(Color.red);
					}
					if(((Pnum1Text.getText()).length())== 3 &&
							(((Pnum2Text.getText()).length())== 3||((Pnum2Text.getText()).length())== 4) 
							&& ((Pnum3Text.getText()).length())== 4) {
						PnumAr.setBackground(Color.green);
					}else {
						PnumAr.setBackground(Color.red);
					}
					if((EmailText.getText()).contains("@")== true && EmailText.getText().length() > 3) {
						EmailAr.setBackground(Color.green);
					}else {
						EmailAr.setBackground(Color.red);
					}
				}
			}
		});
		
	}

	private void searchViewID() {
		int a = 0;
		String searchText = IDText.getText();
		if (!searchText.equals("")) { // 검색어 입력 필드가 비어있지 않은 경우에만 검색을 진행합니다.
			MemDAO dao = MemDAO.getInstance();
			ArrayList<MemDTO> aList = dao.searchProID(searchText);
			for (@SuppressWarnings("unused") MemDTO dto : aList) {
				a++;
						};
			if(a == 0) {
				JOptionPane.showMessageDialog(this, "아이디 사용 가능.");
				IDAr.setBackground(Color.GREEN);
				IDset = "true";
			}else {
				JOptionPane.showMessageDialog(this, "아이디 사용 불가.");
				IDText.setText("");
				IDAr.setBackground(Color.red);
				IDset = "false";
						}
		} else {
			JOptionPane.showMessageDialog(this, "다시 입력해주세요.");
		}
	} // end searchView()

	private void insertView() {
		
		String pw = new String(PWText1.getPassword());
		if (!NameText.getText().equals("") && !YYMMDDText.getText().equals("") && !Pnum1Text.getText().equals("")
				&& !Pnum2Text.getText().equals("") && !Pnum3Text.getText().equals("")
				&& !IDText.getText().equals("")&& !pw.equals("")&& !EmailText.getText().equals("")) {
			
			//&& !GenderText.getText().equals("")
			MemDTO dto = new MemDTO();
			dto.setName(NameText.getText());
			dto.setYYMMDD(Integer.parseInt(YYMMDDText.getText()));
			dto.setPnum1(Integer.parseInt(Pnum1Text.getText()));
			dto.setPnum2(Integer.parseInt(Pnum2Text.getText()));
			dto.setPnum3(Integer.parseInt(Pnum3Text.getText()));
			dto.setGender(Gender);
			dto.setID(IDText.getText());
			dto.setPW(pw);
			dto.setEmail(EmailText.getText());
			MemDAO dao = MemDAO.getInstance();
			dao.insertPro(dto);
		} else {
			JOptionPane.showMessageDialog(this, "모든 필드에 값을 입력하여주세요.");
		}

	} // end insertView()
	
}
