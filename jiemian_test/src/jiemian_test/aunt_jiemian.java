package jiemian_test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;


import java.util.*;
import java.awt.event.*;

public class aunt_jiemian extends JFrame implements ActionListener{
	//��Ҫ�Ŀؼ�
    JPanel jp1,jp2;
    JLabel jl1;
    JButton jb1,jb2,jb3,jb4;
    JTable jt;
    JTextField jtf1;
	Vector rowData,columnName;
	JScrollPane jsp=null;
	
	//���������Լ��������ݿ������Ҫ����Ķ���
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	String jdbcurl="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
	String username="root";
	String password="";
	
	static aunt_jiemian  adm=new aunt_jiemian();
	
	static Container container = adm.getContentPane();
	static JLabel lbl=new JLabel("��ӭ�㣬��ͨ����Ա");
	static JButton button_check=new JButton();
    static JButton button1=new JButton("��¼");
    static JButton button_exit=new JButton("�˳�");
    static JButton button_addstu=new JButton("���ѧ����Ϣ");
    static JButton button_updatestu=new JButton("�޸�ѧ����Ϣ");
    static JButton button_manageroom=new JButton("�������");
    static JDialog diag_check=new JDialog(adm);
    
    static ImageIcon bg=new ImageIcon("��ͨ����Ա����.jpg");
    public static void main(String[] args) {
    	
       JLabel label = new JLabel(bg);
 	   label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
 	   adm.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
 	   JPanel jp=(JPanel)adm.getContentPane(); 
 	   jp.setOpaque(false);
       adm.setTitle("��ͨ����Ա����");
       adm.setSize(400,400);
       adm.setBackground(Color.GRAY);
       adm.setLocationRelativeTo(null);
       container.setLayout(null);
       container.setBackground(Color.GRAY);

       button_check.setText("ѧ����Ϣ");
       button_check.setSize(100,50);
       button_check.setLocation(250,150);
       button_check.addActionListener(adm);
       
       /*button_updatestu.setSize(150,50);
       button_updatestu.setLocation(220,200);
       button_updatestu.addActionListener(adm);
       
       
       
       button_addstu.setSize(150,50);
       button_addstu.setLocation(420,200);
       button_addstu.addActionListener(adm);*/
       
       button_manageroom.setSize(100,50);
       button_manageroom.setLocation(50,150);
       button_manageroom.addActionListener(adm);
       
       button_exit.setSize(100,50);
       button_exit.setLocation(150,250);
       button_exit.addActionListener(adm);
       
       lbl.setSize(200, 100);
       lbl.setLocation(130,0);
       
       /*container.add(lbl);
       container.add(button_check);
       container.add(button_addstu);
       container.add(button_updatestu);*/
       container.add(button_check);
       container.add(lbl);
       container.add(button_manageroom);
       container.add(button_exit);
       adm.setVisible(true);
       adm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt=(JButton)e.getSource();
		// TODO Auto-generated method stub
		if(bt==button_manageroom)
		{
			admin_dormitorycheck dormitorycheck=new admin_dormitorycheck(this,"�������",false);
			dormitorycheck.setLocationRelativeTo(null);
		}
		if(bt==button_exit)
		{
			this.dispose();
		}
		if(bt==button_check)
		{
			admin_stucheckjiemian check=new admin_stucheckjiemian(this,"��Ϣ��ѯ",false) ;
		}
	}
	 

}
