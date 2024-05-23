package com.example.JavaProject.SNS;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.example.JavaProject.JDBC.MemDAO_Edit;
import com.example.JavaProject.JDBC.MemDTO_Edit;


@SuppressWarnings("serial")
public class Note_Main extends JFrame{
	Font myFont = new Font("굴림", Font.PLAIN, 16);
	private String inTitel = "";
	
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scroll;
	
	private JPanel loginPanel = new JPanel(null);
	private JButton Uplode = new JButton("추가/삭제");
	private JButton txtBt = new JButton("채팅");
	private JButton RetxtBt = new JButton("새로고침");

	public Note_Main(String id) {
		setContentPane(loginPanel);
		setSize(335, 495);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("게시판");
		
		String[] columnNames = new String[] { "num", "Title","UserId" };
		model = new DefaultTableModel(columnNames, 0) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		table = new JTable(model);
        scroll = new JScrollPane(table);
        scroll.setViewportView(table);
		scroll.setBounds(10, 55, 300, 300);
        
		Uplode.setBounds(10, 10, 300, 40);
		txtBt.setBounds(10, 360, 300, 40);
		RetxtBt.setBounds(10, 405, 300, 40);
		loginPanel.add(scroll).setFont(myFont);
		loginPanel.add(Uplode).setFont(myFont);
		loginPanel.add(txtBt).setFont(myFont);
		loginPanel.add(RetxtBt).setFont(myFont);
		loginPanel.add(scroll);
		
		
		
		selectView();
		
		txtBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "회원가입 기능~~", "회원가입", JOptionPane.DEFAULT_OPTION);
				searchView();
				if(!inTitel.equals("")) {
					new Note_Chat(id,inTitel);
					selectView();
					inTitel = "";
				}
			}
		});	
		
		RetxtBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "회원가입 기능~~", "회원가입", JOptionPane.DEFAULT_OPTION);
				selectView();
			}
		});	
		
		Uplode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "업로드 기능~~ 기술부족으로 실패", "업로드", JOptionPane.DEFAULT_OPTION);
				new Note_Edit(id);
				selectView();
			}
		});	
	}
	private void selectView() {

		model.setRowCount(0);
		MemDAO_Edit dao = MemDAO_Edit.getInstance();

		ArrayList<MemDTO_Edit> aList = dao.selectPro();

		for (MemDTO_Edit dto : aList) {
			String[] line = new String[] { 
					String.valueOf(dto.getNum()), 
								   dto.getTitle(),
								   dto.getUserID()
					};

			model.addRow(line);
		}

	} // end selectView()
	
	private void searchView() {
		int row = table.getSelectedRow();
		if (row != -1) {
			inTitel = table.getModel().getValueAt(row, 1 )+"\t";
		} else {
			JOptionPane.showMessageDialog(this, "게시판을 선택해주세요.");
		}
		 // 검색어 입력 필드가 비어있지 않은 경우에만 검색을 진행합니다.
	}
	
}