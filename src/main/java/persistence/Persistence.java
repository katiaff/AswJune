package persistence;

import persistence.impl.UserDaoImpl;

public class Persistence {

	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}
	
}
