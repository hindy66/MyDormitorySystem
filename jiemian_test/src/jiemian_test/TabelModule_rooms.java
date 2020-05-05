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
	
	
	
	//���������Լ��������ݿ������Ҫ����Ķ���
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		String jdbcurl="jdbc:mysql://localhost:3306/student-system?useUnicode=true&characterEncoding=utf-8&useSSL=true";
		String username="root";
		String password="";
		
				
		void init(String sql)
		{
			//ͨ���������columnName��rowData���ᱻ�Զ���ӵ�TableModule����
		    if(sql.equals(""))
			{
				sql="select * from room";
			}
			columnName=new Vector();
			columnName.add("����¥");
	 		columnName.add("¥��");
	 		columnName.add("����");
	 		columnName.add("����");
	 		columnName.add("�۸�");
	 		columnName.add("��Ա");
			
	 		
	 		//rowData���Դ�Ŷ�������
	 		rowData=new Vector();
	         
	 		//��������\
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
	 				//���뵽rowData(һ��һ�����ݵ���ӣ���ÿ������¥Ϊһ����)
	 				rowData.add(hang);
	 			}
	 			
	 		}catch(Exception e){
	 			e.printStackTrace();
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
	
	//�õ����й��ж�������
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return this.rowData.size();
		}

		//�õ����й��ж�������
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return this.columnName.size();
		}

		//�õ�ĳ��ĳ�е�����
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
