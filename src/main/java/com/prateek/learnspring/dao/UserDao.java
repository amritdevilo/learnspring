package com.prateek.learnspring.dao;

import java.util.List;

import com.prateek.learnspring.model.User;

public interface UserDao {
	
	public void addUser(User user);
	
	public User getUser(int id);
	
	public List getAllUsers();
}
