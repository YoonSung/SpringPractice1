package com.practice.dao;

import org.apache.ibatis.session.SqlSession;

public class MybatisUserDao {
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
}