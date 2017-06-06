package persistence;

public interface UserDao {
	
	void saveUser(User user);
	boolean existUser(User user);
	boolean userWithDifferentData(User givenUser);
	User getUserByEmail(String email);
}
