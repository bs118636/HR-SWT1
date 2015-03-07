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

public class SuggestionBrowse extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuggestionBrowse frame = new SuggestionBrowse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	private JTextField textField_1;
	/**
	 * Create the frame.
	 */
	public SuggestionBrowse() {
		conn = db.dbConnector();
	
		setTitle("Suggestion Box");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(33, 38, 66, 14);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(126, 35, 135, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblSuggestion = new JLabel("Suggestion");
		lblSuggestion.setBounds(33, 89, 86, 14);
		contentPane.add(lblSuggestion);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(33, 114, 363, 90);
		contentPane.add(textArea);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String EID = textField_1.getText();
					if (textField.getText().equals("") || textArea.getText().equals("") || EID.equals(""))
					{
						JOptionPane.showMessageDialog(null, "There is necessary data you have not entered.");
					}
					else
					{
						int EmployeeID = Integer.parseInt(EID);
						String query = "SELECT SuggestionID FROM suggestion";
						PreparedStatement ps = conn.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						int suggestionID = 1;
						while(rs.next())
						{
							suggestionID+=1; //increases count by how many rows are received
						}
						rs.close();
						
						query = "INSERT INTO suggestion (SuggestionID,Name,SuggestionText,EmployeeID) VALUES (?,?,?,?)";
						ps = conn.prepareStatement(query);
						ps.setInt(1,suggestionID);
						ps.setString(2, textField.getText());
						ps.setString(3, textArea.getText());
						ps.setInt(4,EmployeeID);
					
						ps.execute();
						JOptionPane.showMessageDialog(null, "Data Saved");
						ps.close();
						System.exit(0);
					}
					
				}
				catch (Exception e)
				{
					System.out.println("Something went wrong");
				}
			}
		});
		btnSubmit.setBounds(310, 227, 89, 23);
		contentPane.add(btnSubmit);
		
		JLabel lblEmployeeid = new JLabel("EmployeeID");
		lblEmployeeid.setBounds(33, 64, 86, 14);
		contentPane.add(lblEmployeeid);
		
		textField_1 = new JTextField();
		textField_1.setBounds(126, 61, 31, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
}
