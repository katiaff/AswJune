package participationSystem.persistence;

import java.util.List;

import participationSystem.model.Proposal;

public interface ProposalDao {
	
	void addProposal(Proposal proposal) throws Exception;
	void deleteProposal(int id);
	void updateProposal(Proposal proposal);
	List<Proposal> getAllProposals();
	Proposal searchProposalById(int id);
	void voteProposal(Proposal proposal);
}
