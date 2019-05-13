package com.baord.boardpage;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.baord.model.repository.MyBoardDAO;
import com.board.main.Main;
import com.board.model.domain.MemberShip;
import com.board.model.domain.MyBoard;
import com.board.tableautosize.TableAutoSize;

public class NoticeBoard extends JPanel {
	Main main;
	// pages1.add()
	JPanel p_top, p_center, p_bottom;
	JLabel la_title;

	// p_top.add()

	JScrollPane scroll;

	// p_bottom.add()
	JPanel p_bottom_1, p_bottom_2, p_bottom_3;

	// p_bottom_2.add()
	Choice choice;
	JTextField t_search;
	JButton bt_search;
	JButton bt_reset;
	JButton bt_write;

	// db관련
	MyBoardDAO myBoardDAO;
	List<MyBoard> list;
	JTable table;
	TableModel tableModel;
	MemberShip member;
	String[] columnName;
	ClickedPage clickedPage;
	
	int hit=0;
	
	public void setMember(MemberShip member) {
		this.member = member;
	}

	public NoticeBoard(Main main) {
		// create
		this.main = main;
		
		
		
		myBoardDAO = new MyBoardDAO();
		columnName = new String[5];

		p_top = new JPanel();
		p_center = new JPanel();
		p_bottom = new JPanel();
		la_title = new JLabel("NOTICE BOARD");

		tableModel = new TableModel(this);
		table = new JTable();
		scroll = new JScrollPane(table);
		// table.setRowHeight(rowHeight);

		p_bottom_1 = new JPanel();// empty panel
		p_bottom_2 = new JPanel();// search/write
		p_bottom_3 = new JPanel();// empty panel
		choice = new Choice();
		choice.add("작성자");
		choice.add("제목");

		t_search = new JTextField();
		bt_search = new JButton("검색");
		bt_reset = new JButton("새로고침");
		bt_write = new JButton("글쓰기");

		// pages1 Size & Color

		p_top.setPreferredSize(new Dimension(600, 70));
		p_center.setPreferredSize(new Dimension(600, 570));
		p_bottom.setPreferredSize(new Dimension(600, 150));

		// pages1.setBackground(Color.BLACK);
		p_top.setBackground(Color.BLACK);
		// p_center.setBackground(Color.GRAY);
		p_bottom.setBackground(Color.RED);

		// title setting
		la_title.setFont(new Font("돋움", Font.BOLD, 50));
		la_title.setForeground(Color.white);

		// p_center size & color
		scroll.setPreferredSize(new Dimension(580, 570));

		// p_bottom size & color
		p_bottom_1.setPreferredSize(new Dimension(600, 50));
		p_bottom_2.setPreferredSize(new Dimension(600, 100));
		p_bottom_3.setPreferredSize(new Dimension(600, 50));

		p_bottom_1.setBackground(Color.BLACK);
		p_bottom_2.setBackground(Color.BLACK);
		p_bottom_3.setBackground(Color.BLACK);

		// p_bottom_2 size & color
		choice.setPreferredSize(new Dimension(80, 24));
		t_search.setPreferredSize(new Dimension(200, 24));
		bt_search.setPreferredSize(new Dimension(60, 24));
		bt_reset.setPreferredSize(new Dimension(100, 24));
		bt_write.setPreferredSize(new Dimension(80, 24));

		// pages1.add();
		setLayout(new BorderLayout());
		// add(pages1);

		add(p_top, BorderLayout.NORTH);
		add(p_center);
		add(p_bottom, BorderLayout.SOUTH);

		// add(la_title)
		p_top.add(la_title);

		// p_top.add
		p_center.add(scroll);

		// p_bottom.add
		p_bottom.setLayout(new BorderLayout());
		p_bottom.add(p_bottom_1, BorderLayout.NORTH);
		p_bottom.add(p_bottom_2);
		p_bottom.add(p_bottom_3, BorderLayout.SOUTH);

		// p_bottom_2.add
		p_bottom_2.add(choice);
		p_bottom_2.add(t_search);
		p_bottom_2.add(bt_search);
		p_bottom_2.add(bt_reset);
		p_bottom_3.add(bt_write);
//=============================================================
		// table Click
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = 0;

				int select = (int) table.getValueAt(row, col);
				clickedPage=(ClickedPage)main.getPage(3);
				System.out.println("NoticeBoard : "+select);
				
				System.out.println(clickedPage);
				clickedPage.selectBoard(select);
				hit++;
				main.showPage(3);

			}
		});

		// bt_write Event
		bt_write.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WriteBoard writeBoard = (WriteBoard) main.getPage(4);
				writeBoard.setMember(member);
				main.showPage(4);

			}
		});

		// 선택한 choice의 인덱스를 알기위한 이벤트.
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

			}
		});

		// search Event
		bt_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		// 새로고침
		bt_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showBoard();

			}
		});

		columnName[0]="NO";
		columnName[1]="글제목";		
		columnName[2]="작성자";
		columnName[3]="작성일";
		columnName[4]="조회수";
		
		showBoard();
		table.updateUI();
		setVisible(false);
		setSize(600, 800);
		
	}

	// =============================================================
	public void showBoard() {

		tableModel.columnName = columnName;
		list = myBoardDAO.selectAll();
		Object[][] data = new Object[list.size()][columnName.length];
		for (int i = 0; i < list.size(); i++) {
			MyBoard myBoard = list.get(i);
			data[i][0] = myBoard.getMyboard_id();
			data[i][1] = myBoard.getTitle();
			data[i][2] = myBoard.getWriter();
//			data[i][3] = GetDate.getDate(myBoard.getRegdate());
			data[i][3] = myBoard.getRegdate();
			data[i][4] = myBoard.getHit();
		}

		tableModel.data = data;

		table.setModel(tableModel);
	}
		
	

//=============================================================
	public void search(String choice) {
		
	}

}
