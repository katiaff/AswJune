package participationSystem.persistence;

import java.util.List;

import participationSystem.model.Comment;

public interface CommentDao {
	
	void addComment(Comment comment) throws Exception;
	void updateComment(Comment comment);
	void voteComment(Comment comment);
	Comment searchCommentById(int id);
	List<Comment> allCommentsByProposal(int id);
	List<Comment> chronologicalOrderedCommentsByProposal(int idProposal);
	List<Comment> popularityOrderedCommentsByProposal(int idProposal);

}
