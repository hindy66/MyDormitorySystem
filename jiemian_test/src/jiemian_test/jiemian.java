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

public class jiemian extends JFrame implements ActionListener {
	public static final String[] args = null;
	static textlistener tlist=new textlistener();
	static jiemian mJFrame=new jiemian();
	static JDialog diag=new JDialog(mJFrame);
	static JDialog diag_log=new JDialog(mJFrame);
	static  MyWinListener wlist=new MyWinListener();
    static JButton buttonCLOSE=new JButton("关闭");
    static JButton buttonCANLE=new JButton("取消");
    static JLabel lbl=new JLabel("欢迎进入学生宿舍管理系统");
    static JLabel lbl_log=new JLabel("登录成功！");
    static JLabel lbl_id=new JLabel("用户名:");
    static JLabel lbl_password=new JLabel("密码:");
    static JButton button1=new JButton("登录");
    static JButton button2=new JButton("退出");
    static JTextField id=new JTextField(10);
    static JPasswordField password=new JPasswordField(10);
    static ImageIcon bg=new ImageIcon("界面.jpg");
    
	//加载数据以及进行数据库操纵需要定义的对象
    static PreparedStatement ps=null;
    static Connection ct=null;
    static ResultSet rs=null;
    static String jdbcurl="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
    static String username="root";
    static String pd="";
	static String uname[]= new String[40];
	static String pword[]=new String[40];
	static int right[]=new int[40];
	static String name[]=new String[40];
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Container container = mJFrame.getContentPane();
        mJFrame.setSize(600,600);
        mJFrame.setLocation(200,200);
        mJFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        mJFrame.setTitle("学生宿舍管理系统");
		container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(null);
        lbl.setSize(200,100);
        lbl.setBackground(Color.yellow);
        button1.setSize(100,50);
        button2.setSize(100,50);
        id.setSize(200,30);
        password.setSize(200,30);
        lbl_id.setSize(50,30);
        lbl_password.setSize(30,30);
        lbl.setLocation(225, 0);
        button1.setLocation(150, 500);
        button2.setLocation(350,500);
        id.setLocation(200,200);
        password.setLocation(200,300 );
        lbl_id.setLocation(150,200);
        lbl_password.setLocation(150, 300);
        container.add(lbl);
        container.add(button1);
        container.add(button2);
        container.add(id);
        container.add(password);
        container.add(lbl_id);
        container.add(lbl_password);
        diag.setSize(200,150);
        diag.setLayout(new FlowLayout(FlowLayout.CENTER,5,20));
        diag.add(buttonCLOSE);
        diag.add(buttonCANLE);
        diag_log.setSize(300,300);
        diag_log.add(lbl_log);
        diag.setLayout(new FlowLayout(FlowLayout.CENTER,5,20));
        id.addActionListener(tlist);
        password.addActionListener(tlist);
        buttonCLOSE.addActionListener(mJFrame);
        buttonCANLE.addActionListener(mJFrame);
        button2.addActionListener(mJFrame);
        button1.addActionListener(tlist);
        mJFrame.addWindowListener(wlist);
       
        JLabel label = new JLabel(bg);
		label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
		mJFrame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
		JPanel jp=(JPanel)mJFrame.getContentPane(); 
		jp.setOpaque(false);
        
        mJFrame.setVisible(true);
        
        
        try {
			Class.forName("com.mysql.jdbc.Driver");
			ct=DriverManager.getConnection(jdbcurl, username, pd);
			ps=ct.prepareStatement("select * from user");
			rs=ps.executeQuery();
			int i=0;
			while(rs.next()&&i<39) {
				uname[i]=rs.getString(1);
				pword[i]=rs.getString(2);
				right[i]=rs.getInt(3);
				name[i]=rs.getString(4);
				i++;
			}
			
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
    static class MyWinListener implements WindowListener{

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		diag.setLocationRelativeTo(null);
		diag.setVisible(true);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton bt=(JButton)e.getSource();
		if(bt==buttonCLOSE) {
			diag.dispose();
			mJFrame.dispose();
			System.exit(0);
		}
		if(bt==buttonCANLE) {
			diag.setVisible(false);
		}
		if(bt==button1) {
			
		}
		if(bt==button2) {
			mJFrame.dispose();
			System.exit(0);
		}
		
		
	}
	 static class textlistener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i=0;
			 for(i=0;i<=39;i++)
			   { 
				   if(id.getText().equals(uname[i])&&password.getText().equals(pword[i])) 
				   {
					   if(right[i]==8)
					   {
					     id.setText("");
					     password.setText("");
					     mJFrame.dispose();
					     admin_jiemian ad=new admin_jiemian();
				         admin_jiemian.main(args);
				         break;
					   }
					   if(right[i]==4)
					   {
						   mJFrame.dispose();
						   aunt_jiemian.main(args);
						   break;
					   }
					   if(right[i]==0)
					   {
						   mJFrame.dispose();
						   stu_jiemian stu=new stu_jiemian(name[i]);
						   break;
					   }
				    }
			   }
				if(i>39) {
					diag_log.setLocationRelativeTo(null);
					diag_log.setVisible(true);
					lbl_log.setText("你的输入的用户或密码不正确！");
					id.setText("");
					password.setText("");
				}
			}
			 
		 }
    
}





