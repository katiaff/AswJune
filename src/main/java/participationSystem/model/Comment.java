package participationSystem.model;

import java.util.Date;

public class Comment {
	
	private int id;
	
	private String comment;
	private int numberOfVotes;
	private Date commentDate;
	
	
	private int idProposal;
	private int idUser;
	
	public Comment(int id, String comment, int numberOfVotes, Date commentDate, int idProposal, int idUser) {
		super();
		this.id = id;
		this.comment = comment;
		this.numberOfVotes = numberOfVotes;
		this.commentDate = commentDate;
		this.idProposal = idProposal;
		this.idUser = idUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getNumberOfVotes() {
		return numberOfVotes;
	}

	public void setNumberOfVotes(int numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public int getIdProposal() {
		return idProposal;
	}

	public void setIdProposal(int idProposal) {
		this.idProposal = idProposal;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((commentDate == null) ? 0 : commentDate.hashCode());
		result = prime * result + id;
		result = prime * result + idProposal;
		result = prime * result + idUser;
		result = prime * result + numberOfVotes;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (commentDate == null) {
			if (other.commentDate != null)
				return false;
		} else if (!commentDate.equals(other.commentDate))
			return false;
		if (id != other.id)
			return false;
		if (idProposal != other.idProposal)
			return false;
		if (idUser != other.idUser)
			return false;
		if (numberOfVotes != other.numberOfVotes)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", numberOfVotes=" + numberOfVotes + ", commentDate="
				+ commentDate + ", idProposal=" + idProposal + ", idUser=" + idUser + "]";
	}
}
