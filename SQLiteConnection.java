import java.sql.*;
import javax.swing.*;

public class SQLiteConnection {
	public static Connection conn = null;
	public SQLiteConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			//Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\seren\\eclipse-workspace\\GardeningClubProject\\Gardener.db");
			//Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\seren\\eclipse-workspace\\GardeningClubProject\\src\\GardenerDB.db");
			//Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\seren\\eclipse-workspace\\GardeningClubProject\\GardenerDB.db");
			conn = DriverManager.getConnection("jdbc:sqlite:database\\GardenerDB.db");
			//JOptionPane.showMessageDialog(null, "Database Connected"); // tests to see if database is connected
			//return conn;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e); // displays error message
			System.out.println(e);
		}
	}
	public static Connection getConnection() {
		return conn;
	}
}
