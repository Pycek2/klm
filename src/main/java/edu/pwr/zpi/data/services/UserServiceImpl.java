package edu.pwr.zpi.data.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.pwr.zpi.data.dao.UserDao;
import edu.pwr.zpi.data.dao.impl.VerificationResult;
import edu.pwr.zpi.data.entities.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User save(User user) {
		return userDao.save(user);		
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public User find(long id) {
		return userDao.find(id);
	}

	//transaction!
	//@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void disableUser(long id) {
		User u = userDao.find(id);
		u.setDisabled(true);
		userDao.save(u);		
	}

	//transaction!
	@Override
	public void enableUser(long id) {
		User u = userDao.find(id);
		u.setDisabled(false);
		userDao.save(u);			
	}

	@Override	
	public void remove(long userId) {
		userDao.removeUserRoles(userId);
		userDao.remove(userId);
	}

	@Override
	public VerificationResult verify(String login, String password) {
		return userDao.verify(login, password);
	}

	@Override
	public User updatePassword(long userId, String newPass) {
		User u = userDao.find(userId);
		u.setPassword(newPass);
		userDao.save(u);
		return u;
	}

}
