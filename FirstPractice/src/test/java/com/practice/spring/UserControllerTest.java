package com.practice.spring;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
		mockMvc.perform(post("/user"))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/"));
	}
}