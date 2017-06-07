package participationSystem.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import participationSystem.persistence.AllowWordsDao;
import persistence.Database;

public class AllowWordsDaoImpl implements AllowWordsDao{
	
	//Queries
	private String ADD_WORDS = "INSERT INTO PUBLIC.WORD (WORD) VALUES (?)";
	private String FIND_ALL_WORDS = "SELECT * FROM PUBLIC.WORDS";

	@Override
	public void add(List<String> words) {
		
		PreparedStatement pst = null;
		Connection con = null;
		
		try {
			con=Database.getConnection();
			pst=con.prepareStatement(ADD_WORDS);

			for (String word : words) {
				pst.setString(1, word);
				pst.executeUpdate();
			}

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public List<String> findAllWords() {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con=null;
		
		ArrayList<String> words = new ArrayList<String>();
		
		try {
			con=Database.getConnection();
			pst=con.prepareStatement(FIND_ALL_WORDS);

			rs = pst.executeQuery();
			
			while (rs.next()) {
				words.add(rs.getString("word"));
			}

			return words;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				rs.close();
				pst.close();
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	@Override
	public boolean checkWords(String comment) {
		
		List<String> allWords = findAllWords();
		
		for(String word : allWords) {
			if(comment.contains(word))
				return false;
		}
		return true;
	}
}
