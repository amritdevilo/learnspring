package com.prateek.learnspring.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.prateek.learnspring.model.User;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void addUser(User user) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User getUser(int id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			return session.get(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public List getAllUsers() {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from User");
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
