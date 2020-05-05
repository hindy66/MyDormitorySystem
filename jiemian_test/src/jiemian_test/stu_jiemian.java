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

public class stu_jiemian extends JFrame implements ActionListener {

	//需要的控件
    JPanel jp1,jp2;
    JLabel jl1;
    JButton jb1,jb2,jb3,jb4;
    JTable jt;
    JTextField jtf1;
	Vector rowData,columnName;
	JScrollPane jsp=null;
	
	//加载数据以及进行数据库操纵需要定义的对象
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	String jdbcurl="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
	String username="root";
	String password="";
	
    String stuname[]=new String [1]; 
	
	
	static Container container;
	static JLabel lbl=new JLabel("欢迎你，同学");
	static JButton button_checkself=new JButton();
    static JButton button1=new JButton("登录");
    static JButton button_exit=new JButton("退出");
    static JButton button_dormitory=new JButton("查看宿舍信息");
    static JButton button_roommate=new JButton("舍友信息");
    static JButton button_manageroom=new JButton("宿舍管理");
    

    static ImageIcon bg=new ImageIcon("学生界面2.jpg");
    
	public stu_jiemian(String name) {
	   stuname[0]=name;
	   lbl.setText("欢迎你，"+stuname[0]+"同学");
	 
	   
	   JLabel label = new JLabel(bg);
	   label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
	   this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
	   JPanel jp=(JPanel)this.getContentPane(); 
	   jp.setOpaque(false);
	   this.pack();
       this.setTitle("学生界面");
       this.setSize(600,400);
       this.setBackground(Color.GRAY);
       this.setLocationRelativeTo(null);
       this.setLayout(null);
       
       
       container=this.getContentPane();
       container.setLayout(null);
       container.setBackground(Color.GRAY);

       button_checkself.setText("查看个人信息");
       button_checkself.setSize(150,50);
       button_checkself.setLocation(30,200);
       button_checkself.addActionListener(this);
       
       button_roommate.setSize(150,50);
       button_roommate.setLocation(420,200);
       button_roommate.addActionListener(this);
       
       
       
       button_dormitory.setSize(150,50);
       button_dormitory.setLocation(220,200);
       button_dormitory.addActionListener(this);
       
       button_manageroom.setSize(150,50);
       button_manageroom.setLocation(130,300);
       button_manageroom.addActionListener(this);
       
       button_exit.setSize(150,50);
       button_exit.setLocation(220,300);
       button_exit.addActionListener(this);
       
       lbl.setSize(200, 100);
       lbl.setLocation(230,0);
       
       container.add(lbl);
       container.add(button_checkself);
       container.add(button_dormitory);
       container.add(button_roommate);
       /*container.add(button_manageroom);*/
       container.add(button_exit);
       this.setVisible(true);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton bt=(JButton)e.getSource();
		if(bt==button_checkself) {
			stu_personalcheck stuupdate=new stu_personalcheck(this,"个人信息",false,stuname[0]) ;
		}
		if(bt==button_dormitory)
		{
			stu_dormitorycheck dormitorycheck=new stu_dormitorycheck(this,"宿舍信息",false,stuname[0]);
			System.out.println(stuname[0]);
			dormitorycheck.setLocationRelativeTo(null);
		}
		if(bt==button_roommate)
		{
			stu_roommatecheck sr=new stu_roommatecheck(stuname[0]);
			sr.setLocationRelativeTo(null);
		 
		}
		if(bt==button_manageroom)
		{
			stu_roommatecheck sr=new stu_roommatecheck(stuname[0]);
			sr.setLocationRelativeTo(null);
		}
		if(bt==button_exit)
		{
			this.dispose();
		}
	       
      }


}
