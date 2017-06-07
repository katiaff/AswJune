package persistence.impl;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import persistence.Database;
import persistence.User;
import persistence.UserDao;

public class UserDaoImpl implements UserDao{
	
	//Queries
	private static String SAVE_USER = "INSERT INTO PUBLIC.User(dni, firstName, lastName, password, email, birthdate, address, nationality, pollingStation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String CHECK_IF_EXIST_USER = "SELECT * FROM USER WHERE DNI=?";
	private static String USER_BY_EMAIL = "SELECT * FROM PUBLIC.user WHERE EMAIL= ?";
	

	public void saveUser(User user) {
		
		PreparedStatement pst = null;
		Connection con=null;
		
		if(existUser(user) || userWithDifferentData(user))
		{
			File log = new File("Logs/log.txt");
			
			if(existUser(user)) {
				System.out.println("User already exists: " + user.toString());
				try {
					
					BufferedWriter bw = new BufferedWriter(new FileWriter(log));
					bw.append("User already exists: " + user.getFirstName() + " " + user.getLastName() 
						+ " " + user.getDni() + "\n");
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			
			if(userWithDifferentData(user)){
				System.out.println("User has different data: " + user.toString());
				try {
					
					BufferedWriter bw = new BufferedWriter(new FileWriter(log));
					bw.append("User already exists with different data: " + user.getFirstName() + " " + user.getLastName() 
						+ " " + user.getDni() + "\n");
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
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

	@Override
	public boolean userWithDifferentData(User givenUser) 
	{
		User searchedUser = getUserByEmail(givenUser.getEmail());
		return givenUser.equals(searchedUser);
	}

	@Override
	public User getUserByEmail(String email) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con =null;
		
		try {
			con = Database.getConnection();
			pst = con.prepareStatement(USER_BY_EMAIL);
			pst.setString(1, email);

			rs = pst.executeQuery();
			if (rs.next()) {

				Integer idBase = rs.getInt("id");
				String dni = rs.getString("dni");
				String name = rs.getString("firstName");
				String surname = rs.getString("lastName");
				Date birth = rs.getDate("birthDate");
				String nationality = rs.getString("nationality");
				String address = rs.getString("address");
				int polling = rs.getInt("pollingStation");
				String pass = rs.getString("password");

				User user = new User(dni, name, surname, birth, address, email, nationality, polling);
				user.setId(idBase);
				user.setPassword(pass);

				return user;
			}
			return null;

		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
}
