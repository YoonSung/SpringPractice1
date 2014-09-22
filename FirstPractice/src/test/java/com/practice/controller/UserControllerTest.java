package com.practice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.practice.controller.UserController;
import com.practice.dao.UserDao;
import com.practice.domain.Authentication;
import com.practice.domain.User;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
			.andExpect(model().size(1))
			.andExpect(model().attribute("authentication", new Authentication()))
			.andExpect(forwardedUrl("/users/login"));
	}
	
	
	//Validation Error Check
	@Test
	public void loginWithNoParam() throws Exception {
		mockMvc.perform(post("/user/login"))
			//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("authentication"))
			.andExpect(forwardedUrl("/users/login"));
	}
	
	@Test
	public void loginWithNotExistsUserId() throws Exception {
		mockMvc.perform(post("/user/login")
				.param("userId", "Yoonssung")
				.param("password", "password")
		)
			//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(model().size(2))
			.andExpect(model().attributeExists("authentication"))
			.andExpect(model().attributeExists("errorMessage"))
			.andExpect(model().attribute("errorMessage", "아이디가 존재하지 않습니다."))
			.andExpect(forwardedUrl("/users/login"));
	}
	
	@Test
	public void loginWithMissPassword() throws Exception {
		
		User user = new User("Yoonsung", "Password", "JungYoonSung", "estrella@nhnnext.org");
		when(userDao.findById(user.getUserId())).thenReturn(user);
		
		mockMvc.perform(post("/user/login")
				.param("userId", user.getUserId())
				.param("password", "noExists")
		)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(model().size(2))
			.andExpect(model().attributeExists("authentication"))
			.andExpect(model().attributeExists("errorMessage"))
			.andExpect(model().attribute("errorMessage", "비밀번호가 존재하지 않습니다."))
			.andExpect(forwardedUrl("/users/login"));
	}
	
	@Test
	public void loginWithValidUser() throws Exception {
		
		User user = new User("Yoonsung", "Password", "JungYoonSung", "estrella@nhnnext.org");
		when(userDao.findById(user.getUserId())).thenReturn(user);
		
		mockMvc.perform(post("/user/login")
				.param("userId", user.getUserId())
				.param("password", user.getPassword())
		)
			//.andDo(print())
			.andExpect(status().isMovedTemporarily())
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("authentication"))
			.andExpect(redirectedUrl("/"));
	}
	
	@Test
	public void logout() throws Exception {
		User user = new User("Yoonsung", "Password", "JungYoonSung", "estrella@nhnnext.org");
		when(userDao.findById(user.getUserId())).thenReturn(user);
		
		ResultActions auth =this.mockMvc.perform(post("/user/login")
				.param("userId", user.getUserId())
				.param("password", user.getPassword())
		 );
		
		MvcResult result = auth.andReturn();
		MockHttpSession session = (MockHttpSession)result.getRequest().getSession();
		
		
		ResultActions actions = mockMvc.perform(get("/user/logout").session(session))
			.andExpect(status().isMovedTemporarily());
			//.andExpect(MockMvcResultMatchers.s.sessionAttribute("userId", null));
		
		MockHttpSession session2 = (MockHttpSession)actions.andReturn().getRequest().getSession();
		assertEquals(session2.getAttribute("userId"), null);

	}
}