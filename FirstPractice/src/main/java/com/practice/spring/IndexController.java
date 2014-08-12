package com.practice.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/test")
	public String entrance() {
		System.out.println("test");
		return "index";
	}
}