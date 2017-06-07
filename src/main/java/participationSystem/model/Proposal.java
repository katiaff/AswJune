package participationSystem.model;

public class Proposal {
	
	private int id;
	
	private String proposalContent;
	private int numberOfVotes;

	private Category category;
	
	private int idCategory;
	private int idUser;
	
	public Proposal()
	{
		
	}
	
	public Proposal(int id, String proposalContent, int numberOfVotes, Category category, int idCategory, int idUser) {
		super();
		this.id = id;
		this.proposalContent = proposalContent;
		this.numberOfVotes = numberOfVotes;
		this.category = category;
		this.idCategory = idCategory;
		this.idUser = idUser;
	}

	public Proposal(String proposalContent, int numberOfVotes, int idCategory, int idUser) {
		this.proposalContent = proposalContent;
		this.numberOfVotes = numberOfVotes;
		this.idCategory = idCategory;
		this.idUser = idUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProposalContent() {
		return proposalContent;
	}

	public void setProposalContent(String proposalContent) {
		this.proposalContent = proposalContent;
	}

	public int getNumberOfVotes() {
		return numberOfVotes;
	}

	public void setNumberOfVotes(int numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
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
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + id;
		result = prime * result + idCategory;
		result = prime * result + idUser;
		result = prime * result + numberOfVotes;
		result = prime * result + ((proposalContent == null) ? 0 : proposalContent.hashCode());
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
		Proposal other = (Proposal) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id != other.id)
			return false;
		if (idCategory != other.idCategory)
			return false;
		if (idUser != other.idUser)
			return false;
		if (numberOfVotes != other.numberOfVotes)
			return false;
		if (proposalContent == null) {
			if (other.proposalContent != null)
				return false;
		} else if (!proposalContent.equals(other.proposalContent))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Proposal [id=" + id + ", proposalContent=" + proposalContent + ", numberOfVotes=" + numberOfVotes
				+ ", category=" + category + ", idCategory=" + idCategory + ", idUser=" + idUser + "]";
	}
}
