import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.sql.*;

import javax.swing.JPasswordField;



public class HRMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HRMenu frame = new HRMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*BEGINNING of SQL CODE
	 */
	Connection connection = null; //Connection -------------------------------------------------------------------------
	private JPasswordField passwordField_1;
	

	
	/**
	 * Create the frame.
	 */
	public HRMenu() {
		setTitle("Login");
		connection=db.dbConnector(); //Method in db class ---------------------------------------------------------------
		/*END of SQL CODE
		 */
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(178, 41, 116, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(85, 111, 62, 14);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(85, 50, 62, 14);
		contentPane.add(lblUsername);
		
		JButton btnSubmit = new JButton("Login");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String query = "SELECT * FROM admin WHERE Username=? AND Password=?";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1,textField.getText());
					ps.setString(2,passwordField_1.getText()); //deprecated but works
					ResultSet rs = ps.executeQuery();
					int count = 0;
					
					while(rs.next())
					{
						count+=1;
					}
					
					if (count == 1)
					{
						JOptionPane.showMessageDialog(null, "Username & Password is Correct!");
						
						HRMainMenu mainmenu = new HRMainMenu();
						setVisible(false);
						mainmenu.setVisible(true);
						
						
					}
					else if (count > 1)
					{
						JOptionPane.showMessageDialog(null, "Duplicate Username & Password");
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Username & Password is Incorrect! Try Again");
						textField.setText("");
						passwordField_1.setText("");
					}
					rs.close();
					ps.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnSubmit.setBounds(249, 197, 89, 23);
		contentPane.add(btnSubmit);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(178, 105, 116, 26);
		contentPane.add(passwordField_1);
	}
}
