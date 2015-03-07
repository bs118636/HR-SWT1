import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ComplaintSubmit extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblDepartment;
	private JTextField textField_1;
	private JLabel lblEvidence;
	private JTextField textField_2;
	private JLabel lblType;
	private JLabel lblEnterComplaint;
	private JTextArea txtrEnterHere;
	private JButton btnSubmit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComplaintSubmit frame = new ComplaintSubmit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;
	private JLabel lblEmployeeid;
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * Create the frame.
	 */
	public ComplaintSubmit() {
		conn = db.dbConnector();
		
		setTitle("Submit Your Complaint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 76, 17);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(96, 8, 103, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(10, 42, 76, 14);
		contentPane.add(lblDepartment);
		
		textField_1 = new JTextField();
		textField_1.setBounds(96, 39, 103, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblEvidence = new JLabel("Evidence?");
		lblEvidence.setBounds(255, 12, 67, 14);
		contentPane.add(lblEvidence);
		
		textField_2 = new JTextField();
		textField_2.setBounds(325, 9, 44, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		lblType = new JLabel("Type Y for Yes, N for No.");
		lblType.setBounds(209, 28, 215, 36);
		contentPane.add(lblType);
		
		lblEnterComplaint = new JLabel("Enter Complaint");
		lblEnterComplaint.setBounds(181, 97, 118, 14);
		contentPane.add(lblEnterComplaint);
		
		txtrEnterHere = new JTextArea();
		txtrEnterHere.setText("Enter Here.");
		txtrEnterHere.setLineWrap(true);
		txtrEnterHere.setBounds(31, 122, 379, 78);
		contentPane.add(txtrEnterHere);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try
			{
				Date date = Calendar.getInstance().getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //Date
				
				boolean evidence = false;	//boolean FOR EVIDENCE
				if (textField_2.getText().equals("Y"))
				{
					evidence = true;
				}
				else if (textField_2.getText().equals("N"))
				{
					evidence = false;
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Incorrect Input for Evidence");
					textField_2.setText("");
					evidence = false;
				} //BOOLEAN
				
				if(textField.getText().equals("") || txtrEnterHere.equals("") || textField_1.equals("") || textField.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Necessary Data is Missing");
				}
				else
				{
					String defendant = textField_4.getText();
					if(defendant.equals("")) //makes defendant null in database
					{
						defendant = null;
					}
					
					String query = "SELECT CaseID FROM complaint";
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					int caseID = 1;
					while (rs.next())
					{
						caseID = rs.getInt(1)+1;
					}
					rs.close();
					query = "INSERT INTO complaint (CaseID, Complaintiff, ComplaintText, Defendant, DateCreated, Department, Employee_EmployeeID, Evidence, HREmployee_EmployeeID) VALUES (?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(query);
					ps.setInt(1,caseID); //CaseID
					ps.setString(2, textField.getText()); //Complaintiff
					ps.setString(3, txtrEnterHere.getText()); //ComplaintText
					ps.setString(4, defendant); //Defendant
					ps.setString(5, sdf.format(date)); //DateCreated
					ps.setString(6, textField_1.getText()); //Department
					ps.setString(7, textField_3.getText()); //EmployeeID
					ps.setBoolean(8, evidence); //Evidence
					ps.setInt(9,2); //HR EMployee ID
				
					ps.execute();
					JOptionPane.showMessageDialog(null, "Data Saved");
					ps.close();
					System.exit(0);
					//rs.close();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			} 
		});
		btnSubmit.setBounds(321, 227, 89, 23);
		contentPane.add(btnSubmit);
		
		lblEmployeeid = new JLabel("EmployeeID");
		lblEmployeeid.setBounds(10, 72, 76, 14);
		contentPane.add(lblEmployeeid);
		
		textField_3 = new JTextField();
		textField_3.setBounds(96, 66, 28, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblDefendant = new JLabel("Defendant");
		lblDefendant.setBounds(10, 214, 67, 14);
		contentPane.add(lblDefendant);
		
		
		textField_4 = new JTextField();
		textField_4.setBounds(96, 211, 103, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblIfKnown = new JLabel("if known");
		lblIfKnown.setBounds(10, 239, 55, 14);
		contentPane.add(lblIfKnown);
		
	}
}
