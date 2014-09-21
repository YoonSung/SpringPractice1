package com.practice.spring;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {
	
	private static final Logger log = LoggerFactory.getLogger(UserTest.class);
	
	static Validator validator;
	Set<ConstraintViolation<User>> constraintViolations;
	
	@BeforeClass
	public static void init() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	@After
	public void printErrors() {
		for (ConstraintViolation<User> constraintViolation : constraintViolations) {
			log.info("violation : {}", constraintViolation.getMessage());
		}
	}
	
	@Test
	public void emptyId() throws Exception {
		constraintViolations =  validator.validate(new User("", "password", "namename", "lvev9925@naver.com"));
		assertThat(constraintViolations.size(), is(2));
	}
	
	@Test
	public void emptyPassword() throws Exception {
		constraintViolations =  validator.validate(new User("userId", "", "namename", "lvev9925@naver.com"));
		assertThat(constraintViolations.size(), is(2));
	}
	
	@Test
	public void emptyName() throws Exception {
		constraintViolations =  validator.validate(new User("userId", "password", "", "lvev9925@naver.com"));
		assertThat(constraintViolations.size(), is(1));
	}
	
	@Test
	public void emptyEmail() throws Exception {
		constraintViolations =  validator.validate(new User("userId", "password", "username", ""));
		assertThat(constraintViolations.size(), is(1));
	}
	
	@Test
	public void invalidEmailFormat() throws Exception {
		constraintViolations =  validator.validate(new User("userId", "password", "username", "lvev9925naver.com"));
		assertThat(constraintViolations.size(), is(1));
	}
}
