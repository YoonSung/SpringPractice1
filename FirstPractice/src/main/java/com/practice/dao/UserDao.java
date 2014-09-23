package com.practice.dao;

import com.practice.domain.User;

public interface UserDao {

	public abstract User findById(String userId);

	public abstract int create(User user);

	public abstract int update(User user);

}