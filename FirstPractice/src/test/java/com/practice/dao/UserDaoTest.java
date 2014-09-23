package com.practice.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.practice.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
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
		
		String userId = "YoonSung10";
		
		User createdUser = new User(userId, "PassPass", "정윤성", "estrella@nhnnext.org");
		int affectedRowNum = userDao.create(createdUser);
		assertEquals(affectedRowNum, 1);
		
		User userFromDatabase = userDao.findById(userId);
		assertEquals(createdUser, userFromDatabase);
	}
}
