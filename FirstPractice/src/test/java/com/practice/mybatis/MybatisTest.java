package com.practice.mybatis;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.practice.spring.User;

public class MybatisTest {

	@Test
	public void gettingStart() throws IOException {
		String resource = "mybatis-config-test.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		User user = session.selectOne("UserMapper.findById", new User("Yoonsung", null, null, null));
		
		assertNotNull(user.getPassword());
		assertNotNull(user.getName());
		assertNotNull(user.getEmail());
		
		session.close();
	}

}
