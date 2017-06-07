package participationSystem.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import participationSystem.model.Proposal;
import participationSystem.persistence.ProposalDao;
import persistence.Database;
import persistence.Persistence;

public class ProposalDaoImpl implements ProposalDao{
	
	//Queries
	private static String ADD_PROPOSAL = "INSERT INTO proposal(proposalContent, numberOfVotes, idUser, idCategory) VALUES (?,?,?,?)";
	private static String DELETE_COMMENTS_TO_DELETE_PROPOSAL =  "DELETE FROM comment WHERE idProposal=?";
	private static String DELETE_PROPOSAL =  "DELETE FROM proposal WHERE id=?";
	private static String UPDATE_PROPOSAL = "UPDATE proposal SET proposalContent = ?, numberOfVotes = ?, idCategory = ? WHERE id= ?";
	private static String ALL_PROPOSALS = "SELECT * FROM proposal";
	private static String PROPOSAL_BY_ID = "SELECT * FROM proposal WHERE id=?";

	@Override
	public void addProposal(Proposal proposal) throws Exception {
		
		PreparedStatement pst = null;
		Connection con = null;
		
		try {
			con = Database.getConnection();

			if (Persistence.getAllowWordsDao().checkWords(proposal.getProposalContent())){

				pst = con.prepareStatement(ADD_PROPOSAL);
				pst.setString(1, proposal.getProposalContent());
				pst.setInt(2, proposal.getNumberOfVotes());
				pst.setInt(3, proposal.getIdUser());
				pst.setInt(4, proposal.getIdCategory());

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
	public void deleteProposal(int id) {
		PreparedStatement pst = null;
		Connection con = null;
		
		try {
			con = Database.getConnection();
			pst = con.prepareStatement(DELETE_COMMENTS_TO_DELETE_PROPOSAL);
			pst.setInt(1, id);
			pst.executeUpdate();
			pst.close();

			pst = con.prepareStatement(DELETE_PROPOSAL);
			pst.setInt(1, id);
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

	@Override
	public void updateProposal(Proposal proposal) {
		
		PreparedStatement pst = null;
		Connection con=null;
		
		try {
			con = Database.getConnection();
			pst = con.prepareStatement(UPDATE_PROPOSAL);
			pst.setString(1, proposal.getProposalContent());
			pst.setInt(2, proposal.getNumberOfVotes());
			pst.setInt(3, proposal.getIdCategory());
			pst.setInt(4, proposal.getId());

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

	@Override
	public List<Proposal> getAllProposals() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Proposal> proposals = new ArrayList<Proposal>();
		
		try {
			con = Database.getConnection();
			pst = con.prepareStatement(ALL_PROPOSALS);

			rs = pst.executeQuery();
			while (rs.next()) {

				int id = rs.getInt("id");
				String proposalContent = rs.getString("proposalContent");
				int numberOfVotes = rs.getInt("numberOfVotes");
				int idCategory = rs.getInt("idCategory");
				int idUser = rs.getInt("idUser");

				Proposal proposal = new Proposal(proposalContent, numberOfVotes, idCategory, idUser);
				proposal.setId(id);

				proposals.add(proposal);
			}

			return proposals;

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
	public Proposal searchProposalById(int id) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con=null;
		
		try {
			con=Database.getConnection();
			pst = con.prepareStatement(PROPOSAL_BY_ID);
			pst.setInt(1, id);

			rs = pst.executeQuery();
			while (rs.next()) {

				int idProposal = rs.getInt("id");
				String proposalContent = rs.getString("proposalContent");
				int numberOfVotes = rs.getInt("numberOfVotes");
				int idCategory = rs.getInt("idCategory");
				int idUser = rs.getInt("idUser");

				Proposal proposal = new Proposal();
				proposal.setNumberOfVotes(numberOfVotes);
				proposal.setIdUser(idUser);
				proposal.setProposalContent(proposalContent);
				proposal.setIdCategory(idCategory);
				proposal.setId(idProposal);

				return proposal;
			}
			
			return null;

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
	public void voteProposal(Proposal proposal) {
		int votes = proposal.getNumberOfVotes();
		
		proposal.setNumberOfVotes(votes +1);
		updateProposal(proposal);
	}
}
