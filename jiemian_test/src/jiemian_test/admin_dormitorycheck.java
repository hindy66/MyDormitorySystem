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
	//���������Լ��������ݿ������Ҫ����Ķ���
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	String jdbcurl="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
	String username="root";
	String password="";
	String sql;


    admin_stucheckjiemian stucheck;
    //��Ҫ�Ŀؼ�
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
		  //�ϲ�����
		   jp1=new JPanel();
		   jtf1=new JTextField(10);
		   jb_dormitorycheck=new JButton("��ѯ");
		   jl1=new JLabel("����������¥:");
		   jb_dormitorycheck.addActionListener(this);
		   
		   //�Ѹ����ؼ����뵽jp1��
		   jp1.add(jl1);
		   jp1.add(jtf1);
		   jp1.add(jb_dormitorycheck);
		   jp1.setSize(300,50);
		   jp1.setLocation(0, 0);
		   
		   //�²�����
		   jp2=new JPanel();
		   
		   

		   
		      //����¥����		
		         
                sm1=new TableModule_dormitory();
                jt1=new JTable(sm1);
                jt1.setSize(50, 100);
				//��ʼ���ɹ����ı���
			    jsp1=new JScrollPane(jt1);			    
			    jsp1.setSize(300, 200);
			    jsp1.setLocation(150,50);
			   
			    
			    //��������ѯѧ��������Ϣ
				jtf_stucheck=new JTextField(10);
				jb_stucheck=new JButton("��ѯ");
				jl_stucheck=new JLabel("����������:");
		        jb_stucheck.addActionListener(this);
		        jp2.add(jl_stucheck);
		        jp2.add(jtf_stucheck);
		        jp2.add(jb_stucheck);
		        jp2.setSize(300,50);
		        jp2.setLocation(0, 250);
		        
		        
			    //��������
		        
		        sm2=new TabelModule_rooms("");
			    jt2=new JTable(sm2);
			    jsp2=new JScrollPane(jt2); 
			    jsp2.setSize(300,200);
			    jsp2.setLocation(150,300);
			    
			    //������Ϣ������ť
			    jp3=new JPanel();
			    jb3=new JButton("�������");
				jb3.addActionListener(this);
				jp3.add(jb3);
				jp3.setSize(100, 100);
				jp3.setLocation(450,400);

				
				
			    
			    //���ı�����뵽JFrame����
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
			
			//��ѯ��sql���
			//String sql_check="select * from stus where stuname='"+name+"'";
            String sql_check="SELECT * FROM dormitory WHERE DomNum LIKE'%"+name+"%'";
			//����һ���µ�module����
			 sm1=new TableModule_dormitory(sql_check);
			
			//����JTable
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
				 //������ʾ����
					JOptionPane.showMessageDialog(this, "����ѡ��");
		    }
			else
			{		
			  admin_roomupdate s=new admin_roomupdate(this,"������Ϣ����",true,sm2,rownum);
			   sm2=new TabelModule_rooms();
				
				//����JTable
		       jt2.setModel(sm2);
			}
		}
		
	}

}
