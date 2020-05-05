package jiemian_test;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class admin_dormitorycheck extends JDialog  implements ActionListener{
	//加载数据以及进行数据库操纵需要定义的对象
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	String jdbcurl="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
	String username="root";
	String password="";
	String sql;


    admin_stucheckjiemian stucheck;
    //需要的控件
    JPanel jp1,jp2,jp3;
    JLabel jl1,jl_stucheck;
    JButton jb_dormitorycheck,jb2,jb3,jb4,jb_stucheck;
    JTable jt1,jt2,jtf3;
    JTextField jtf1,jtf_stucheck;
    Vector rowData,columnName;
    JScrollPane jsp1=null,jsp2=null;
    TableModule_dormitory sm1;
    TabelModule_rooms sm2;
    
    public admin_dormitorycheck (Frame owner,String title,boolean moduel)
    {

    	super(owner,title,moduel);
		  //上部内容
		   jp1=new JPanel();
		   jtf1=new JTextField(10);
		   jb_dormitorycheck=new JButton("查询");
		   jl1=new JLabel("请输入宿舍楼:");
		   jb_dormitorycheck.addActionListener(this);
		   
		   //把各个控件加入到jp1中
		   jp1.add(jl1);
		   jp1.add(jtf1);
		   jp1.add(jb_dormitorycheck);
		   jp1.setSize(300,50);
		   jp1.setLocation(0, 0);
		   
		   //下部内容
		   jp2=new JPanel();
		   
		   

		   
		      //宿舍楼内容		
		         
                sm1=new TableModule_dormitory();
                jt1=new JTable(sm1);
                jt1.setSize(50, 100);
				//初始化可滚动文本框
			    jsp1=new JScrollPane(jt1);			    
			    jsp1.setSize(300, 200);
			    jsp1.setLocation(150,50);
			   
			    
			    //按姓名查询学生宿舍信息
				jtf_stucheck=new JTextField(10);
				jb_stucheck=new JButton("查询");
				jl_stucheck=new JLabel("请输入名字:");
		        jb_stucheck.addActionListener(this);
		        jp2.add(jl_stucheck);
		        jp2.add(jtf_stucheck);
		        jp2.add(jb_stucheck);
		        jp2.setSize(300,50);
		        jp2.setLocation(0, 250);
		        
		        
			    //宿舍内容
		        
		        sm2=new TabelModule_rooms("");
			    jt2=new JTable(sm2);
			    jsp2=new JScrollPane(jt2); 
			    jsp2.setSize(300,200);
			    jsp2.setLocation(150,300);
			    
			    //宿舍信息调整按钮
			    jp3=new JPanel();
			    jb3=new JButton("宿舍调整");
				jb3.addActionListener(this);
				jp3.add(jb3);
				jp3.setSize(100, 100);
				jp3.setLocation(450,400);

				
				
			    
			    //将文本框加入到JFrame当中
			    this.getContentPane().add(jp1);
			    this.getContentPane().add(jsp1);
			    this.add(jp2);
				this.add(jsp2);
				this.add(jp3);
			    
				
				
			     this.setLayout(null);
				 this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			     this.setSize(600,600);
			     this.setVisible(true);
			     this.setBackground(Color.GRAY);
			     this.setLocationRelativeTo(null);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton bt=(JButton)e.getSource();
		if(bt==jb_dormitorycheck)
		{
           String name=jtf1.getText().trim();
			
			//查询的sql语句
			//String sql_check="select * from stus where stuname='"+name+"'";
            String sql_check="SELECT * FROM dormitory WHERE DomNum LIKE'%"+name+"%'";
			//创建一个新的module对象
			 sm1=new TableModule_dormitory(sql_check);
			
			//更新JTable
			 jt1.setModel(sm1);
		}
		if(bt==jb_stucheck)
		{
			String name=jtf_stucheck.getText().trim();
			
			String sql="SELECT * FROM room WHERE Members LIKE'%"+name+"%'";
			
			sm2=new TabelModule_rooms(sql);
			
			jt2.setModel(sm2);
		}
		if(bt==jb3)
		{
			int rownum= this.jt2.getSelectedRow();
			if(rownum==-1)
		    {	    	  
				 //出现提示窗口
					JOptionPane.showMessageDialog(this, "请先选中");
		    }
			else
			{		
			  admin_roomupdate s=new admin_roomupdate(this,"宿舍信息调整",true,sm2,rownum);
			   sm2=new TabelModule_rooms();
				
				//更新JTable
		       jt2.setModel(sm2);
			}
		}
		
	}

}
