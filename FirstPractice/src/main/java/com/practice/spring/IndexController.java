package com.practice.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String entrance(Model model) {
		System.out.println("request!");
		model.addAttribute("test", System.currentTimeMillis());
		return "index";
	}
}