package persistence;

import participationSystem.persistence.AllowWordsDao;
import participationSystem.persistence.CategoryDao;
import participationSystem.persistence.CommentDao;
import participationSystem.persistence.ProposalDao;
import participationSystem.persistence.impl.AllowWordsDaoImpl;
import participationSystem.persistence.impl.CategoryDaoImpl;
import participationSystem.persistence.impl.CommentDaoImpl;
import participationSystem.persistence.impl.ProposalDaoImpl;
import persistence.impl.UserDaoImpl;

public class Persistence {

	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}
	
	public static ProposalDao getProposalDao() {
		return new ProposalDaoImpl();
	}

	public static CommentDao getCommentaryDao() {
		return new CommentDaoImpl();
	}
	
	public static CategoryDao getCategoryDao() {
		return new CategoryDaoImpl();
	}
	
	public static AllowWordsDao getAllowWordsDao() {
		return new AllowWordsDaoImpl();
	}
}
