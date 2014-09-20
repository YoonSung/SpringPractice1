package com.practice.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class UserControllerTest {

	MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = standaloneSetup(new UserController()).build(); 
	}
	
	@Test
	public void forward() throws Exception {
		mockMvc.perform(get("/user/form"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/users/form"));
	}
	
	@Test
	public void create() throws Exception {
		mockMvc.perform(post("/user")
				.param("userId", "Yoonsung")
				.param("password", "JungPassword")
				.param("name", "정윤성")
				.param("email", "estrella@nhnnext.org")
		)
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/"));
	}
}