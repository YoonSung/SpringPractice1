package com.practice.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.practice.domain.User;

public class MybatisUserDao implements UserDao{

	private static final Logger log = LoggerFactory.getLogger(JdbcUserDao.class);
	
	//TODO DELETE
	//TODO SET AUTOWIRED
	private DataSource dataSource;
	private SqlSession sqlSession;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	@PostConstruct
	public void init() {
		
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("user.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		
		log.info("Database Initialize Success!!");
	}
	
	@Override
	public User findById(String userId) {
		return sqlSession.selectOne("UserMapper.findById", userId);
	}

	@Override
	public int create(User user) {
		return sqlSession.insert("UserMapper.create", user);
	}

	@Override
	public int update(User user) {
		return sqlSession.update("UserMapper.update", user);
	}
}