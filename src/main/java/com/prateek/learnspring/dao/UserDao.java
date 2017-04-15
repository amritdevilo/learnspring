package com.prateek.learnspring.dao;

import java.util.List;

import com.prateek.learnspring.entity.User;
import com.prateek.learnspring.exceptions.DalException;
import com.prateek.learnspring.model.ChangePassword;
import com.prateek.learnspring.model.UserSearch;

public interface UserDao {
	
	public boolean addUser(User user) throws DalException;
	
	public User getUser(String id) throws DalException;
	
	public List getAllUsers() throws DalException;
	
	public boolean isEmailExists(String email) throws DalException;
	
	public User getUserByEmail(String email) throws DalException;
	
	public List<UserSearch> getUserList(String key) throws DalException;

	public void changeUserPassword(String id, String encNewPasswd) throws DalException;
}
