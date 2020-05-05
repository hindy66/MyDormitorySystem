package jiemian_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;


public class admin_stuupdate extends JDialog  implements ActionListener {
	
	
	
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	String jdbcurl="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
	String username="root";
	String password="";
	String driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
	String sql;
	
	//定义我需要的控件
	JLabel lb1,lb2,lb3,lb4,lb5,jdl;
	JButton jb_add,jb_cancel;
	JTextField jt1,jt2,jt3,jt4,jt5;
	JPanel jp1,jp2,jp3;
	static JDialog diag;
	


	public admin_stuupdate(admin_stucheckjiemian stucheck, String title, boolean moduel, TabelModule sm, int rownum) {
		// TODO Auto-generated constructor stub
		super(stucheck,title,moduel);
		
	    jdl=new JLabel();
		diag=new JDialog(this);
		diag.setModal(true);
		diag.setLocationRelativeTo(null);
		diag.setSize(100, 100);
	
		lb1=new JLabel("学号");
		lb2=new JLabel("名字");
		lb3=new JLabel("性别");
		lb4=new JLabel("年龄");
		lb5=new JLabel("专业");
		
		jb_add=new JButton("修改");
		jb_cancel=new JButton("取消");
		jb_add.addActionListener(this);
		jb_cancel.addActionListener(this);
		
		//初始化文本框
		jt1=new JTextField();
		jt1.setText((String)sm.getValueAt(rownum, 0));
		//使得jt1不能被修改
		jt1.setEditable(false);
		jt2=new JTextField();
		jt2.setText((String)sm.getValueAt(rownum, 1));
		jt3=new JTextField();
		jt3.setText((String)sm.getValueAt(rownum, 2));
		jt4=new JTextField();
		jt4.setText(sm.getValueAt(rownum, 3).toString());
		jt5=new JTextField();
		jt5.setText((String)sm.getValueAt(rownum, 4));
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		jp1.setLayout(new GridLayout(5,1));
		jp2.setLayout(new GridLayout(5,1));
		
		jp1.add(lb1);
		jp1.add(lb2);
		jp1.add(lb3);
		jp1.add(lb4);
		jp1.add(lb5);
		
		jp2.add(jt1);
		jp2.add(jt2);
		jp2.add(jt3);
		jp2.add(jt4);
		jp2.add(jt5);
		
		jp3.add(jb_add);
		jp3.add(jb_cancel);
		
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);
		
		this.setLocationRelativeTo(null);
		this.setSize(300,200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b==jb_cancel)
		{
			this.dispose();
		}
		if(b==jb_add)
		{
		// TODO Auto-generated method stub
		 try {
	    	  //加载驱动(把需要得驱动程序加入内存)
	    	  Class.forName(driver);
	    	  //得到连接
	    	  ct=DriverManager.getConnection(url,username,password);
	    	  //创建statement(主要用于sql语句)   	  
	    
	    	  sql="update stus set stuname=?,stusex=?,stuage=?,studept=? where stuid=?";
	    	  //給问号赋值
	    	  ps=ct.prepareStatement(sql);
	 		  ps.setString(1, jt2.getText());
	 		  ps.setString(2, jt3.getText());
	 		  ps.setString(3, jt4.getText());
	 		  ps.setString(4, jt5.getText());
	 		  ps.setString(5, jt1.getText());
	 		  int i;
	 		  i=ps.executeUpdate();
	    	  System.out.print(i);
	    	  this.dispose();
	    	  if(i==1)
	    	  {
	    		  System.out.println("执行成功！");
	    	  }
	    	  else
	    	  {
	    		  System.out.println("执行出错！");
	    	  }
	      }catch(Exception e1) {
	    	 e1.printStackTrace(); 
	      }finally {
	    	  //关闭资源
	    	  //关闭顺序是，谁后创建，谁先关闭
	    	  try {
	    		  if(ps!=null)
	    		  {
	        	  ps.close();
	    		  }
	        	  if(ct!=null)
	        	  {
	        		ct.close();
	        	  }
	    	  }catch(SQLException e1) {
	    		  e1.printStackTrace();
	    	  }

	      }
		  
		    jdl.setText("信息添加成功！");
		    diag.add(jdl);
			diag.setLocationRelativeTo(null);
			diag.setVisible(true);
			this.dispose();
		}
	}

}
