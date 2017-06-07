package repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import persistence.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByEmailAndPassword(String email, String password);
}
