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
	//需要的控件
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
	   //中间内容
	   //设置列名
	 		columnName=new Vector();
	 		columnName.add("学号");
	 		columnName.add("名字");
	 		columnName.add("性别");
	 		columnName.add("年龄");
	 		columnName.add("专业");
	 		
	 		//rowData可以存放多行数据
	 		rowData=new Vector();
	        sql="select * from stus where stuname='"+this.stuname+"'";
            sm=new TabelModule(sql);
         
            jt=new JTable(sm);

			//初始化可滚动文本框
		    jsp=new JScrollPane(jt);
		    
		    //将文本框加入到JFrame当中
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
