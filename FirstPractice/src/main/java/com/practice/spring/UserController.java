package com.practice.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/user/form")
	public String form() {
		return "/users/form";
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public String create(User user) {
		log.info("Create User : {}", user);
		
		return "redirect:/";
	}
}
