package jiemian_test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class stu_dormitorycheck extends JDialog  implements ActionListener {

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
	    
	    String stuname;
	    
	    public stu_dormitorycheck (Frame owner,String title,boolean moduel,String stuname)
	    {
        
	    	super(owner,title,moduel);
	    	this.stuname=stuname;
	    	
			  //�ϲ�����
			   jp1=new JPanel();
			   jtf1=new JTextField(10);
			   jb_dormitorycheck=new JButton("��ѯ");
			   jl1=new JLabel("�����ڵ�����¥:");
			   jb_dormitorycheck.addActionListener(this);
			   
			   //�Ѹ����ؼ����뵽jp1��
			   jp1.add(jl1);
			   /*jp1.add(jtf1);
			   jp1.add(jb_dormitorycheck);*/
			   jp1.setSize(300,50);
			   jp1.setLocation(0, 0);
			   
			   //�²�����
			   jp2=new JPanel();
			   
			   

			   
			       //����¥����
			        sql="select * from dormitory where DomNum in(SELECT DomNum from room where Members='"+stuname+"')";
	                sm1=new TableModule_dormitory(sql);
	                jt1=new JTable(sm1);
	                jt1.setSize(50, 100);
					//��ʼ���ɹ����ı���
				    jsp1=new JScrollPane(jt1);			    
				    jsp1.setSize(300, 200);
				    jsp1.setLocation(150,50);
				   
				    
				    //��������ѯѧ��������Ϣ
					jtf_stucheck=new JTextField(10);
					jb_stucheck=new JButton("��ѯ");
					jl_stucheck=new JLabel("�����ڵ�����:");
			        jb_stucheck.addActionListener(this);
			        jp2.add(jl_stucheck);
			        /*jp2.add(jtf_stucheck);
			        jp2.add(jb_stucheck);*/
			        jp2.setSize(300,50);
			        jp2.setLocation(0, 250);
			        
			        
				    //��������
			        
			        sql="select * from room where RoomNum in(SELECT RoomNum from room where Members='"+stuname+"') and DomNum in(SELECT DomNum from room where Members='"+stuname+"')";
			        sm2=new TabelModule_rooms(sql);
				    jt2=new JTable(sm2);
				    jsp2=new JScrollPane(jt2); 
				    jsp2.setSize(300,200);
				    jsp2.setLocation(150,300);
				    
				    //������Ϣ������ť
				    /*jp3=new JPanel();
				    jb3=new JButton("�������");
					jb3.addActionListener(this);
					jp3.add(jb3);
					jp3.setSize(100, 100);
					jp3.setLocation(450,400);*/

					
					
				    
				    //���ı�����뵽JFrame����
				    this.getContentPane().add(jp1);
				    this.getContentPane().add(jsp1);
				    this.add(jp2);
					this.add(jsp2);
					/*this.add(jp3);*/
				    
					
					
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
		
	}

}
