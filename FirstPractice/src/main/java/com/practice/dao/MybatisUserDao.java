package com.practice.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practice.domain.User;

@Repository
public class MybatisUserDao implements UserDao{

	@Autowired
	private SqlSession sqlSession;
	
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