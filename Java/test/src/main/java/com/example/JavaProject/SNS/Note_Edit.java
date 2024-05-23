package com.example.JavaProject.SNS;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.example.JavaProject.JDBC.MemDAO_Edit;
import com.example.JavaProject.JDBC.MemDTO_Edit;

@SuppressWarnings("serial")
public class Note_Edit extends JFrame {
	Font myFont = new Font("굴림", Font.PLAIN, 16);
	LineBorder myBorder = new LineBorder(Color.black, 1, true);
	private String id,IDset;
	private JPanel loginPanel = new JPanel(null);
	private JScrollPane scroll;
	
	
	private JLabel TitelLabel = new JLabel("제목");
	private JTextField TitelText = new JTextField();
	private JButton TitelBt = new JButton("추가");
	
	private JTable table;
	private JButton deleteBt = new JButton("삭제");
	private DefaultTableModel model;
	
	int numSel;

	public Note_Edit(String id) {
		this.id = id;
		String[] columnNames = new String[] { "num","Titel","upime" };
		model = new DefaultTableModel(columnNames, 0) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		table = new JTable(model);
		scroll = new JScrollPane(table);
		
		setContentPane(loginPanel);
		setSize(335, 495);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("게시판_삭제/추가");
		
		TitelLabel.setBounds(10, 0, 80, 40);
		TitelText.setBounds(10, 30, 300, 40);
		TitelBt.setBounds(10, 75, 300, 40);
		scroll.setBounds(10, 120, 300, 280);
		deleteBt.setBounds(10, 410, 300, 40);
		
		loginPanel.add(TitelLabel).setFont(myFont);
		loginPanel.add(TitelText).setFont(myFont);
		loginPanel.add(TitelBt).setFont(myFont);
//		loginPanel.add(table).setFont(myFont);
		loginPanel.add(deleteBt).setFont(myFont);
		loginPanel.add(scroll).setFont(myFont);
		
		searchView();
		
		TitelBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "아이디 비번 찾기 기능~~", "아이디/비밀번호 찾기", JOptionPane.DEFAULT_OPTION);
				searchViewID();
				if(IDset.equals("true")){
					insertView();
					searchView();
					TitelText.setText("");
				}
			}
		});
		

		deleteBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "아이디 비번 찾기 기능~~", "아이디/비밀번호 찾기", JOptionPane.DEFAULT_OPTION);
				deleteView();
				searchView();
				TitelText.setText("");
			}
		});
	}
	
	private void searchViewID() {
		int a = 0;
		String searchText = TitelText.getText();
		if (!searchText.equals("")) { // 검색어 입력 필드가 비어있지 않은 경우에만 검색을 진행합니다.
			MemDAO_Edit dao = MemDAO_Edit.getInstance();
			ArrayList<MemDTO_Edit> aList = dao.searchProID(searchText);
			for (@SuppressWarnings("unused") MemDTO_Edit dto : aList) {
				a++;
						};
			if(a == 0) {
				JOptionPane.showMessageDialog(this, "제목 사용 가능.");
				IDset = "true";
			}else {
				JOptionPane.showMessageDialog(this, "제목 사용 불가.");
				TitelText.setText("");
				IDset = "false";
						}
		} else {
			JOptionPane.showMessageDialog(this, "다시 입력해주세요.");
		}
	} // end searchView()
	
	private void deleteView() {

		int row = table.getSelectedRow();
		if (row != -1) {
			MemDAO_Edit dao = MemDAO_Edit.getInstance();
			MemDTO_Edit dto = new MemDTO_Edit();
			dto.setNum(Integer.parseInt((String) table.getValueAt(row, 0)));
			dao.deletePro(dto);
			JOptionPane.showMessageDialog(this, "삭제 완료.");
		} else {
			JOptionPane.showMessageDialog(this, "삭제할 행을 선택해주세요.");
		}

	} // end deleteView()
	
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if (obj == table && e.getClickCount() == 2)
			setInputRecord();
	} // end mouseClicked()
	
	private void insertView() {
		if (!TitelText.getText().equals("")) {
			model.setRowCount(0);
			MemDTO_Edit dto = new MemDTO_Edit();
			dto.setTitle(TitelText.getText());
			dto.setUserID(id);
			MemDAO_Edit dao = MemDAO_Edit.getInstance();
			try {
				dao.insertPro(dto);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, "모든 필드에 값을 입력하여주세요.");
		}

	} 
	
	
	private void searchView() {

		String searchText = id;
		if (!searchText.equals("")) { // 검색어 입력 필드가 비어있지 않은 경우에만 검색을 진행합니다.
			model.setRowCount(0);
			MemDAO_Edit dao = MemDAO_Edit.getInstance();
			ArrayList<MemDTO_Edit> aList = dao.searchPro(searchText);
			for (MemDTO_Edit dto : aList) {
				String[] line = new String[] { 
						String.valueOf(dto.getNum()), 
									   dto.getTitle(),
									   dto.getTime()	   
						};
				model.addRow(line);
			}
		} else {
			JOptionPane.showMessageDialog(this, "검색할 내용을 입력해주세요.");
		}

	} // end searchView()
	private void setInputRecord() {

		numSel = table.getSelectedRow();
		TitelText.setText(table.getValueAt(numSel, 1).toString());
	}
	
	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}
}