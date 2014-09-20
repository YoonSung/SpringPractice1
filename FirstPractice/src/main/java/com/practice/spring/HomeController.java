package com.practice.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/")
	public String entrance(Model model) {
		log.info("Logger Test!");
		model.addAttribute("test", System.currentTimeMillis());
		return "home";
	}
}