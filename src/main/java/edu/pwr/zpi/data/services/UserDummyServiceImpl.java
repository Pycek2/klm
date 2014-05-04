package edu.pwr.zpi.data.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pwr.zpi.data.dao.UserDao;
import edu.pwr.zpi.data.dao.impl.VerificationResult;
import edu.pwr.zpi.data.entities.User;

//@Service("userDummyService")
public class UserDummyServiceImpl implements UserService {

	@Autowired
	private UserDao userDAO;
	
	@Override
	public User save(User user) {
		return null;
	}
	
	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public User find(long id) {
		return userDAO.find(id);
	}

	@Override
	public void disableUser(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableUser(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VerificationResult verify(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updatePassword(long userId, String newPass) {
		// TODO Auto-generated method stub
		return null;
	}
}
