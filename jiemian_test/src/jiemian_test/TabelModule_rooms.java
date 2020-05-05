package jiemian_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class TabelModule_rooms extends AbstractTableModel {
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
			//通过这个方法columnName和rowData将会被自动添加到TableModule当中
		    if(sql.equals(""))
			{
				sql="select * from room";
			}
			columnName=new Vector();
			columnName.add("宿舍楼");
	 		columnName.add("楼层");
	 		columnName.add("房号");
	 		columnName.add("床数");
	 		columnName.add("价格");
	 		columnName.add("成员");
			
	 		
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
	 				hang.add(rs.getString(4));
	 				hang.add(rs.getString(5));
	 				hang.add(rs.getString(6));
	 				//加入到rowData(一行一行数据的添加，即每个宿舍楼为一个组)
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
			

		
		
		
		public TabelModule_rooms()
		{
			this.init("");
		}
		
		public TabelModule_rooms(String sql)
		{
			this.init(sql);
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
