package persistence;

public interface UserDao {
	
	void saveUser(User user);
	boolean existUser(User user);

}
