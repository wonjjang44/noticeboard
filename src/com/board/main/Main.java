package com.board.main;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.baord.boardpage.ClickedPage;
import com.baord.boardpage.NoticeBoard;
import com.baord.boardpage.WriteBoard;
import com.board.login.LoginForm;
import com.board.login.RegistForm;


public class Main extends JFrame {

	public static int Main_WIDTH=600;
	public static int Main_HEIGHT=840;
	JPanel p_wrapper;
	JPanel[] pages = new JPanel[5];
  

	public Main() {
      p_wrapper = new JPanel();
      // �гε� ����
      pages[0]=new LoginForm(this);
      pages[1]=new RegistForm(this);
      pages[2]=new NoticeBoard(this);//�Խ��� ���� ������
      pages[3]=new ClickedPage(this);//���� ������
      pages[4]=new WriteBoard(this);//�۾��� ������
      


      // size
      p_wrapper.setPreferredSize(new Dimension(500, 600));

      // �г� ����
      add(p_wrapper);

      for (int i = 0; i < pages.length; i++) {
         p_wrapper.add(pages[i]);
      }

      showPage(0);
      setSize(Main_WIDTH, Main_HEIGHT);
      setLocationRelativeTo(null);
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
	}

   // ������ ����
   public void showPage(int page) {
      for (int i = 0; i < pages.length; i++) {
         if (i == page) {
            pages[i].setVisible(true);
         } else {
            pages[i].setVisible(false);
         }
      }
   }

//�������� ������ ���´�.
   public JPanel getPage(int page) {
      return pages[page];
   }

   public static void main(String[] args) {
      new Main();
   }
}