package com.prateek.learnspring.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
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
	public boolean addUser(User user) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.save(user);
			return true;
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			return false;
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
	
	@Transactional
	public boolean isEmailExists(String email) {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from User where email=:email");
			query.setParameter("email", email);
			List res = query.getResultList();
			if (res != null && res.size() == 1) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
	
	@Transactional
	public User getUserByEmail(String email) {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from User where email=:email");
			query.setParameter("email", email);
			User user = (User)query.getSingleResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
