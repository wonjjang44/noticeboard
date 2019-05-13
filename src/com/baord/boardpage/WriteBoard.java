package com.baord.boardpage;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.baord.model.repository.MyBoardDAO;
import com.board.main.Main;
import com.board.model.domain.MemberShip;
import com.board.model.domain.MyBoard;

public class WriteBoard extends JPanel {

	Main main;

	JPanel p_title, p_title_n, p_title_s;
	JLabel la_top;
	JLabel la_title;
	JTextField t_title;

	JPanel p_content;
	JPanel p_content_n;
	Choice ch_style;
	Choice ch_size;
	Choice ch_color;
	JTextArea area;
	JTextField write;
	
	JPanel p_write;

	JPanel p_bt;
	JButton bt_regist, bt_cancel;

	// db관련
	Connection con;

	// Font & Color
	String[] font = { "돋움", };
	String[] size = { "" };
	Color[] color = {};
	
	MemberShip member;
	MyBoardDAO myBoardDAO;

	String title;
	String writer;
	String content;
	String hit;
	
	NoticeBoard noticeBoard;
	public void setMember(MemberShip member) {
		this.member = member;
	}

	public WriteBoard(Main main) {
		this.main = main;
		noticeBoard=(NoticeBoard)main.getPage(2);
		myBoardDAO=new MyBoardDAO();
		p_title = new JPanel();
		p_title_n = new JPanel();
		la_top = new JLabel("WRITE BOARD");
		p_title_s = new JPanel();
		la_title = new JLabel("제목");
		t_title = new JTextField();
		write=new JTextField();
		p_write=new JPanel();
		p_content = new JPanel();
		p_content_n = new JPanel();
		ch_style = new Choice();
		ch_size = new Choice();
		ch_color = new Choice();
		// 임시
		ch_style.add("돋움");
		ch_size.add("11");
		ch_color.add("black");

		area = new JTextArea();
		p_bt = new JPanel();
		bt_regist = new JButton("등록");
		bt_cancel = new JButton("취소");
		NoticeBoard noticeBonard=(NoticeBoard)main.getPage(2);
		
		System.out.println("WriteBoard : "+member);
		// panel setting

		p_title.setPreferredSize(new Dimension(600, 100));
		p_content.setPreferredSize(new Dimension(600, 400));
		p_bt.setPreferredSize(new Dimension(600, 80));
		
		p_write.setPreferredSize(new Dimension(300,100));//==========
		
		p_title.setBackground(Color.BLACK);
		p_content.setBackground(Color.BLACK);
		p_bt.setBackground(Color.BLACK);

		// p_title setting
		p_title_n.setPreferredSize(new Dimension(600, 60));
		p_title_n.setBackground(Color.BLACK);
		p_title_s.setPreferredSize(new Dimension(600, 38));
		p_title_s.setBackground(Color.BLACK);
		la_top.setFont(new Font("돋움", Font.BOLD, 30));
		la_top.setForeground(Color.GREEN);
		la_title.setFont(new Font("돋움", Font.BOLD, 20));
		la_title.setForeground(Color.GREEN);
		t_title.setPreferredSize(new Dimension(500, 28));

		// p_content setting
		p_content_n.setPreferredSize(new Dimension(600, 50));
		p_content_n.setBackground(Color.BLACK);
		ch_style.setPreferredSize(new Dimension(70, 20));
		ch_size.setPreferredSize(new Dimension(70, 20));
		ch_color.setPreferredSize(new Dimension(70, 20));

		area.setPreferredSize(new Dimension(600, 350));
		area.setFont(new Font("돋움", Font.BOLD, 20));

		// p_bt setting
		bt_regist.setPreferredSize(new Dimension(80, 30));
		bt_cancel.setPreferredSize(new Dimension(80, 30));

		bt_regist.setBackground(Color.GREEN);
		bt_cancel.setBackground(Color.GREEN);

		// add.panel
		setLayout(new BorderLayout());
		add(p_title, BorderLayout.NORTH);
		add(p_content);
		add(p_bt, BorderLayout.SOUTH);

		// add.p_title
		p_title.setLayout(new BorderLayout());
		p_title.add(p_title_n, BorderLayout.NORTH);
		p_title.add(p_title_s);
		p_title_n.add(la_top);
		p_title_s.add(la_title);
		p_title_s.add(t_title);

		// add.p_content
		p_content.setLayout(new BorderLayout());
		p_content.add(p_content_n, BorderLayout.NORTH);
		p_content_n.add(ch_style);
		p_content_n.add(ch_size);
		p_content_n.add(ch_color);

		p_content.add(area);

		// add.p_bt
		p_bt.add(bt_regist);
		p_bt.add(bt_cancel);
		updateUI();

		// Button regist
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberShip memberShip=new MemberShip();
				memberShip.setMembership_id(member.getMembership_id());
				System.out.println("WriteBoard: "+member);
				registBoard();
				
				t_title.setText("");
				area.setText("");
				
				if(t_title.getText()==null) {
					JOptionPane.showMessageDialog(WriteBoard.this, "한글자 이상 입력해주세요");
					main.showPage(4);
				}else if(area.getText()==null) {
					JOptionPane.showMessageDialog(WriteBoard.this, "한글자 이상 입력해주세요");
					main.showPage(4);
				}
				
				noticeBoard.showBoard();
				main.showPage(2);
			}
		});
		// Button cancel
		bt_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showPage(2);
			}
		});

		this.setPreferredSize(new Dimension(581, 800));
		setVisible(false);
	}

	public void registBoard() {
//		System.out.println("등록성공..");
		title=t_title.getText();
		content=area.getText();
		writer=write.getText();
		
		MyBoard myBoard=new MyBoard();
		myBoard.setTitle(title);
		myBoard.setContent(content);
		myBoard.setWriter(writer);
		
		int result=myBoardDAO.insert(myBoard);
		if(result==0) {
			JOptionPane.showMessageDialog(WriteBoard.this, "글쓰기에 실패하였습니다. 다시 시도해주세요.");
		}else {
			JOptionPane.showMessageDialog(WriteBoard.this, "글을 성공적으로 등록하였습니다!!");
			
			
			main.showPage(0);
		}
		
	}

}
