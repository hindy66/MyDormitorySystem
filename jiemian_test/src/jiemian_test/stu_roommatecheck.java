package jiemian_test;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class stu_roommatecheck extends JFrame  {

	    //���������Լ��������ݿ������Ҫ����Ķ���
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		String jdbcurl="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
		String username="root";
		String password="";
		String sql;
		String stuname;
		
		static Container container;
		static JLabel lbl=new JLabel("���������Ϣ����:");
		static JButton button_checkself=new JButton();
	    static JButton button1=new JButton("��¼");
	    static JButton button_exit=new JButton("�˳�");
	    static JButton button_dormitory=new JButton("�鿴������Ϣ");
	    static JButton button_roommate=new JButton("�޸�ѧ����Ϣ");
	    static JButton button_manageroom=new JButton("�������");
	    
	    static JTable jt;
	    static TabelModule sm;
	    JScrollPane jsp=null;
		
		public stu_roommatecheck (String stu)
		{
			this.stuname=stu;
			this.setSize(400, 400);
			this.setTitle("������Ϣ");
			container=this.getContentPane();
			this.setLayout(null);
			
			lbl.setLocation(0, 0);
			lbl.setSize(150,50);
			lbl.setBackground(Color.YELLOW);
			
			sql="SELECT * FROM stus where stuname in(SELECT Members FROM room WHERE DomNum in(SELECT DomNum FROM room where Members='"+stuname+"')AND RoomNum in (SELECT RoomNum FROM room where Members='"+stuname+"')) ";
			sm=new TabelModule(sql);
			jt=new JTable(sm);
			jsp=new JScrollPane(jt);
			jsp.setLocation(50,70);
			jsp.setSize(300,200);
			
			container.add(lbl);
			container.add(jsp);
			this.setVisible(true);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setLocationRelativeTo(null);
			
		}
}
