package participationSystem.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import participationSystem.model.Comment;
import participationSystem.persistence.CommentDao;
import persistence.Database;
import persistence.Persistence;

public class CommentDaoImpl implements CommentDao{
	
	//Queries
	private static String ADD_COMMENT = "INSERT INTO comments (content, votes, date, user_id, proposal_id) VALUES (?, ?, ?, ?, ?)";
	private static String SEARCH_COMMENT_BY_ID = "SELECT * FROM comments WHERE ID=?";
	private static String ALL_COMMENTS_PROPOSAL = "SELECT * FROM comments WHERE PROPOSAL_ID=?";
	private static String CHRONOLOGICAL_ORDER = "SELECT * FROM comments WHERE PROPOSAL_ID=? ORDER BY date DESC";
	private static String POPULARITY_ORDER = "SELECT * FROM comments WHERE PROPOSAL_ID=? ORDER BY VOTES DESC";
	private static String UPDATE_COMMENT = "UPDATE comments SET CONTENT = ?, " + "VOTES = ?, date = ? WHERE ID = ?";

	@Override
	public void addComment(Comment comment) throws Exception {
		
		PreparedStatement pst = null;
		Connection con = null;
		
		try {
			con = Database.getConnection();
			if (Persistence.getAllowWordsDao().checkWords(comment.getComment())) {
				pst = con.prepareStatement(ADD_COMMENT);
				pst.setString(1, comment.getComment());
				pst.setInt(2, comment.getNumberOfVotes());
				pst.setDate(3, new java.sql.Date(comment.getCommentDate().getTime()));
				pst.setInt(4, comment.getIdUser());
				pst.setInt(5, comment.getIdProposal());

				pst.executeUpdate();
			} else {
				throw new Exception("There are not allowed words");
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
	public void voteComment(Comment comment) {
		int votes = comment.getNumberOfVotes();
		
		comment.setNumberOfVotes(votes +1);
		updateComment(comment);	
	}

	@Override
	public Comment searchCommentById(int id) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con=null;

		try {
			con=Database.getConnection();
			pst = con.prepareStatement(SEARCH_COMMENT_BY_ID);
			pst.setInt(1, id);

			rs = pst.executeQuery();
			rs.next();
			
			int idComment = rs.getInt("id");
			String commentContent = rs.getString("comment");
			int numberOfVotes = rs.getInt("numberOfVotes");
			Date commentDate = rs.getDate("commentDate");
			int idUser = rs.getInt("idUser");
			int idProposal = rs.getInt("idProposal");

			Comment comment = new Comment(idComment, commentContent, numberOfVotes, commentDate, idUser, idProposal);

			return comment;

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
	public List<Comment> allCommentsByProposal(int id) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con=null;
		
		List<Comment> comments = new ArrayList<Comment>();
		
		try {
			con=Database.getConnection();
			pst = con.prepareStatement(ALL_COMMENTS_PROPOSAL);
			pst.setInt(1, id);

			rs = pst.executeQuery();
			while (rs.next()) {

				int idComment = rs.getInt("id");
				String commentContent = rs.getString("comment");
				int numberOfVotes = rs.getInt("numberOfVotes");
				Date commentDate = rs.getDate("commentDate");
				int idUser = rs.getInt("idUser");
				int idProposal = rs.getInt("idProposal");

				Comment comment = new Comment(idComment, commentContent, numberOfVotes, commentDate, idUser, idProposal);

				comments.add(comment);
			}
			return comments;

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
	public List<Comment> chronologicalOrderedCommentsByProposal(int id) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con=null;
		
		List<Comment> comments = new ArrayList<Comment>();
		
		try {
			con = Database.getConnection();
			pst = con.prepareStatement(CHRONOLOGICAL_ORDER);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while (rs.next()) {

				int idComment = rs.getInt("id");
				String commentContent = rs.getString("comment");
				int numberOfVotes = rs.getInt("numberOfVotes");
				Date commentDate = rs.getDate("commentDate");
				int idUser = rs.getInt("idUser");
				int idProposal = rs.getInt("idProposal");

				Comment comment = new Comment(idComment, commentContent, numberOfVotes, commentDate, idUser, idProposal);

				comments.add(comment);
			}
			return comments;

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
	public List<Comment> popularityOrderedCommentsByProposal(int id) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con=null;
		
		List<Comment> comments = new ArrayList<Comment>();

		try {
			con=Database.getConnection();
			pst = con.prepareStatement(POPULARITY_ORDER);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {

				int idComment = rs.getInt("id");
				String contentComment = rs.getString("commentContent");
				int numberOfVotes = rs.getInt("numberOfVotes");
				Date commentDate = rs.getDate("commentDate");
				int idUser = rs.getInt("idUser");
				int idProposal = rs.getInt("idProposal");

				Comment comment = new Comment(idComment, contentComment, numberOfVotes, commentDate, idUser, idProposal);

				comments.add(comment);
			}
			return comments;

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
	public void updateComment(Comment comment) {
		
		PreparedStatement pst = null;
		Connection con = null;
		
		try {
			con = Database.getConnection();
			pst = con.prepareStatement(UPDATE_COMMENT);
			pst.setString(1, comment.getComment());
			pst.setInt(2, comment.getNumberOfVotes());
			pst.setDate(3, new java.sql.Date(comment.getCommentDate().getTime()));
			pst.setInt(4, comment.getId());

			pst.executeUpdate();

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

}
