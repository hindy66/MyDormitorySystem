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
	
	//���������Լ��������ݿ������Ҫ����Ķ���
		PreparedStatement ps=null;
		Statement ps1=null;
		Connection ct=null;
		ResultSet rs=null;
		String jdbcurl="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
		String username="root";
		String password="";
		String sql;
	
	
	admin_stucheckjiemian stucheck;
	//��Ҫ�Ŀؼ�
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
		//�ϲ�����
		   jp1=new JPanel();
		   jtf1=new JTextField(10);
		   jb1=new JButton("��ѯ");
		   jl1=new JLabel("����������:");
		   jb1.addActionListener(this);
		   
		   //�Ѹ����ؼ����뵽jp1��
		   jp1.add(jl1);
		   jp1.add(jtf1);
		   jp1.add(jb1);
		   
		   //�²�����
		   jp2=new JPanel();
		   //jb2=new JButton("���");
		   jb3=new JButton("�޸�");
		   jb3.addActionListener(this);
		   jb4=new JButton("ɾ��");
		   jb4.addActionListener(this);
		   //jb2.addActionListener(this);
		   
		   //�Ѹ����ؼ����뵽JB2����
		   //jp2.add(jb2);
		   /*jp2.add(jb3);
		   jp2.add(jb4);*/
		   
		   //�м�����
		   //��������
		 		columnName=new Vector();
		 		columnName.add("ѧ��");
		 		columnName.add("����");
		 		columnName.add("�Ա�");
		 		columnName.add("����");
		 		columnName.add("רҵ");
		 		
		 		//rowData���Դ�Ŷ�������
		 		rowData=new Vector();
		         
                sm=new TabelModule();
                
                jt=new JTable(sm);

				//��ʼ���ɹ����ı���
			    jsp=new JScrollPane(jt);
			    
			    //���ı�����뵽JFrame����
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
			
			//��ѯ��sql���
			//String sql_check="select * from stus where stuname='"+name+"'";
            String sql_check="SELECT * FROM stus WHERE stuname LIKE'%"+name+"%'";
			//����һ���µ�module����
			 sm=new TabelModule(sql_check);
			
			//����JTable
			jt.setModel(sm);
		 }
		 if(bt==jb3)
		 {
			 int rownum= this.jt.getSelectedRow();
		    	if(rownum==-1)
			    {	    	  
					 //������ʾ����
						JOptionPane.showMessageDialog(this, "����ѡ��");
			    }
		    	admin_stuupdate stu=new admin_stuupdate(stucheck,"�޸�ѧ����Ϣ",true,sm,rownum);
		    	 //��������ģ��
		        sm=new TabelModule();
					
				//����JTable
				jt.setModel(sm);
		 }
		 else if(bt==jb4)
		    {
		    //JTabel��getSelectedRow�����᷵���û����е���
		    //����û�һ��ûѡ���ͷ���-1.	
		      int rownum= this.jt.getSelectedRow();
		         if(rownum==-1)
		        {	    	  
				 //������ʾ����
					JOptionPane.showMessageDialog(this, "����ѡ��");
		        }
		         else 
		         {
		        	//���е��б���Ǵ�0��ʼ��
		            String stuid=(String)sm.getValueAt(rownum, 0);
		            String stuname=(String)sm.getValueAt(rownum, 1);
		            System.out.println("��ѡ�е�ѧ��id��:"+stuid);
		            
		     		//��������\
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
		     			//�ر���Դ����Ҫ
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
		         //��������ģ��
		         sm=new TabelModule();
					
				//����JTable
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
