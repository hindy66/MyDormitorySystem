package jiemian_test;

import javax.swing.JDialog;
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

public class admin_stuadd extends JDialog  implements ActionListener{
  
	int i;
	Connection ct=null;
	Statement sm=null;
	PreparedStatement ps=null;
	String username="root";
	String password="";
	String driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";;
	ResultSet rs,rs1;
	String sql;
	int rowdata;
	//定义我需要的控件
		JLabel lb1,lb2,lb3,lb4,lb5,jdl;
		JButton jb_add,jb_cancel;
		JTextField jt1,jt2,jt3,jt4,jt5;
		JPanel jp1,jp2,jp3;
		static JDialog diag;
		
		
		//owner它的父窗口，title窗口名，module指定是模式窗口还是非模式窗口
		public admin_stuadd(Frame owner,String title,boolean moduel) {
			super(owner,title,moduel);
			
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
			
			jb_add=new JButton("确定");
			jb_cancel=new JButton("取消");
			jb_add.addActionListener(this);
			jb_cancel.addActionListener(this);
			
			jt1=new JTextField();
			jt2=new JTextField();
			jt3=new JTextField();
			jt4=new JTextField();
			jt5=new JTextField();
			
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
			// TODO Auto-generated method stub
			JButton b=(JButton)e.getSource();
			if(b==jb_cancel)
			{
				this.dispose();
			}
			if(b==jb_add)
			{
			    try {
					Class.forName("com.mysql.jdbc.Driver");
					ct=DriverManager.getConnection(url, username, password);
					sql="select * from major where major='"+jt5.getText()+"'";
		 			ps=ct.prepareStatement(sql);
		 			rs1=ps.executeQuery();
		 			rs1.last();
		 			rowdata=rs1.getRow();
		 			
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}finally {
			    	  //关闭资源
			    	  //关闭顺序是，谁后创建，谁先关闭
			    	  try {
			    		  if(sm!=null)
			    		  {
			        	  sm.close();
			    		  }
			        	  if(ct!=null)
			        	  {
			        		ct.close();
			        	  }
			    	  }catch(SQLException e1) {
			    		  e1.printStackTrace();
			    	  }

			      }
	 			if(rowdata==0)
	 			{
	 				jdl.setText("你输入的专业不存在！");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
	 			}
				
	 			else if(jt1.getText().equals("")||jt2.getText().equals("")||jt3.getText().equals("")||jt4.getText().equals("")||jt5.getText().equals(""))
				{
					jdl.setText("请保持信息完整性！");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
				}
				
				
				else
				{
					String stusex=jt3.getText();
					String studep=jt5.getText();
					try {
				    	  //加载驱动(把需要得驱动程序加入内存)
				    	  Class.forName(driver);
				    	  //得到连接
				    	  ct=DriverManager.getConnection(url,username,password);
				    	  //创建statement(主要用于sql语句)
				    	  sm=ct.createStatement();
				    	  //执行各种数据库操作
				    	  
				    	  //演示添加数据到stus,room,user表
				    	  //excuteupdate可以执行crud操作
				    	  i=sm.executeUpdate("insert into stus values('"+jt1.getText()+"','"+jt2.getText()+"','"+jt3.getText()+"','"+jt4.getText()+"','"+jt5.getText()+"')");
				    	  sm.executeUpdate("insert into user values('"+jt1.getText()+"','"+jt1.getText()+"','0','"+jt2.getText()+"')");
				    	  
				    	  
				    	  //添加完学生的同时为其分配宿舍，更新dormitory表
				    	  admin_roommatch roommatch=new admin_roommatch(jt3.getText(),jt5.getText());
				    	  String floornum,roomnum,dormitorynum;
				    	  floornum=roommatch.finalfloornum;
				    	  roomnum=roommatch.finalroomnum;
				    	  dormitorynum=roommatch.finaldormitory;
				    	  sm.executeUpdate("insert into room values('"+dormitorynum+"','"+floornum+"','"+roomnum+"','4','1000','"+jt2.getText()+"')");
				 
				    	  
				    	  System.out.print(i); 
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
				    		  if(sm!=null)
				    		  {
				        	  sm.close();
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
}
