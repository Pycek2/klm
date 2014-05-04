package edu.pwr.zpi.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public HomeController() {
		logger.info("HomeController created");
	}
	
	@RequestMapping({"/", "/home"})
	public String homePage(ModelMap model) {
		model.addAttribute("message", "spring rulez");
		return "home";
	}
	
}
