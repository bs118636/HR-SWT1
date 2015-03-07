import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EmployeeMainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeMainMenu frame = new EmployeeMainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmployeeMainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Complaint");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ComplaintSubmit cs = new ComplaintSubmit();
				setVisible(false);
				cs.setVisible(true);
			}
		});
		btnNewButton.setBounds(5, 5, 145, 222);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Suggestion ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuggestionBrowse sb = new SuggestionBrowse();
				setVisible(false);
				sb.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(289, 5, 145, 222);
		contentPane.add(btnNewButton_1);
	}

}
