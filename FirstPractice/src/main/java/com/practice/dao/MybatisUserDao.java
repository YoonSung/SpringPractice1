package com.practice.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.practice.domain.User;

public class MybatisUserDao implements UserDao{

	private static final Logger log = LoggerFactory.getLogger(MybatisUserDao.class);
	
	//TODO SET AUTOWIRED
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
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