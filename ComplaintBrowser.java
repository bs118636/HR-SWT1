import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;



public class ComplaintBrowser extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComplaintBrowser frame = new ComplaintBrowser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null; //Connection ------------------------------------------------------------------------------
	private JButton btnNewButton;
	/**
	 * Create the frame.
	 */
	public ComplaintBrowser() {
		conn = db.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 67, 434, 153);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnData = new JButton("Load Data");
		btnData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					String query = "SELECT CaseID, Complaintiff,ComplaintText,Defendant,DateCreated FROM complaint";
					PreparedStatement ps =conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception ee)
				{
					
				}
				
			}
		});
		btnData.setBounds(144, 11, 145, 23);
		contentPane.add(btnData);
		
		btnNewButton = new JButton("Course of Action");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			try
			{
				int selectedRowIndex = table.getSelectedRow(); //number of SelectedRow
				int value = (int) table.getModel().getValueAt(selectedRowIndex, 0);
				System.out.println(value);
				CourseOfAction coa = new CourseOfAction(value);
				coa.setVisible(true);
			}
			catch(Exception ee)
			{
				JOptionPane.showMessageDialog(null, "You did not select a row");
			}
			}
		});
		btnNewButton.setBounds(144, 227, 145, 23);
		contentPane.add(btnNewButton);
	}
}
