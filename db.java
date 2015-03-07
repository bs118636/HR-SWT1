import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import java.util.Calendar;
import java.util.Date;

public class db {
	Connection conn = null;
	public static Connection dbConnector()
	{
		try
		{
			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhrdb", "root", "admin");
			JOptionPane.showMessageDialog(null, "Connection Successful");
			JOptionPane.showMessageDialog(null, "Today is : " + sdf.format(date));
			return conn;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
