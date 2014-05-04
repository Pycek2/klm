package edu.pwr.zpi.data.services;

import java.util.List;

import edu.pwr.zpi.data.dao.impl.VerificationResult;
import edu.pwr.zpi.data.entities.User;

public interface UserService {

	User save(User user);

	List<User> getAllUsers();

	User find(long id);
	
	void disableUser(long id);
	
	void enableUser(long id);

	void remove(long userId);

	VerificationResult verify(String login, String password);

	User updatePassword(long userId, String newPass);

}