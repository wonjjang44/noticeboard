package com.baord.boardpage;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.baord.model.repository.MyBoardDAO;
import com.board.main.Main;
import com.board.model.domain.MyBoard;

public class ClickedPage extends JPanel {

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

	JPanel p_bt;
	JButton bt_edit, bt_back,bt_del;

	//DAO
	MyBoardDAO myBoardDAO;
	
	String title;
	String content;
	
	int hit=0;
	
	int myboard_id;
	NoticeBoard noticeBoard;
	public ClickedPage(Main main) {
		this.main = main;
		myBoardDAO = new MyBoardDAO();
		p_title = new JPanel();
		p_title_n = new JPanel();
		la_top = new JLabel();
		p_title_s = new JPanel();
		la_title = new JLabel("제목");
		t_title = new JTextField();
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
		bt_edit = new JButton("수정");
		bt_back = new JButton("취소");
		bt_del=new JButton("삭제");
		
		noticeBoard=(NoticeBoard)main.getPage(2);
		// panel setting

		p_title.setPreferredSize(new Dimension(600, 100));
		p_content.setPreferredSize(new Dimension(590, 400));
		p_bt.setPreferredSize(new Dimension(600, 80));

		p_title.setBackground(Color.PINK);
		p_content.setBackground(Color.CYAN);
		p_bt.setBackground(Color.YELLOW);

		// p_title setting
		p_title_n.setPreferredSize(new Dimension(600, 60));
		p_title_n.setBackground(Color.PINK);
		p_title_s.setPreferredSize(new Dimension(600, 38));
		
		p_title_s.setBackground(Color.CYAN);
		la_top.setFont(new Font("돋움", Font.BOLD, 30));
		la_top.setForeground(Color.WHITE);
		la_title.setFont(new Font("돋움", Font.BOLD, 20));
		la_title.setForeground(Color.BLACK);
		t_title.setPreferredSize(new Dimension(500, 28));

		// p_content setting
		p_content_n.setPreferredSize(new Dimension(600, 50));
		p_content_n.setBackground(Color.CYAN);
		ch_style.setPreferredSize(new Dimension(70, 20));
		ch_size.setPreferredSize(new Dimension(70, 20));
		ch_color.setPreferredSize(new Dimension(70, 20));

		area.setPreferredSize(new Dimension(500, 350));
		area.setFont(new Font("돋움", Font.BOLD, 20));

		// p_bt setting
		bt_edit.setPreferredSize(new Dimension(80, 30));
		bt_back.setPreferredSize(new Dimension(80, 30));
		bt_del.setPreferredSize(new Dimension(80, 30));

		bt_edit.setBackground(Color.ORANGE);
		bt_back.setBackground(Color.ORANGE);
		bt_del.setBackground(Color.ORANGE);

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
		p_bt.add(bt_edit);
		p_bt.add(bt_del);
		p_bt.add(bt_back);
		updateUI();

		// Button edit
		bt_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit();
				main.showPage(2);
			}
		});
		
		//Button delete
		bt_del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				del(myboard_id);
			}
		});
		
		// Button cancel
		bt_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showPage(2);
			}
		});
		
		area.setText("");
		this.setPreferredSize(new Dimension(580, 800));
		setVisible(false);
	}

	public void selectBoard(int select) {
		System.out.println("ClickedPage:"+select);
		MyBoard myBoard =myBoardDAO.select(select);
		myboard_id=myBoard.getMyboard_id();
		t_title.setText(myBoard.getTitle());
		area.setText(myBoard.getContent());

	}

	public void edit() {
		//System.out.println("편집할거니..?");
		title=t_title.getText();
		content=area.getText();
		
		MyBoard myBoard=new MyBoard();
		myBoard.setTitle(title);
		myBoard.setContent(content);
		myBoard.setMyboard_id(myboard_id);
		
		int result=myBoardDAO.update(myBoard);
		if(result==0) {
			JOptionPane.showMessageDialog(ClickedPage.this, "수정에 실패하였습니다..");
		}else {
			JOptionPane.showMessageDialog(ClickedPage.this, "수정을 성공적으로 완료했습니다!!");
			noticeBoard.showBoard();
			main.showPage(2);
		}
		
	}
	public void del(int myBoard_id) {
		//System.out.println("삭제할거니..?");
		
		int result=myBoardDAO.delete(myBoard_id);
		if(result==0) {
			JOptionPane.showMessageDialog(ClickedPage.this, "삭제에 실패하였습니다..");
		}else {
			JOptionPane.showMessageDialog(ClickedPage.this, "성공적으로 삭제하였습니다!!");
			noticeBoard.showBoard();
			main.showPage(2);
		}
	}
}
