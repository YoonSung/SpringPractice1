package com.practice.mybatis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import com.practice.spring.User;

public class MybatisTest {
	
	static SqlSession sqlSession;
	private static final Logger log = LoggerFactory.getLogger(MybatisTest.class);
	
	@BeforeClass
	public static void setUp() throws IOException {
		String resource = "mybatis-config-test.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		sqlSession = sqlSessionFactory.openSession();		
	}
	
	@AfterClass
	public static void end() {
		sqlSession.close();
	}
	
	@Test
	public void findById() throws Exception {
		User user = sqlSession.selectOne("UserMapper.findById", "Yoonsung");
		
		assertNotNull(user.getPassword());
		assertNotNull(user.getName());
		assertNotNull(user.getEmail());
		
		log.info("findById by mybatis : {}", user);
	}
	
	@Test
	public void create() throws Exception {
		User createdUser = new User("Yoonsung2", "password", "JungYoonSung", "lvev9925@naver.com");
		int affectedRow = sqlSession.insert("UserMapper.create", createdUser);
		
		assertThat(affectedRow, is(1));
		
		User selectUser = sqlSession.selectOne("UserMapper.findById", "Yoonsung2");
		assertEquals(createdUser, selectUser);
	}
	
}
