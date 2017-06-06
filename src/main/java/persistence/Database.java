package persistence;

import java.sql.*;

public class Database {

	public static Connection getConnection() {
		
		try {
			Connection db = DriverManager.getConnection("jdbc:postgresql:aswJune", "postgres", "postgresql");
			return db;
		} catch (SQLException e) {
			System.err.println("Connection to the database refused: ");
			System.err.println(e);
			return null;
		}
	}
}
