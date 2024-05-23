package com.example.JavaProject.SNS;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import com.example.JavaProject.JDBC.MemDAO_Chat;
import com.example.JavaProject.JDBC.MemDTO_Chat;

@SuppressWarnings("serial")
public class Note_Chat extends JFrame implements ActionListener{
	Font myFont = new Font("굴림", Font.PLAIN, 16);
	private JPanel displayPanel; 
	private JPanel inputPanel;

	private JTextArea display;
	private JTextField input;
	
	private String Title,id;
	
	public Note_Chat(String id, String Title) {
		this.Title = Title;
		this.id = id;
		this.setTitle("채팅창");
		this.setSize(335, 495);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		displayPanel = new JPanel();
		displayPanel.setLayout(new FlowLayout());
		display = new JTextArea(21, 22);
		display.setFont(myFont);
		display.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(display);
		displayPanel.add(scroll);
		
		inputPanel = new JPanel();
	
		inputPanel.setLayout(new FlowLayout());
	
		input = new JTextField(22);
	
		input.setFont(myFont);
	
		input.addActionListener((ActionListener) this);
	
		 
	
		inputPanel.add(input);
		
		this.setLayout(new BorderLayout());
	
		this.add(displayPanel, BorderLayout.CENTER);
	
		this.add(inputPanel, BorderLayout.SOUTH);
		 
	
		this.setVisible(true);
		searchView();		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == input){
		display.setText(null);
		insertView();
		searchView();
		input.selectAll();
		}
	}
	private void searchView() {

		String searchText = Title;
		if (!searchText.equals("")) { // 검색어 입력 필드가 비어있지 않은 경우에만 검색을 진행합니다.
			
			MemDAO_Chat dao = MemDAO_Chat.getInstance();
			ArrayList<MemDTO_Chat> aList = dao.searchPro(searchText);
			for (MemDTO_Chat dto : aList) {
				display.append( id+": " + dto.getDescription() + "\n");
			}
		} else {
			JOptionPane.showMessageDialog(this, "검색할 내용을 입력해주세요.");
		}

	}
	private void insertView() {
		if (!input.getText().equals("") ) {
			MemDTO_Chat dto = new MemDTO_Chat();
			dto.setTitle(Title);
			dto.setUserID(id);
			dto.setDescription(input.getText());
			
			MemDAO_Chat dao = MemDAO_Chat.getInstance();
			dao.insertPro(dto);
		} else {
			JOptionPane.showMessageDialog(this, "모든 필드에 값을 입력하여주세요.");
		}

	}

}
