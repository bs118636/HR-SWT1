import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.*;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class CourseOfAction extends JFrame {

	private JPanel contentPane;
	private DefaultStyledDocument doc;
	private JTextArea textArea;
	private JTextArea txtrF;
	private JButton btnSubmit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int a = 0;
					CourseOfAction frame = new CourseOfAction(a);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;
	/**
	 * Create the frame.
	 */
	public CourseOfAction(int fromBrowser) {
	conn = db.dbConnector();	
	final int fromTheBrowser = fromBrowser; //data from the Browser
	
		setTitle("Course of Action");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRecommend = new JLabel("Recommend");
		lblRecommend.setBounds(48, 32, 91, 19);
		contentPane.add(lblRecommend);
		
		
		txtrF = new JTextArea();
		txtrF.setText("Enter Here");
		txtrF.setBounds(48, 67, 354, 114);
		contentPane.add(txtrF);
		
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (fromTheBrowser == 0)
				{
					JOptionPane.showMessageDialog(null,"You did not launch from the Browser");
				}
				try
				{
					if (txtrF.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null,"Necessary Data was not entered.");
					}
					else
					{
						int reviewID = 0;
						String query = "SELECT ReviewID FROM review";
						PreparedStatement ps = conn.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
					
						while(rs.next())
						{
							reviewID = rs.getInt(1) + 1;
						}
						rs.close();
						query = "INSERT INTO review (ReviewID, CaseID, ReviewDetails, HREmployee_EmployeeID) VALUES (?,?,?,?)";
						ps = conn.prepareStatement(query);
						ps.setInt(1, reviewID);
						ps.setInt(2, fromTheBrowser);
						ps.setString(3, txtrF.getText());
						ps.setInt(4, 1);
						ps.execute();
						JOptionPane.showMessageDialog(null,"Data Saved");
						ps.close();
						System.exit(0);
					}
				}
				catch (Exception ee)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			
			}
		});
		btnSubmit.setBounds(295, 192, 107, 35);
		contentPane.add(btnSubmit);
	
		
		
	}		
		
		
	
		
}
		
	

