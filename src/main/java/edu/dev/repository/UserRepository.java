package edu.dev.repository;

import edu.dev.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

	//@Query(value = "SELECT id, name, email, password, role FROM user WHERE email=?1", nativeQuery = true)
	List<User> findByEmail(String username);
	
	List<User> findByEmailAndPasswordAndRole(String username, String password, int role);
}
