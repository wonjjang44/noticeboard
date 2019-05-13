package com.board.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.baord.model.repository.MemberShipDAO;
import com.board.main.Main;
import com.board.model.domain.MemberShip;

public class RegistForm extends JPanel{
	public static int RegistForm_WIDTH;
	public static int RegistForm_HEIGHT;
	Main main;
	JPanel p_west;
	JPanel p_center;
	JLabel la_name;
	JLabel la_id;
	JLabel la_pw;
	JLabel la_address;
	JTextField tf_id;
	JTextField tf_pw;
	JTextField tf_name;
	JTextField tf_gender;
	JButton bt_regist;
	JButton bt_cancel;
	
	String id;
	String password;
	String name;
	String gender;
	
	
	//DAO
	MemberShipDAO memberShipDAO;
	public RegistForm(Main main) {
		this.main = main;
		memberShipDAO = new MemberShipDAO();
		RegistForm_WIDTH = Main.Main_WIDTH;
		RegistForm_HEIGHT = Main.Main_HEIGHT;
		createComponent();
		this.setBounds(650, 150, RegistForm_WIDTH, RegistForm_HEIGHT);
		this.setVisible(true);
		
	}
	
	public void createComponent() {
		p_west = new JPanel();
		p_west.setPreferredSize(new Dimension(RegistForm_WIDTH/3, RegistForm_HEIGHT));
		
		p_center = new JPanel();
		p_center.setPreferredSize(new Dimension(RegistForm_WIDTH*2/3, RegistForm_HEIGHT));
		
		Dimension la_size = new Dimension(RegistForm_WIDTH/3, 50);
		Font la_font = new Font(null, Font.BOLD, 20);
		la_id = new JLabel("아이디");
		la_pw = new JLabel("패스워드");
		la_name = new JLabel("이름");
		la_address = new JLabel("성별");
		
		la_id.setPreferredSize(la_size);
		la_pw.setPreferredSize(la_size);
		la_name.setPreferredSize(la_size);
		la_address.setPreferredSize(la_size);
		
		la_id.setFont(la_font);
		la_pw.setFont(la_font);
		la_name.setFont(la_font);
		la_address.setFont(la_font);
		
		la_id.setHorizontalAlignment(SwingConstants.CENTER);
		la_pw.setHorizontalAlignment(SwingConstants.CENTER);
		la_name.setHorizontalAlignment(SwingConstants.CENTER);
		la_address.setHorizontalAlignment(SwingConstants.CENTER);
		
		p_west.add(la_id);
		p_west.add(la_pw);
		p_west.add(la_name);
		p_west.add(la_address);
		
		Dimension tf_size = new Dimension(RegistForm_WIDTH*2/4, 50);
		
		tf_id = new JTextField();
		tf_pw = new JTextField();
		tf_name = new JTextField();
		tf_gender = new JTextField();
		
		tf_id.setPreferredSize(tf_size);
		tf_pw.setPreferredSize(tf_size);
		tf_name.setPreferredSize(tf_size);
		tf_gender.setPreferredSize(tf_size);
		
		bt_regist = new JButton("등록");
		bt_cancel = new JButton("취소");
		bt_regist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("bt_regist Clicked");
				id = tf_id.getText();
				password = tf_pw.getText();
				name = tf_name.getText();
				gender = tf_gender.getText();
				
				MemberShip memberShip = new MemberShip();
				memberShip.setId(id);
				memberShip.setPassword(password);
				memberShip.setName(name);
				memberShip.setGender(gender);
				
				int result=memberShipDAO.insert(memberShip);
				if(result==0) {
					JOptionPane.showMessageDialog(RegistForm.this, "회원가입을 다시 진행주세요^^...");
				}else {
					JOptionPane.showMessageDialog(RegistForm.this, "회원가입 완료!!");
					main.showPage(0);
				}
				tf_id.setText("");
				tf_pw.setText("");
				tf_name.setText("");
				tf_gender.setText("");
			}
		});
		bt_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.showPage(0);
			}
		});
		
		p_center.add(tf_id);
		p_center.add(tf_pw);
		p_center.add(tf_name);
		p_center.add(tf_gender);
		p_center.add(bt_regist);
		p_center.add(bt_cancel);
		
	
		
		//this.setUndecorated(true);
		this.add(p_west, BorderLayout.WEST);
		this.add(p_center);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	
}
