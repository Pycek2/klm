package edu.pwr.zpi.web.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String loginSuccess(Model model, Principal principal ) {
 
		String login = principal.getName();
		logger.info("logged as {} ", login);
		
		model.addAttribute("username", login);
		model.addAttribute("message", "Spring Security Custom Form example");
		return "home";
 
	}
 
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Model model) {
 
		return "login";
 
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(Model model) {
 
		model.addAttribute("error", "true");
		return "login";
 
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(Model model) {
 
		return "login";
 	}

}
