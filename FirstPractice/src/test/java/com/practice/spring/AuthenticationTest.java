package com.practice.spring;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

public class AuthenticationTest {
	
	static Validator validator;
	Set<ConstraintViolation<Authentication>> constraintViolations;
	private static final Logger log = LoggerFactory.getLogger(AuthenticationTest.class);
	
	@BeforeClass
	public static void init() {
		ValidatorFactory validatorFactory  = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	@After
	public void print() {
		for (ConstraintViolation<Authentication> constraintViolation : constraintViolations) {
			log.info("field : {},     errorMessage : {}", constraintViolation.getPropertyPath(), constraintViolation.getMessage());
		}
	}
	
	@Test
	public void emptyId() {
		constraintViolations = validator.validate(new Authentication("", "password"));
		assertThat(constraintViolations.size(), is(2));
	}
	
	@Test
	public void userIdWithInvalidSize() {
		constraintViolations = validator.validate(new Authentication("a", "password"));
		assertThat(constraintViolations.size(), is(1));
		
		constraintViolations = validator.validate(new Authentication("ab", "password"));
		assertThat(constraintViolations.size(), is(1));
		
		constraintViolations = validator.validate(new Authentication("abc", "password"));
		assertThat(constraintViolations.size(), is(1));
		
		constraintViolations = validator.validate(new Authentication("abcd", "password"));
		assertThat(constraintViolations.size(), is(0));
	}
	
	@Test
	public void emptyPassword() {
		constraintViolations = validator.validate(new Authentication("ididid", ""));
		assertThat(constraintViolations.size(), is(2));
	}
	
	@Test
	public void passwordWithInvalidSize() {
		constraintViolations = validator.validate(new Authentication("ididid", "a"));
		assertThat(constraintViolations.size(), is(1));
		
		constraintViolations = validator.validate(new Authentication("ididid", "ab"));
		assertThat(constraintViolations.size(), is(1));
		
		constraintViolations = validator.validate(new Authentication("ididid", "abc"));
		assertThat(constraintViolations.size(), is(1));
		
		constraintViolations = validator.validate(new Authentication("ididid", "abc†d"));
		assertThat(constraintViolations.size(), is(0));
	}

}
