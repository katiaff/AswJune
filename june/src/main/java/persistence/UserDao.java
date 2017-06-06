package persistence;

public interface UserDao {
	
	void save(User user);
	boolean exists(User user);

}
