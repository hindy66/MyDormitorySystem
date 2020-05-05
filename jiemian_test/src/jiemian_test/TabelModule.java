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
	
	
	
	//���������Լ��������ݿ������Ҫ����Ķ���
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
 		columnName.add("ѧ��");
 		columnName.add("����");
 		columnName.add("�Ա�");
 		columnName.add("����");
 		columnName.add("רҵ");
 		
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
 				hang.add(rs.getInt(4));
 				hang.add(rs.getString(5));
 				//���뵽rowData(һ��һ�����ݵ���ӣ���ÿ��ѧ��Ϊһ����)
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
	
	//ͨ��sql�������ȡ����
	public TabelModule(String sql)
	{
		this.init(sql);
	}
		
		
   //���췽�������ڳ�ʼ���õ��ı�
	public  TabelModule()
	{
		this.init("");
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
