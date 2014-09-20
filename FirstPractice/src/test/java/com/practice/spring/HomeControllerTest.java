package com.practice.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;

public class HomeControllerTest {

	@Test
	public void forward() throws Exception {
		standaloneSetup(new HomeController()).build()
		.perform(get("/"))
		.andExpect(status().isOk()).andExpect(forwardedUrl("home"));
	}

}