package jiemian_test;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class admin_roomupdate extends JDialog  implements ActionListener{
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs_dormitory=null,rs_stu=null,rs_room=null;
	String username="root";
	String password="";
	String driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
	String sql;
	
	//定义我需要的控件
	JLabel lb1,lb2,lb3,lb4,lb5,jdl,lb6;
	JButton jb_add,jb_cancel;
	JTextField jt1,jt2,jt3,jt4,jt5,jt6;
	JPanel jp1,jp2,jp3;
	static JDialog diag;
	
    //判断修改信息是否合法
	boolean message=true;
	int result=0;
	
	 String stuname=null;
	 String stusex=null;
	 String DorSex=null;
	 
	 String floor;
     String room;
     char roomfirst[];
     
     String dormitory;
     String roomnum;
     int rowCount=0;
	
	public admin_roomupdate(admin_dormitorycheck stucheck, String title, boolean moduel, TabelModule_rooms sm, int rownum) {
		// TODO Auto-generated constructor stub
		super(stucheck,title,moduel);
		
	    jdl=new JLabel();
		diag=new JDialog(this);
		diag.setModal(true);
		diag.setLocationRelativeTo(null);
		diag.setSize(200, 100);
	
		lb1=new JLabel("宿舍楼");
		lb2=new JLabel("楼层");
		lb3=new JLabel("房号");
		lb4=new JLabel("床数");
		lb5=new JLabel("价格");
		lb6=new JLabel("成员");
		
		jb_add=new JButton("修改");
		jb_cancel=new JButton("取消");
		jb_add.addActionListener(this);
		jb_cancel.addActionListener(this);
		
		//初始化文本框
		jt1=new JTextField();
		jt1.setText((String)sm.getValueAt(rownum, 0));
		//使得jt4,5,6不能被修改
		jt2=new JTextField();
		jt2.setText((String)sm.getValueAt(rownum, 1));
		jt3=new JTextField();
		jt3.setText((String)sm.getValueAt(rownum, 2));
		jt4=new JTextField();
		jt4.setText(sm.getValueAt(rownum, 3).toString());
		jt4.setEditable(false);
		jt5=new JTextField();
		jt5.setText((String)sm.getValueAt(rownum, 4));
		jt5.setEditable(false);
		jt6=new JTextField();
		jt6.setText((String)sm.getValueAt(rownum, 5));
		jt6.setEditable(false);
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		jp1.setLayout(new GridLayout(6,1));
		jp2.setLayout(new GridLayout(6,1));
		
		jp1.add(lb1);
		jp1.add(lb2);
		jp1.add(lb3);
		jp1.add(lb4);
		jp1.add(lb5);
		jp1.add(lb6);
		
		jp2.add(jt1);
		jp2.add(jt2);
		jp2.add(jt3);
		jp2.add(jt4);
		jp2.add(jt5);
		jp2.add(jt6);
		
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
			//检查修改的信息是否合法
			
			
			//1.检查宿舍楼是否存在
			String DorNum=jt1.getText();
	        try {
				  Class.forName(driver);
				  ct=DriverManager.getConnection(url,username,password);
				  sql="select * from dormitory where DomNum='"+DorNum+"'";
				  ps=ct.prepareStatement(sql);
				  rs_dormitory=ps.executeQuery();
				  rs_dormitory.last();
				  result=rs_dormitory.getRow();
				  /*if(result==0)
				  {
					    jt1.setText("");
					    jdl.setText("你输入的宿舍楼不存在！");
					    diag.add(jdl);
						diag.setLocationRelativeTo(null);
						diag.setVisible(true);
				  }
				  else 
				  {
					  message=true; 
				  }*/
				  //检查学生性别是否与宿舍楼性别匹配
				  stuname=jt6.getText();
				  sql="select * from stus where stuname='"+stuname+"'";
				  ps=ct.prepareStatement(sql);
				  rs_stu=ps.executeQuery();
				  rs_stu.next();
				  stusex=rs_stu.getString(3);
				  
				  rs_dormitory.beforeFirst();
				  rs_dormitory.next();
				  DorSex=rs_dormitory.getString(4);
				  
				  /*if(DorSex.equals(stusex)==false)
				  {
					    
					    jt1.setText("");
					    jdl.setText("学生无法入住该宿舍楼！");
					    diag.add(jdl);
						diag.setLocationRelativeTo(null);
						diag.setVisible(true);
				  }
				  else
				  {
						message=true;
				   }*/
				
			} catch (Exception  e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
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
	        
	        //2.检查修改的楼层与宿舍是否对应
	        floor=jt2.getText();
	        room=jt3.getText();
	        roomfirst=room.toCharArray();
	        /*
	         if(floor.equals(Character.toString(roomfirst[0]))==false)
	        {
				    jdl.setText("你输入的楼层与宿舍不对应！");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
	        }
	         else if(Integer.parseInt(floor)<1||Integer.parseInt(floor)>5)
	         {
				    jdl.setText("你输入的楼层不存在！");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
	         }
	         else
				{
					message=true;
				}*/
	         
	        //3.检查宿舍是否满人
	        dormitory=jt1.getText();
	        roomnum=jt3.getText();
	        sql="select * from room where DomNum='"+dormitory+"'and RoomNum='"+roomnum+"'";
			try {
				 //加载驱动(把需要得驱动程序加入内存)
		    	  Class.forName(driver);
		    	  //得到连接
		    	  ct=DriverManager.getConnection(url,username,password);
		    	  //创建statement(主要用于sql语句)   	  
				ps=ct.prepareStatement(sql);
				rs_room=ps.executeQuery();
				rs_room.last();
			    rowCount =rs_room.getRow();
				/*if(rowCount>4)
				{
				    jdl.setText("该宿舍满人！");
				    jt2.setText("");
				    jt3.setText("");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
				}
				else
				{
					message=true;
				}*/
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			if(result==0)
			  {
				    jt1.setText("");
				    jdl.setText("你输入的宿舍楼不存在！");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
					message=false;
			  }
			
			else if(DorSex.equals(stusex)==false)
			  {
				    
				    jt1.setText("");
				    jdl.setText("学生无法入住该宿舍楼！");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
					message=false;
			  }
			
			//2.检查修改的楼层与宿舍是否对应
	   
	        else if(floor.equals(Character.toString(roomfirst[0]))==false)
	        {
				    jdl.setText("你输入的楼层与宿舍不对应！");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
					message=false;
	        }
	        else if(Integer.parseInt(floor)<1||Integer.parseInt(floor)>5)
	        {
				    jdl.setText("你输入的楼层不存在！");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
					message=false;
	        }
	        else if(rowCount>4)
			{
			    jdl.setText("该宿舍满人！");
			    jt2.setText("");
			    jt3.setText("");
			    diag.add(jdl);
				diag.setLocationRelativeTo(null);
				diag.setVisible(true);
				message=false;
			}
	        else 
	        {
	            message=true;
	        }
			
			
	        
	     if(message==true)
	     {
		// TODO Auto-generated method stub
		 try {
	    	  //加载驱动(把需要得驱动程序加入内存)
	    	  Class.forName(driver);
	    	  //得到连接
	    	  ct=DriverManager.getConnection(url,username,password);
	    	  //创建statement(主要用于sql语句)   	  
	    
	    	  sql="update room set DomNum=?,Floor=?,RoomNum=?,Beds=?,Price=? where Members=?";
	    	  //給问号赋值
	    	  ps=ct.prepareStatement(sql);
	 		  ps.setString(1, jt1.getText());
	 		  ps.setString(2, jt2.getText());
	 		  ps.setString(3, jt3.getText());
	 		  ps.setString(4, jt4.getText());
	 		  ps.setString(5, jt5.getText());
	 		  ps.setString(6, jt6.getText());
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
}
