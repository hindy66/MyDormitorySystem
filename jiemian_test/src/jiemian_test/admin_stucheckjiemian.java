package jiemian_test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;



public class admin_stucheckjiemian extends JDialog  implements ActionListener{
	
	//加载数据以及进行数据库操纵需要定义的对象
		PreparedStatement ps=null;
		Statement ps1=null;
		Connection ct=null;
		ResultSet rs=null;
		String jdbcurl="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
		String username="root";
		String password="";
		String sql;
	
	
	admin_stucheckjiemian stucheck;
	//需要的控件
    JPanel jp1,jp2;
    JLabel jl1;
    JButton jb1,jb2,jb3,jb4;
    JTable jt;
    JTextField jtf1;
	Vector rowData,columnName;
	JScrollPane jsp=null;
	TabelModule sm;

	public admin_stucheckjiemian(Frame owner,String title,boolean moduel) {
		super(owner,title,moduel);
		//上部内容
		   jp1=new JPanel();
		   jtf1=new JTextField(10);
		   jb1=new JButton("查询");
		   jl1=new JLabel("请输入名字:");
		   jb1.addActionListener(this);
		   
		   //把各个控件加入到jp1中
		   jp1.add(jl1);
		   jp1.add(jtf1);
		   jp1.add(jb1);
		   
		   //下部内容
		   jp2=new JPanel();
		   //jb2=new JButton("添加");
		   jb3=new JButton("修改");
		   jb3.addActionListener(this);
		   jb4=new JButton("删除");
		   jb4.addActionListener(this);
		   //jb2.addActionListener(this);
		   
		   //把各个控件加入到JB2当中
		   //jp2.add(jb2);
		   /*jp2.add(jb3);
		   jp2.add(jb4);*/
		   
		   //中间内容
		   //设置列名
		 		columnName=new Vector();
		 		columnName.add("学号");
		 		columnName.add("名字");
		 		columnName.add("性别");
		 		columnName.add("年龄");
		 		columnName.add("专业");
		 		
		 		//rowData可以存放多行数据
		 		rowData=new Vector();
		         
                sm=new TabelModule();
                
                jt=new JTable(sm);

				//初始化可滚动文本框
			    jsp=new JScrollPane(jt);
			    
			    //将文本框加入到JFrame当中
				this.add(jsp);
				this.add(jp1, "North");
				this.add(jp2, "South");
				
				
				 this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			     this.setSize(400,300);
			     this.setVisible(true);
			     this.setLocationRelativeTo(null);
}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 JButton bt=(JButton)e.getSource();
		 if(bt==jb1)
		 {
		    String name=jtf1.getText().trim();
			
			//查询的sql语句
			//String sql_check="select * from stus where stuname='"+name+"'";
            String sql_check="SELECT * FROM stus WHERE stuname LIKE'%"+name+"%'";
			//创建一个新的module对象
			 sm=new TabelModule(sql_check);
			
			//更新JTable
			jt.setModel(sm);
		 }
		 if(bt==jb3)
		 {
			 int rownum= this.jt.getSelectedRow();
		    	if(rownum==-1)
			    {	    	  
					 //出现提示窗口
						JOptionPane.showMessageDialog(this, "请先选中");
			    }
		    	admin_stuupdate stu=new admin_stuupdate(stucheck,"修改学生信息",true,sm,rownum);
		    	 //更新数据模型
		        sm=new TabelModule();
					
				//更新JTable
				jt.setModel(sm);
		 }
		 else if(bt==jb4)
		    {
		    //JTabel的getSelectedRow方法会返回用户点中的行
		    //如果用户一行没选，就返回-1.	
		      int rownum= this.jt.getSelectedRow();
		         if(rownum==-1)
		        {	    	  
				 //出现提示窗口
					JOptionPane.showMessageDialog(this, "请先选中");
		        }
		         else 
		         {
		        	//表中的列编号是从0开始的
		            String stuid=(String)sm.getValueAt(rownum, 0);
		            String stuname=(String)sm.getValueAt(rownum, 1);
		            System.out.println("我选中的学生id是:"+stuid);
		            
		     		//加载驱动\
		     		try {
		     			Class.forName("com.mysql.jdbc.Driver");
		     			ct=DriverManager.getConnection(jdbcurl, username, password);
		     			sql="delete from stus where stuid='"+stuid+"'";
		     			ps=ct.prepareStatement(sql);
		     			ps1=ct.createStatement();
		     			ps1.executeUpdate("delete from user where accout='"+stuid+"'");
		     			ps1.executeUpdate("delete from room where Members='"+stuname+"'");
		     			ps.executeUpdate();	     			
		     		}catch(Exception e1){
		     			e1.printStackTrace();
		     		}finally {
		     			//关闭资源很重要
		     			try {
		     				if(ps!=null)
		     				{
		     				  ps.close();
		     				}
		     				if(ct!=null)
		     				{
		     					ct.close();
		     				}
		     			} catch (SQLException e2) {
		     				// TODO Auto-generated catch block
		     				e2.printStackTrace();

		     			}
		     		}
		         }
		         //更新数据模型
		         sm=new TabelModule();
					
				//更新JTable
				jt.setModel(sm);
			
		    }
	}
	
	public void stuupdate_interface (boolean c) {
		if(c==true)
		{
		   jp2.add(jb3);
		   jp2.add(jb4);
		   jp1.removeAll();
		 
		}
	}
}
