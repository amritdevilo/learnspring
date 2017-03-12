package com.prateek.learnspring.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import org.hibernate.SessionFactory;

import com.prateek.learnspring.exceptions.DalException;
import com.prateek.learnspring.model.Message;
import com.prateek.learnspring.model.UserMessage;

public class MessageDaoImpl implements MessageDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addMessage(Message message) throws DalException {
		try {
			sessionFactory.getCurrentSession().persist(message);
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}
	
	public Message getMessage(String messageId) throws DalException {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from Messages where id=:messageId");
			query.setParameter("id", messageId);
			Message res = (Message)query.getSingleResult();
			return res;
		} catch (NoResultException e) {
			return null;
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}
	
	public List<UserMessage> getMessageFromId(String toId, String fromId, int limit) throws DalException {
		try {
			String sql = "select m.id as messageId, m.fromId, u.firstName, u.lastName, u.email, s.songId, s.name, s.link, m.ts "
					+ "from  Message m "
					+ "left join User u on m.fromId=u.id "
					+ "left join song s on m.songId=s.id "
					+ "where m.fromId=:fromId and m.toId=:toId "
					+ "order by m.ts desc";
			if (limit != -1){
				sql += " limit by :limit";
			}
			Query query = sessionFactory.getCurrentSession()
					.createNativeQuery(sql, "MessageAndRatingDtoMapping");
			query.setParameter("fromId", fromId);
			query.setParameter("toId", toId);
			if (limit != -1) {
				query.setParameter("limit", limit);
			}
			
			List<UserMessage> res = query.getResultList();
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

	public List<UserMessage> getAllMessages(String toId, int from , int to) throws DalException {
		try {
			String sql = "select m.id as messageId, m.fromId, u.firstName, u.lastName, u.email, s.songId, s.name, s.link, m.ts "
					+ "from  Message m "
					+ "left join User u on m.fromId=u.id "
					+ "left join song s on m.songId=s.id "
					+ "where m.toId=:toId "
					+ "order by m.ts desc limit by :limit";
			Query query = sessionFactory.getCurrentSession()
					.createNativeQuery(sql, "MessageAndRatingDtoMapping");
			query.setParameter("toId", toId);
			if (to != -1) {
				to = Integer.MAX_VALUE;
				query.setParameter("limit", to);
			}
			
			List<UserMessage> res = query.getResultList();
			return res.subList(from, Math.min(to, res.size()));
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
