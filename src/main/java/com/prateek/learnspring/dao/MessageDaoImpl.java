package com.prateek.learnspring.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.prateek.learnspring.entity.Message;
import com.prateek.learnspring.entity.Rating;
import com.prateek.learnspring.entity.Song;
import com.prateek.learnspring.exceptions.DalException;
import com.prateek.learnspring.model.MessageImport;
import com.prateek.learnspring.model.UserMessage;

public class MessageDaoImpl implements MessageDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(rollbackFor={DalException.class})
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
	
	@Transactional(rollbackFor={DalException.class})
	public Message getMessage(String messageId) throws DalException {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from Message where id=:messageId");
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
	
	@Transactional(rollbackFor={DalException.class})
	public List<UserMessage> getMessageFromId(String toId, String fromId, int limit) throws DalException {
		try {
			String sql = "select m.id as messageId, m.fromId, u.firstName, u.lastName, u.email, s.songId, s.name, s.link "
					+ "from  Message m "
					+ "left join UserDetails u on m.fromId=u.id "
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

	@Transactional(rollbackFor={DalException.class})
	public List<UserMessage> getAllMessages(String toId, int from, int to) throws DalException {
		try {
			String sql = "select m.id as messageId, m.fromId, u.firstName, u.lastName, u.email, s.id as songId, s.name, s.link "
					+ "from message m "
					+ "left join song s on m.songId=s.id "
					+ "left join UserDetails u on m.fromId=u.id "
					+ "where m.toId=:toId "
					+ "order by m.ts desc limit :limit";
			Query query = sessionFactory.getCurrentSession()
					.createNativeQuery(sql, "MessageAndRatingDtoMapping");
			query.setParameter("toId", toId);
			if (to == -1) {
				to = Integer.MAX_VALUE;
			}
			query.setParameter("limit", to);
			List<UserMessage> res = query.getResultList();
			int _from = Math.max(0, from);
			int _to = Math.min(res.size(), to); // exclusive
			return new ArrayList<UserMessage>(res.subList(_from, _to));
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}

	@Transactional(rollbackFor={DalException.class})
	public void importMessage(MessageImport messageImport) throws DalException {
		try {
			Query query = this.sessionFactory.getCurrentSession().createQuery("select id from Rating where songId=:songId and userId=:userId");
			query.setParameter("songId", messageImport.getSongId());
			query.setParameter("userId", messageImport.getUserId());
			
			List res = query.getResultList();
			
			if (res != null && res.size() > 0) {
				query = this.sessionFactory.getCurrentSession().createQuery("update Rating r set r.rating=:rating where songId=:songId and userId=:userId");
				query.setParameter("songId", messageImport.getSongId());
				query.setParameter("userId", messageImport.getUserId());
				query.setParameter("rating", messageImport.getRating());
				
				query.executeUpdate();
			} else {
				Rating rating = new Rating(messageImport.getSongId(), messageImport.getUserId(), messageImport.getRating());
				this.sessionFactory.getCurrentSession().save(rating);
			}
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
