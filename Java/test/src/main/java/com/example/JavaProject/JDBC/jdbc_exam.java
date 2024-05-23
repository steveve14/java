package com.example.JavaProject.JDBC;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
class Memberinfo extends JFrame implements ActionListener, MouseListener {
	JTable table;
	DefaultTableModel model;
	JButton selectBtn, insertBtn, updateBtn, deleteBtn, searchBtn;
	JTextField nameTxt, YYMMDDTxt, Pnum1Txt, Pnum2Txt, Pnum3Txt, GenderTxt, IDTxt,PWTxt, EmailTxt;
	JTextField uNameTxt, uAgeTxt;
	JDialog jg, upDialog;
	int numSel;

	public Memberinfo() {

		String[] columnNames = new String[] { "num", "name", "YYDDMM", "Pnum1", "Pnum2", "Pnum3", "Gender", "ID", "PW", "Email" };
		model = new DefaultTableModel(columnNames, 0) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		table = new JTable(model);
		selectBtn = new JButton("조회");
		insertBtn = new JButton("입력");
		updateBtn = new JButton("수정");
		deleteBtn = new JButton("삭제");
		searchBtn = new JButton("검색");

		nameTxt = new JTextField(10);
		YYMMDDTxt = new JTextField(10);
		Pnum1Txt = new JTextField(10);
		Pnum2Txt = new JTextField(10);
		Pnum3Txt = new JTextField(10);
		GenderTxt = new JTextField(10);
		IDTxt = new JTextField(10);
		PWTxt = new JTextField(10);
		EmailTxt = new JTextField(10);
		
		JPanel jp1 = new JPanel(new GridLayout(9, 2));
		jp1.add(new JLabel("이름"));
		jp1.add(nameTxt);
		jp1.add(new JLabel("생년월일"));
		jp1.add(YYMMDDTxt);
		jp1.add(new JLabel("전화번호1"));
		jp1.add(Pnum1Txt);
		jp1.add(new JLabel("전화번호2"));
		jp1.add(Pnum2Txt);
		jp1.add(new JLabel("전화번호3"));
		jp1.add(Pnum3Txt);
		jp1.add(new JLabel("성별"));
		jp1.add(GenderTxt);
		jp1.add(new JLabel(" ID"));
		jp1.add(IDTxt);
		jp1.add(new JLabel(" PW"));
		jp1.add(PWTxt);
		jp1.add(new JLabel(" Email"));
		jp1.add(EmailTxt);

		JPanel jp2 = new JPanel();
		jp2.add(selectBtn);
		jp2.add(insertBtn);
		jp2.add(deleteBtn);
		jp2.add(updateBtn);
		jp2.add(searchBtn);

		setLayout(new GridLayout(2, 1));

		JPanel jp = new JPanel(new GridLayout(2, 1));
		jp.add(jp1);
		jp.add(jp2);

		add(jp);
		add(new JScrollPane(table));
		

		selectBtn.addActionListener(this);
		insertBtn.addActionListener(this);
		updateBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		searchBtn.addActionListener(this);

		table.addMouseListener(this);
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// end MemTest()

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		if (obj == selectBtn)
			selectView();
		else if (obj == insertBtn) {
			insertView();
			selectView();
			init();
		} else if (obj == updateBtn) {
			updateView();
			selectView();
			init();

		} else if (obj == deleteBtn) {
			deleteView();
			selectView();
			init();
		} else if (obj == searchBtn) {
			searchView();
			init();
		}

	} // end actionPerformed

	private void searchView() {

		String searchText = nameTxt.getText();
		if (!searchText.equals("")) { // 검색어 입력 필드가 비어있지 않은 경우에만 검색을 진행합니다.
			model.setRowCount(0);
			MemDAO dao = MemDAO.getInstance();
			ArrayList<MemDTO> aList = dao.searchPro(searchText);
			for (MemDTO dto : aList) {
				String[] line = new String[] { 
						String.valueOf(dto.getNum()), 
									   dto.getName(),
						String.valueOf(dto.getYYMMDD()), 
						String.valueOf(dto.getPnum1()), 
						String.valueOf(dto.getPnum2()),
						String.valueOf(dto.getPnum3()), 
									   dto.getGender(),
									   dto.getID(),
									   dto.getPW(),
									   dto.getEmail()
						};
				model.addRow(line);
			}
		} else {
			JOptionPane.showMessageDialog(this, "검색할 내용을 입력해주세요.");
		}

	} // end searchView()

	private void deleteView() {

		int row = table.getSelectedRow();
		if (row != -1) {
			MemDAO dao = MemDAO.getInstance();
			MemDTO dto = new MemDTO();
			dto.setNum(Integer.parseInt((String) table.getValueAt(row, 0)));
			dao.deletePro(dto);
		} else {
			JOptionPane.showMessageDialog(this, "삭제할 행을 선택해주세요.");
		}

	} // end deleteView()

	private void updateView() {

//		int num = table.getSelectedRow();
//		String name = nameTxt.getText().trim();
//		String age = String.valueOf(ageTxt.getText()).trim();
//		String loc = locTxt.getText().trim();
//		
//		table.setValueAt(name, numSel, 1);
//		table.setValueAt(age, numSel, 2);
//		table.setValueAt(loc, numSel, 3);
//		table.setValueAt(table.getValueAt(num, 0), numSel, 0);
//		
//		numSel = -1;
		if (!nameTxt.getText().equals("") && !YYMMDDTxt.getText().equals("") && !Pnum1Txt.getText().equals("") && !Pnum2Txt.getText().equals("") && !Pnum3Txt.getText().equals("")
				&& !GenderTxt.getText().equals("")&& !IDTxt.getText().equals("")&& !PWTxt.getText().equals("")&& !EmailTxt.getText().equals("")) {
			try {

				MemDTO dto = new MemDTO();
				dto.setName(nameTxt.getText());
				dto.setYYMMDD(Integer.parseInt(YYMMDDTxt.getText()));
				dto.setPnum1(Integer.parseInt(Pnum1Txt.getText()));
				dto.setPnum2(Integer.parseInt(Pnum2Txt.getText()));
				dto.setPnum3(Integer.parseInt(Pnum3Txt.getText()));
				dto.setGender(GenderTxt.getText());
				dto.setID(IDTxt.getText());
				dto.setPW(PWTxt.getText());
				dto.setEmail(EmailTxt.getText());
				dto.setNum(Integer.parseInt((String) table.getValueAt(numSel, 0)));
				MemDAO dao = MemDAO.getInstance();
				dao.updatePro(dto);
			} catch (NumberFormatException e) {
				// ageTxt.getText()가 숫자로 변환할 수 없는 경우에 여기로 들어옵니다.
				// 적절한 에러 처리를 여기에 작성하세요.
			}
		} else {
			JOptionPane.showMessageDialog(this, "수정할 행을 선택해주세요.");
		}

	} // end updateView()

	private void init() {
		nameTxt.setText("");
		YYMMDDTxt.setText("");
		Pnum1Txt.setText("");
		Pnum2Txt.setText("");
		Pnum3Txt.setText("");
		GenderTxt.setText("");
		IDTxt.setText("");
		PWTxt.setText("");
		EmailTxt.setText("");

	}// end init()

	private void insertView() {
		if (!nameTxt.getText().equals("") && !YYMMDDTxt.getText().equals("") && !Pnum1Txt.getText().equals("") && !Pnum2Txt.getText().equals("") && !Pnum3Txt.getText().equals("")
				&& !GenderTxt.getText().equals("")&& !IDTxt.getText().equals("")&& !PWTxt.getText().equals("")&& !EmailTxt.getText().equals("")) {
			model.setRowCount(0);
			MemDTO dto = new MemDTO();
			dto.setName(nameTxt.getText());
			dto.setYYMMDD(Integer.parseInt(YYMMDDTxt.getText()));
			dto.setPnum1(Integer.parseInt(Pnum1Txt.getText()));
			dto.setPnum2(Integer.parseInt(Pnum2Txt.getText()));
			dto.setPnum3(Integer.parseInt(Pnum3Txt.getText()));
			dto.setGender(GenderTxt.getText());
			dto.setID(IDTxt.getText());
			dto.setPW(PWTxt.getText());
			dto.setEmail(EmailTxt.getText());
			MemDAO dao = MemDAO.getInstance();
			dao.insertPro(dto);
		} else {
			JOptionPane.showMessageDialog(this, "모든 필드에 값을 입력하여주세요.");
		}

	} // end insertView()

	private void selectView() {

		model.setRowCount(0);
		MemDAO dao = MemDAO.getInstance();

		ArrayList<MemDTO> aList = dao.selectPro();

		for (MemDTO dto : aList) {
			String[] line = new String[] { 
					String.valueOf(dto.getNum()), 
								   dto.getName(),
					String.valueOf(dto.getYYMMDD()), 
					String.valueOf(dto.getPnum1()), 
					String.valueOf(dto.getPnum2()),
					String.valueOf(dto.getPnum3()), 
								   dto.getGender(),
								   dto.getID(),
								   dto.getPW(),
								   dto.getEmail() 
					};

			model.addRow(line);
		}

	} // end selectView()

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if (obj == table && e.getClickCount() == 2)
			setInputRecord();
	} // end mouseClicked()

	private void setInputRecord() {

		numSel = table.getSelectedRow();
		nameTxt.setText(table.getValueAt(numSel, 1).toString());
		YYMMDDTxt.setText(table.getValueAt(numSel, 2).toString());
		Pnum1Txt.setText(table.getValueAt(numSel, 3).toString());
		Pnum2Txt.setText(table.getValueAt(numSel, 4).toString());
		Pnum3Txt.setText(table.getValueAt(numSel, 5).toString());
		GenderTxt.setText(table.getValueAt(numSel, 6).toString());
		IDTxt.setText(table.getValueAt(numSel, 7).toString());
		PWTxt.setText(table.getValueAt(numSel, 8).toString());
		EmailTxt.setText(table.getValueAt(numSel, 9).toString());
	} // end setInputRecord()

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}// end class

public class jdbc_exam {

	public static void main(String[] args) {
		new Memberinfo();

	}// end main()

}// end class
