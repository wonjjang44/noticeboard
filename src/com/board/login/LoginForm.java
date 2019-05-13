package com.board.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.baord.boardpage.NoticeBoard;
import com.baord.model.repository.MemberShipDAO;
import com.board.main.Main;
import com.board.model.domain.MemberShip;

public class LoginForm extends JPanel {
	public static int LoginForm_WIDTH;
	public static int LoginForm_HEIGHT;

	Main main;
	JPanel p_north;
	JPanel p_center;
	JPanel p_south;
	JTextField tf_id;
	JTextField pf_pw;
	JButton bt_login;
	JButton bt_regist;
	JLabel la_title;

	MemberShipDAO memberShipDAO;
	MemberShip member;
	
	

	public MemberShip getMember() {
		return member;
	}

	public LoginForm(Main main) {
		this.main = main;
		memberShipDAO = new MemberShipDAO();
		LoginForm_WIDTH = Main.Main_WIDTH;
		LoginForm_HEIGHT = Main.Main_HEIGHT;
		this.setPreferredSize(new Dimension(LoginForm_WIDTH, LoginForm_HEIGHT));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLUE);
		createComponent();
	}

	public void createComponent() {
		p_north = new JPanel();
		p_north.setPreferredSize(new Dimension(LoginForm_WIDTH, LoginForm_HEIGHT / 3));

		p_center = new JPanel();
		p_center.setPreferredSize(new Dimension(LoginForm_WIDTH, LoginForm_HEIGHT / 3));

		p_south = new JPanel();
		p_south.setPreferredSize(new Dimension(LoginForm_WIDTH, LoginForm_HEIGHT / 3));

		Dimension tfpf_size = new Dimension(300, 50);
		tf_id = new JTextField();
		tf_id.setPreferredSize(tfpf_size);

		pf_pw = new JTextField();
		pf_pw.setPreferredSize(tfpf_size);

		la_title = new JLabel("로그인");
		la_title.setFont(new Font("휴먼매직체", Font.PLAIN, 50));

		bt_login = new JButton("로그인");
		bt_login.setPreferredSize(new Dimension(150, 40));
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=tf_id.getText();
				String password=pf_pw.getText();
				System.out.println(password);
				MemberShip memberShip=new MemberShip();
				memberShip.setId(id);
				memberShip.setPassword(password);
				if(memberShip==null) {
					JOptionPane.showMessageDialog(LoginForm.this, "잘못된 정보입니다..");
					return;
				}else {
					
					 member=memberShipDAO.select(memberShip);
					if(member==null) {
						JOptionPane.showMessageDialog(LoginForm.this, "잘못된 정보입니다..");
					}else {
						JOptionPane.showMessageDialog(LoginForm.this, "로그인 성공!!");
						NoticeBoard noticeBoard=(NoticeBoard)main.getPage(2);
						noticeBoard.setMember(member);
						main.showPage(2);
					}
				}
			}
		});

		bt_regist = new JButton("회원가입");
		bt_regist.setPreferredSize(new Dimension(150, 40));
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showPage(1);
			}
		});

		p_north.add(la_title);
		p_center.add(tf_id);
		p_center.add(pf_pw);
		p_south.add(bt_login);
		p_south.add(bt_regist);

		this.add(p_north, BorderLayout.NORTH);
		this.add(p_center);
		this.add(p_south, BorderLayout.SOUTH);
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public JPanel getP_north() {
		return p_north;
	}

	public void setP_north(JPanel p_north) {
		this.p_north = p_north;
	}

	public JPanel getP_center() {
		return p_center;
	}

	public void setP_center(JPanel p_center) {
		this.p_center = p_center;
	}

	public JPanel getP_south() {
		return p_south;
	}

	public void setP_south(JPanel p_south) {
		this.p_south = p_south;
	}

	public JTextField getTf_id() {
		return tf_id;
	}

	public void setTf_id(JTextField tf_id) {
		this.tf_id = tf_id;
	}

	public JTextField getPf_pw() {
		return pf_pw;
	}

	public void setPf_pw(JTextField pf_pw) {
		this.pf_pw = pf_pw;
	}

	public JButton getBt_login() {
		return bt_login;
	}

	public void setBt_login(JButton bt_login) {
		this.bt_login = bt_login;
	}

	public JButton getBt_regist() {
		return bt_regist;
	}

	public void setBt_regist(JButton bt_regist) {
		this.bt_regist = bt_regist;
	}

}
