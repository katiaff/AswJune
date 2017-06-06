package persistence.impl;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.Database;
import persistence.User;
import persistence.UserDao;

public class UserDaoImpl implements UserDao{
	
	//Queries
	private static String SAVE_USER = "INSERT INTO PUBLIC.USERS (dni, firstName, lastName, "
			+ "password, email, birthDate, address, nationality, pollingStation) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String CHECK_IF_EXIST_USER = "SELECT * FROM PUBLIC.USERS WHERE DNI=?";
	
	
	public UserDaoImpl()
	{
		
	}

	public void saveUser(User user) {
		
		PreparedStatement pst = null;
		Connection con=null;
		
		if(existUser(user)) {
			System.out.println("User already exists: " + user.toString());
			File log = new File("Logs/log.txt");
			try {
				
				BufferedWriter bw = new BufferedWriter(new FileWriter(log));
				bw.append("User already exists: " + user.getFirstName() + " " + user.getLastName() + " " + user.getDni() + "\n");
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		try {
			con = Database.getConnection();
			pst = con.prepareStatement(SAVE_USER);
			pst.setString(1, user.getDni());
			pst.setString(2, user.getFirstName());
			pst.setString(3, user.getLastName());
			pst.setString(4, user.getPassword());
			pst.setString(5, user.getEmail());
			pst.setDate(6, new java.sql.Date(user.getDateOfBirth().getTime()));
			pst.setString(7, user.getAddress());
			pst.setString(8, user.getNationality());
			pst.setInt(9, user.getPollingStation());

			pst.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	public boolean existUser(User user)
	{
		PreparedStatement pst = null;
		Connection con=null;
		
		try {
			con = Database.getConnection();
			pst = con.prepareStatement(CHECK_IF_EXIST_USER);
			pst.setString(1, user.getDni());
			ResultSet rs = pst.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


}
