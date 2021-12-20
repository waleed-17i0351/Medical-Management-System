package databaseFiles;

import java.sql.*;

public class connectDB {
	
	public void load() {
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver loaded successfully!");
		Connection con = DriverManager.getConnection(
		"jdbc:oracle:thin:@localhost:1521/xe", "SYSTEM", "12346"		
		);
		System.out.println("Connection Established!");
		} catch(ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
