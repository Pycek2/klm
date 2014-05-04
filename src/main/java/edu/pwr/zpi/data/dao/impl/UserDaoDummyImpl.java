package edu.pwr.zpi.data.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.pwr.zpi.data.dao.UserDao;
import edu.pwr.zpi.data.entities.User;

//@Repository
public class UserDaoDummyImpl implements UserDao {

	private List<User> users = new ArrayList<User>();
	
	public UserDaoDummyImpl() {
		users.add(createUser(1, "admin"));
		users.add(createUser(2, "mama"));
		users.add(createUser(3, "user"));
	}
	
	@Override
	public List<User> getAllUsers() {
		return new ArrayList<User>(users);
	}
	
	private static User createUser(long id, String login) {
		User u = new User();
		
		u.setId(id);
		u.setLogin(login);
		
		return u;
	}

	@Override
	public User find(long id) {
		for (User u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		
		return null;
	}
	
	public User save(User u) {
		if (u.getId() > 0) {
			return updateUser(u);
		} else {
			return addUser(u);
		}
	}

	private User addUser(User u) {
		u.setId(users.size()+1);
		users.add(u);
		return u;
	}

	private User updateUser(User u) {
		User original = users.get(users.size()+1);
		original.setLogin(u.getLogin());	
		return u;
	}

	@Override
	public void remove(long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUserRoles(long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VerificationResult verify(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
