package edu.pwr.zpi.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pwr.zpi.data.dao.impl.VerificationResult;
import edu.pwr.zpi.data.entities.LoginPassword;
import edu.pwr.zpi.data.entities.User;
import edu.pwr.zpi.data.services.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	public UserController() {
		logger.info("UserController created");
	}

	@RequestMapping(value="/", method = {RequestMethod.GET, RequestMethod.POST}) 
	public @ResponseBody List<User> users(HttpServletResponse res) {
		logger.info("UserController.users {}", "get all");
		List<User> list = userService.getAllUsers();
		return list;
	}
	
	@RequestMapping(value="/verify", method = {RequestMethod.GET, RequestMethod.POST}) 
	public @ResponseBody VerificationResult verify(@RequestBody LoginPassword lp) {
		logger.info("UserController.verify");
		VerificationResult result = userService.verify(lp.login, lp.password);
		return result;
	}
	
	@RequestMapping(value="/new", method = RequestMethod.GET) 
	public @ResponseBody List<User> newUser(HttpServletResponse res) {
		logger.info("UserController.users {}", "get all");
		
		User u = new User();
		long t = System.currentTimeMillis();
		u.setFirstName("fn_" + t);
		u.setLastName("ln_" + t);
		u.setLogin("login_" + t);
		u.setPassword("pswd_" + t);
		userService.save(u);
		
		
		return users(res);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) 
	public @ResponseBody User userDetail(@PathVariable("id") long userId, HttpServletResponse res) {
		logger.info("UserController.userDetail {}", userId);
		User u = userService.find(userId);
		return u;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.POST, headers = {"Content-type=application/json"}) 
	public @ResponseBody User userDetail(@RequestBody User user, @PathVariable("id") long userId) {
		logger.info("UserController.userDetail {}", userId);
		User u = userService.save(user);
		return u;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE) 
	public @ResponseBody List<User> remove(@PathVariable("id") long userId, HttpServletResponse res) {
		logger.info("UserController.remove {}", userId);
		userService.remove(userId);
		List<User> list = userService.getAllUsers();
		return list;
	}
	
	@RequestMapping(value="/disable/{id}", method = RequestMethod.POST) 
	public @ResponseBody User disable(@PathVariable("id") long userId, HttpServletResponse res) {
		logger.info("UserController.disable {}", userId);
		userService.disableUser(userId);
		return null;
	}
	
	@RequestMapping(value="/enable/{id}", method = RequestMethod.POST) 
	public @ResponseBody User enable(@PathVariable("id") long userId, HttpServletResponse res) {
		logger.info("UserController.disable {}", userId);
		userService.enableUser(userId);
		return null;
	}
	
	@RequestMapping(value="/passwd/{id}", method = RequestMethod.POST, headers = {"Content-type=application/json"}) 
	public @ResponseBody User updatePasswd(@RequestBody String newPass, @PathVariable("id") long userId) {
		logger.info("UserController.userDetail {}", userId);
		User u = userService.updatePassword(userId, newPass);
		return u;
	}

}
