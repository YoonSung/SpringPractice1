package com.practice.spring;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class UserDaoTest {

	@Autowired
	UserDao userDao;
	
	@Test
	public void findByIdWhenExistsId() {
		User user = userDao.findById("Yoonsung");
		assertEquals(user.getUserId(), "Yoonsung");
	}
	
	@Test
	public void findByIdWhenNotExistsId() {
		User user = userDao.findById("Yoonssung");
		assertNull(user);
	}
	
	@Test
	public void create() throws Exception {
		
		String userId = "JungYoonSung";
		
		User createdUser = new User(userId, "PassPass", "정윤성", "estrella@nhnnext.org");
		int affectedRowNum = userDao.create(createdUser);
		assertEquals(affectedRowNum, 1);
		
		User userFromDatabase = userDao.findById(userId);
		assertEquals(createdUser, userFromDatabase);
	}
}
