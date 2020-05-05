package jiemian_test;

import java.sql.*;




//用于分配宿舍
public class admin_roommatch {
	//加载数据以及进行数据库操纵需要定义的对象   
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
		String name="彭于晏";
		//学生为男性
		if(stusex.equals("男"))
		{
			while(flag==true)
			{
				for(i=0;i<=9;i++)
				{
					manroomnum=""+manfloor+0+i+"";
				    
					System.out.print(manroomnum);
					try {
				    	  //加载驱动(把需要得驱动程序加入内存)
				    	  Class.forName(driver);
				    	  //得到连接
				    	  ct=DriverManager.getConnection(url,username,password);
				    	  
				    	  //创建statement(主要用于sql语句)
				    	  sql="select * from room where DomNum='"+mandormitory+"'and RoomNum='"+manroomnum+"'";
	
				    	  sm=ct.prepareStatement(sql);
				    	  //执行各种数据库操作
				    	  //演示添加数据到stus,room,user表
				    	  //excuteupdate可以执行crud操作
				    	  rs=sm.executeQuery();
				    	  rs.last();
				    	  int rowCount = rs.getRow();
				    	  System.out.println("有"+rowCount+"条记录");
				    	  if(rowCount<4)
				    	  {
				    		  break;
				    	  }
				      }catch(Exception e1) {
				    	 e1.printStackTrace(); 
				      }				//关闭资源很重要
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
		
		
		//学生为女性
		else
		{
			while(flag==true)
			{
				for(i=0;i<=9;i++)
				{
					womanroomnum=""+womanfloor+0+i+"";
				    
					System.out.print(womanroomnum);
					try {
				    	  //加载驱动(把需要得驱动程序加入内存)
				    	  Class.forName(driver);
				    	  //得到连接
				    	  ct=DriverManager.getConnection(url,username,password);
				    	  
				    	  //创建statement(主要用于sql语句)
				    	  sql="select * from room where DomNum='"+womandormitory+"'and RoomNum='"+womanroomnum+"'";
	
				    	  sm=ct.prepareStatement(sql);
				    	  //执行各种数据库操作
				    	  //演示添加数据到stus,room,user表
				    	  //excuteupdate可以执行crud操作
				    	  rs=sm.executeQuery();
				    	  rs.last();
				    	  int rowCount = rs.getRow();
				    	  System.out.println("有"+rowCount+"条记录");
				    	  if(rowCount<4)
				    	  {
				    		  break;
				    	  }
				      }catch(Exception e1) {
				    	 e1.printStackTrace(); 
				      }				//关闭资源很重要
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
