package com.practice.spring;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	MockMvc mockMvc;
	
	@Mock
	UserDao userDao;
	
	@InjectMocks
	UserController userController;
	
	@Before
	public void setUp() {
		mockMvc = standaloneSetup(userController).build(); 
	}
	
	@Test
	public void joinForm() throws Exception {
		mockMvc.perform(get("/user/form"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/users/form"));
	}
	
	@Test
	public void join() throws Exception {
		mockMvc.perform(post("/user")
				.param("userId", "Yoonsung")
				.param("password", "JungPassword")
				.param("name", "정윤성")
				.param("email", "estrella@nhnnext.org")
		)
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/"));
	}
	
	@Test
	public void loginForm() throws Exception {
		mockMvc.perform(get("/user/login/form"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/users/login"));
	}
	
	@Test
	public void login() throws Exception {
		mockMvc.perform(post("/user/login"))
			.andDo(print())
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("errorMessage"))
			.andExpect(redirectedUrl("/"));
	}
}