package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	 private static final String url = "jdbc:mysql://localhost:3306/taxadvisor_db";
	    private static final String user = "root";
	    private static final String password = "1410";  // Use your MySQL passwor
	

		public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(url, user, password);
	}
}
