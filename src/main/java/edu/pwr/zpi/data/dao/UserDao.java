package edu.pwr.zpi.data.dao;

import java.util.List;

import edu.pwr.zpi.data.dao.impl.VerificationResult;
import edu.pwr.zpi.data.entities.User;

public interface UserDao {

	List<User> getAllUsers();

	User find(long id);
	
	User save(User u);

	void remove(long userId);

	void removeUserRoles(long userId);

	VerificationResult verify(String login, String password);
}
