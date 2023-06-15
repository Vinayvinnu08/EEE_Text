package Sending_Receving_data;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Display_data {

	private JFrame frame;
	private JTextField tb1;
	private JTextField tb2;
	private JTable table;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display_data window = new Display_data();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Display_data() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(64, 128, 128));
		frame.setBounds(100, 100, 833, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(47, 58, 64, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblMarks = new JLabel("Marks");
		lblMarks.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMarks.setBounds(47, 110, 64, 31);
		frame.getContentPane().add(lblMarks);
		
		tb1 = new JTextField();
		tb1.setBounds(154, 63, 115, 24);
		frame.getContentPane().add(tb1);
		tb1.setColumns(10);
		
		tb2 = new JTextField();
		tb2.setBounds(154, 115, 115, 24);
		frame.getContentPane().add(tb2);
		tb2.setColumns(10);
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
			   String n=tb1.getText();
			   String mr=tb2.getText();
			   int m=Integer.parseInt(mr);
			   try {
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EEE","root","mrec");
				String q="insert into mrec values('"+n+"','"+m+"')";
				Statement sta=con.createStatement();
				sta.execute(q);
				con.close();
				JOptionPane.showMessageDialog(btnNewButton,"Done"); 
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			   
			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.BOLD, 15));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(144, 209, 108, 31);
		frame.getContentPane().add(btnNewButton);
		
		table = new JTable();
		table.setBounds(377, 58, 379, 182);
		frame.getContentPane().add(table);
		
		btnNewButton_1 = new JButton("CLEAR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
			}
		});
		btnNewButton_1.setFont(new Font("Verdana", Font.BOLD, 15));
		btnNewButton_1.setBounds(610, 262, 96, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("LOAD");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EEE","root","mrec");
					Statement sta=con.createStatement();
					String q="select * from mrec";
					ResultSet rs=sta.executeQuery(q);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) table.getModel();
					int cols=rsmd.getColumnCount();
					String[] colname=new String[cols];
					for(int i=0;i<cols;i++)
					{
						colname[i]=rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colname);
						String n1,m1;
						while(rs.next())
						{
							n1=rs.getString(1);
							m1=rs.getString(2);
							String[] row= {n1,m1};
							model.addRow(row);
						}
						sta.close();
						con.close();
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Verdana", Font.BOLD, 15));
		btnNewButton_2.setBounds(395, 262, 96, 31);
		frame.getContentPane().add(btnNewButton_2);
	}
}
