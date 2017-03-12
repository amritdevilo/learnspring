package com.prateek.learnspring.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;

import com.prateek.learnspring.exceptions.DalException;
import com.prateek.learnspring.model.User;
import com.prateek.learnspring.model.UserSearch;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public boolean addUser(User user) throws DalException {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			session.save(user);
			return true;
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}

	public User getUser(int id) throws DalException {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			return session.get(User.class, id);
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	public List getAllUsers() throws DalException {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from User");
			return query.getResultList();
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}
	
	@Transactional
	public boolean isEmailExists(String email) throws DalException {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from User where email=:email");
			query.setParameter("email", email);
			List res = query.getResultList();
			if (res != null && res.size() == 1) {
				return true;
			}
			return false;
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}
	
	@Transactional
	public User getUserByEmail(String email) throws DalException {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from User where email=:email");
			query.setParameter("email", email);
			List<User> user = (List<User>)query.getResultList();
			if (user.size() == 0) return null;
			return user.get(0);
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}

	
	@Transactional
	public List<UserSearch> getUserList(String key) throws DalException {
		try {
			String sql = "select u.id as userId, u.firstName, u.lastName, u.email from UserDetails u "
					+ "where lower(u.firstName) like :key or lower(u.lastName) like :key or lower(u.email) like :key";
			Query query = this.sessionFactory.getCurrentSession().createNativeQuery(sql, "UserSearchDtoMapping");
			query.setParameter("key", "%" + key + "%");
			
			List<UserSearch> res = query.getResultList();
			return res;
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}
}
