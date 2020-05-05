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
	
	//��������Ҫ�Ŀؼ�
	JLabel lb1,lb2,lb3,lb4,lb5,jdl,lb6;
	JButton jb_add,jb_cancel;
	JTextField jt1,jt2,jt3,jt4,jt5,jt6;
	JPanel jp1,jp2,jp3;
	static JDialog diag;
	
    //�ж��޸���Ϣ�Ƿ�Ϸ�
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
	
		lb1=new JLabel("����¥");
		lb2=new JLabel("¥��");
		lb3=new JLabel("����");
		lb4=new JLabel("����");
		lb5=new JLabel("�۸�");
		lb6=new JLabel("��Ա");
		
		jb_add=new JButton("�޸�");
		jb_cancel=new JButton("ȡ��");
		jb_add.addActionListener(this);
		jb_cancel.addActionListener(this);
		
		//��ʼ���ı���
		jt1=new JTextField();
		jt1.setText((String)sm.getValueAt(rownum, 0));
		//ʹ��jt4,5,6���ܱ��޸�
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
			//����޸ĵ���Ϣ�Ƿ�Ϸ�
			
			
			//1.�������¥�Ƿ����
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
					    jdl.setText("�����������¥�����ڣ�");
					    diag.add(jdl);
						diag.setLocationRelativeTo(null);
						diag.setVisible(true);
				  }
				  else 
				  {
					  message=true; 
				  }*/
				  //���ѧ���Ա��Ƿ�������¥�Ա�ƥ��
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
					    jdl.setText("ѧ���޷���ס������¥��");
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
		    	  //�ر���Դ
		    	  //�ر�˳���ǣ�˭�󴴽���˭�ȹر�
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
	        
	        //2.����޸ĵ�¥���������Ƿ��Ӧ
	        floor=jt2.getText();
	        room=jt3.getText();
	        roomfirst=room.toCharArray();
	        /*
	         if(floor.equals(Character.toString(roomfirst[0]))==false)
	        {
				    jdl.setText("�������¥�������᲻��Ӧ��");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
	        }
	         else if(Integer.parseInt(floor)<1||Integer.parseInt(floor)>5)
	         {
				    jdl.setText("�������¥�㲻���ڣ�");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
	         }
	         else
				{
					message=true;
				}*/
	         
	        //3.��������Ƿ�����
	        dormitory=jt1.getText();
	        roomnum=jt3.getText();
	        sql="select * from room where DomNum='"+dormitory+"'and RoomNum='"+roomnum+"'";
			try {
				 //��������(����Ҫ��������������ڴ�)
		    	  Class.forName(driver);
		    	  //�õ�����
		    	  ct=DriverManager.getConnection(url,username,password);
		    	  //����statement(��Ҫ����sql���)   	  
				ps=ct.prepareStatement(sql);
				rs_room=ps.executeQuery();
				rs_room.last();
			    rowCount =rs_room.getRow();
				/*if(rowCount>4)
				{
				    jdl.setText("���������ˣ�");
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
				    jdl.setText("�����������¥�����ڣ�");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
					message=false;
			  }
			
			else if(DorSex.equals(stusex)==false)
			  {
				    
				    jt1.setText("");
				    jdl.setText("ѧ���޷���ס������¥��");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
					message=false;
			  }
			
			//2.����޸ĵ�¥���������Ƿ��Ӧ
	   
	        else if(floor.equals(Character.toString(roomfirst[0]))==false)
	        {
				    jdl.setText("�������¥�������᲻��Ӧ��");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
					message=false;
	        }
	        else if(Integer.parseInt(floor)<1||Integer.parseInt(floor)>5)
	        {
				    jdl.setText("�������¥�㲻���ڣ�");
				    diag.add(jdl);
					diag.setLocationRelativeTo(null);
					diag.setVisible(true);
					message=false;
	        }
	        else if(rowCount>4)
			{
			    jdl.setText("���������ˣ�");
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
	    	  //��������(����Ҫ��������������ڴ�)
	    	  Class.forName(driver);
	    	  //�õ�����
	    	  ct=DriverManager.getConnection(url,username,password);
	    	  //����statement(��Ҫ����sql���)   	  
	    
	    	  sql="update room set DomNum=?,Floor=?,RoomNum=?,Beds=?,Price=? where Members=?";
	    	  //�o�ʺŸ�ֵ
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
	    		  System.out.println("ִ�гɹ���");
	    	  }
	    	  else
	    	  {
	    		  System.out.println("ִ�г���");
	    	  }
	      }catch(Exception e1) {
	    	 e1.printStackTrace(); 
	      }finally {
	    	  //�ر���Դ
	    	  //�ر�˳���ǣ�˭�󴴽���˭�ȹر�
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
		  
		    jdl.setText("��Ϣ��ӳɹ���");
		    diag.add(jdl);
			diag.setLocationRelativeTo(null);
			diag.setVisible(true);
			this.dispose();
		   }
		}
	}
}
