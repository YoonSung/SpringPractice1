package com.practice.spring;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping("/user/form")
	public String form(Model model) {
		model.addAttribute("user", new User());
		return "/users/form";
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public String create(@Valid User user, BindingResult bindingResult) {
		
		if (bindingResult.getErrorCount() != 0) {
			for (ObjectError error : bindingResult.getAllErrors()) {
				log.error("Register User Error, field : {}, error : {}", error.getArguments() , error.getDefaultMessage());
			}
			return "/users/form";
		}
		
		log.info("Create User : {}", user);
		
		//TODO Validation Check
		//int affectedRow = userDao.create(user);
		
		userDao.create(user);
		
		//TODO create Result Check, 
		
		return "redirect:/";
	}
	
	@RequestMapping("/user/login/form")
	public String loginForm(Model model) {
		model.addAttribute("authentication", new Authentication());
		return "/users/login";
	}
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	public String loginForm(@Valid Authentication authentication, BindingResult bidingResult, Model model, HttpSession session) {

		if (bidingResult.getErrorCount() > 0) {
			for (ObjectError error: bidingResult.getAllErrors()) {
				log.error("Validation Error : {}", error.getDefaultMessage());
			}
			return "/users/login";
		}
		
		User user = userDao.findById(authentication.getUserId());
		
		if (user == null) {
			model.addAttribute("errorMessage", "아이디가 존재하지 않습니다.");
			return "/users/login";
		}
		
		if (!user.isSamePassword(authentication)) {
			model.addAttribute("errorMessage", "비밀번호가 존재하지 않습니다.");
			return "/users/login";
		}
		
		session.setAttribute("userId", user.getUserId());
		return "redirect:/";
	}
	
	@RequestMapping("/user/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userId");
		return "redirect:/";
	}
}
