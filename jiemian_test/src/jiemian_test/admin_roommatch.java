package jiemian_test;

import java.sql.*;




//���ڷ�������
public class admin_roommatch {
	//���������Լ��������ݿ������Ҫ����Ķ���   
	PreparedStatement sm=null;
	Connection ct=null;
	ResultSet rs=null;
	String jdbcurl="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
	String username="root";
	String password="";
	String driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
	String sql;
	
	boolean flag=true;
	int i=0,floor=1;
	int mandornum=19,womandornum=1;
    String mandormitory="c19",womandormitory="c1";
	String manfloor="1",womanfloor="1";
    String manroomnum;
	String womanroomnum;
	String finalfloornum,finalroomnum,finaldormitory;
	public  admin_roommatch(String stusex,String studept)
	{
		String name="������";
		//ѧ��Ϊ����
		if(stusex.equals("��"))
		{
			while(flag==true)
			{
				for(i=0;i<=9;i++)
				{
					manroomnum=""+manfloor+0+i+"";
				    
					System.out.print(manroomnum);
					try {
				    	  //��������(����Ҫ��������������ڴ�)
				    	  Class.forName(driver);
				    	  //�õ�����
				    	  ct=DriverManager.getConnection(url,username,password);
				    	  
				    	  //����statement(��Ҫ����sql���)
				    	  sql="select * from room where DomNum='"+mandormitory+"'and RoomNum='"+manroomnum+"'";
	
				    	  sm=ct.prepareStatement(sql);
				    	  //ִ�и������ݿ����
				    	  //��ʾ������ݵ�stus,room,user��
				    	  //excuteupdate����ִ��crud����
				    	  rs=sm.executeQuery();
				    	  rs.last();
				    	  int rowCount = rs.getRow();
				    	  System.out.println("��"+rowCount+"����¼");
				    	  if(rowCount<4)
				    	  {
				    		  break;
				    	  }
				      }catch(Exception e1) {
				    	 e1.printStackTrace(); 
				      }				//�ر���Դ����Ҫ
					try {
						if(sm!=null)
						{
						  sm.close();
						}
						if(ct!=null)
						{
							ct.close();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}finally {
				    	  //�ر���Դ
				    	  //�ر�˳���ǣ�˭�󴴽���˭�ȹر�
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
				}
				 
				if(i>9)
				{
					if(floor<5)
					{
					  floor=floor+1;
					  manfloor=""+floor+"";
					}
					else
					{
					   mandornum=mandornum+1;
					   floor=1;
					   mandormitory="c"+mandornum+"";					   
					}
				}
				else
				{
					flag=false;
				}
			}
			
			finalroomnum=manroomnum;
			finaldormitory=mandormitory;
			finalfloornum=Integer.toString(floor);
				
		}
		
		
		//ѧ��ΪŮ��
		else
		{
			while(flag==true)
			{
				for(i=0;i<=9;i++)
				{
					womanroomnum=""+womanfloor+0+i+"";
				    
					System.out.print(womanroomnum);
					try {
				    	  //��������(����Ҫ��������������ڴ�)
				    	  Class.forName(driver);
				    	  //�õ�����
				    	  ct=DriverManager.getConnection(url,username,password);
				    	  
				    	  //����statement(��Ҫ����sql���)
				    	  sql="select * from room where DomNum='"+womandormitory+"'and RoomNum='"+womanroomnum+"'";
	
				    	  sm=ct.prepareStatement(sql);
				    	  //ִ�и������ݿ����
				    	  //��ʾ������ݵ�stus,room,user��
				    	  //excuteupdate����ִ��crud����
				    	  rs=sm.executeQuery();
				    	  rs.last();
				    	  int rowCount = rs.getRow();
				    	  System.out.println("��"+rowCount+"����¼");
				    	  if(rowCount<4)
				    	  {
				    		  break;
				    	  }
				      }catch(Exception e1) {
				    	 e1.printStackTrace(); 
				      }				//�ر���Դ����Ҫ
					try {
						if(sm!=null)
						{
						  sm.close();
						}
						if(ct!=null)
						{
							ct.close();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}finally {
				    	  //�ر���Դ
				    	  //�ر�˳���ǣ�˭�󴴽���˭�ȹر�
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
				}
				 
				if(i>9)
				{
					if(floor<5)
					{
					  floor=floor+1;
					  womanfloor=""+floor+"";
					}
					else
					{
					   womandornum=mandornum+1;
					   floor=1;
					   womandormitory="c"+womandornum+"";					   
					}
				}
				else
				{
					flag=false;
				}
			}
			
			finalroomnum=womanroomnum;
			finaldormitory=womandormitory;
			finalfloornum=Integer.toString(floor);
	}

  }
}
