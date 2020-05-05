package jiemian_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class TabelModule extends AbstractTableModel {

	Vector rowData,columnName;
	
	
	
	//加载数据以及进行数据库操纵需要定义的对象
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		String jdbcurl="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
		String username="root";
		String password="";
	
		
	void init(String sql)
	{
		if(sql.equals(""))
		{
			sql="select * from stus";
		}
		columnName=new Vector();
 		columnName.add("学号");
 		columnName.add("名字");
 		columnName.add("性别");
 		columnName.add("年龄");
 		columnName.add("专业");
 		
 		//rowData可以存放多行数据
 		rowData=new Vector();
         
 		//加载驱动\
 		try {
 			Class.forName("com.mysql.jdbc.Driver");
 			ct=DriverManager.getConnection(jdbcurl, username, password);
 			ps=ct.prepareStatement(sql);
 			rs=ps.executeQuery();
 			while(rs.next()) {
 				Vector hang=new Vector();
 				hang.add(rs.getString(1));
 				hang.add(rs.getString(2));
 				hang.add(rs.getString(3));
 				hang.add(rs.getInt(4));
 				hang.add(rs.getString(5));
 				//加入到rowData(一行一行数据的添加，即每个学生为一个组)
 				rowData.add(hang);
 			}
 			
 		}catch(Exception e){
 			e.printStackTrace();
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
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();

 			}
 		}
	}
	
	//通过sql语句来获取数据
	public TabelModule(String sql)
	{
		this.init(sql);
	}
		
		
   //构造方法，用于初始化得到的表
	public  TabelModule()
	{
		this.init("");
	}
	
	//得到表中共有多少行数
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	//得到表中共有多少列数
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnName.size();
	}

	//得到某行某列的数据
	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return((Vector)this.rowData.get(row)).get(column);
		
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return(String) this.columnName.get(column);
	}
}
