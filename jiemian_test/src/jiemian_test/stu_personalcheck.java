package jiemian_test;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class stu_personalcheck extends JDialog  implements ActionListener {
	//��Ҫ�Ŀؼ�
    JPanel jp1,jp2;
    JLabel jl1;
    JButton jb1,jb2,jb3,jb4;
    JTable jt;
    JTextField jtf1;
	Vector rowData,columnName;
	JScrollPane jsp=null;
	TabelModule sm;
	String sql,stuname;
	
	public stu_personalcheck(Frame owner,String title,boolean moduel,String stuname) {
		super(owner,title,moduel);
		this.stuname=stuname;
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
	        sql="select * from stus where stuname='"+this.stuname+"'";
            sm=new TabelModule(sql);
         
            jt=new JTable(sm);

			//��ʼ���ɹ����ı���
		    jsp=new JScrollPane(jt);
		    
		    //���ı�����뵽JFrame����
			 this.add(jsp);
			
			 this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		     this.setSize(400,300);
		     this.setVisible(true);
		     this.setLocationRelativeTo(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
