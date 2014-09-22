package com.practice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;

import com.practice.controller.HomeController;

public class HomeControllerTest {

	@Test
	public void forward() throws Exception {
		standaloneSetup(new HomeController()).build()
		.perform(get("/"))
		.andExpect(status().isOk()).andExpect(forwardedUrl("home"));
	}

}