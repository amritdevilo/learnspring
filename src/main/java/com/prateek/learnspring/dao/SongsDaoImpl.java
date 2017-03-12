package com.prateek.learnspring.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.prateek.learnspring.exceptions.DalException;
import com.prateek.learnspring.model.Rating;
import com.prateek.learnspring.model.Song;
import com.prateek.learnspring.model.SongAndRating;

public class SongsDaoImpl implements SongsDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<SongAndRating> getSongsAndRatingsbyUser(String userId) throws DalException {
		try {
			Query query = sessionFactory.getCurrentSession().createNativeQuery("select r.id as ratingId, s.id as songId, s.name as songName, s.link, s.resource, r.rating "
					+ "from rating r left join song s on r.songId=s.id where r.userId=:userId", "SongAndRatingDtoMapping");
			query.setParameter("userId", userId);
			List<SongAndRating> result = query.getResultList();
			return result;
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}
	
	@Transactional(rollbackFor={Exception.class})
	public void insertRating(String userId, String songId, float rating) throws DalException {
		try {
			Rating r = new Rating(userId, songId, rating);
			sessionFactory.getCurrentSession().persist(r);
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}
	
	@Transactional(rollbackFor={Exception.class})
	public void updateRating(String ratingId, float rating) throws DalException {
		try {
			Query query  = sessionFactory.getCurrentSession().createQuery("update Rating set rating=:rating where id=:ratingId");
			query.setParameter("rating", rating);
			query.setParameter("id", ratingId);
			query.executeUpdate();
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}

	@Transactional(rollbackFor={Exception.class})
	public void deleteSong(String songId) throws DalException {
		try {
			Query query  = sessionFactory.getCurrentSession().createQuery("delete from Rating where songId=:songId");
			query.setParameter("songId", songId);
			query.executeUpdate();
			query  = sessionFactory.getCurrentSession().createQuery("delete from Song where id=:songId");
			query.setParameter("id", songId);
			query.executeUpdate();
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}

	@Transactional(rollbackFor={Exception.class})
	public Song addSong(Song song) throws DalException {
		try {
			if (isLinkExists(song.getLink())) {
				return null;
			}
			sessionFactory.getCurrentSession().persist(song);
			Query query = sessionFactory.getCurrentSession().createQuery("from Song where link=:link");
			query.setParameter("link", song.getLink());
			return (Song)query.getSingleResult();
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}

	@Transactional(rollbackFor={Exception.class})
	public void updateSong(Song song) throws DalException {
		try {
			Query query  = sessionFactory.getCurrentSession().createQuery("update Song set name=:name, link=:link, resource=:resource where id=:id");
			query.setParameter("name", song.getName());
			query.setParameter("link", song.getLink());
			query.setParameter("resource", song.getResource());
			query.setParameter("id", song.getId());
			query.executeUpdate();
		} catch (IllegalStateException e) {
			throw new DalException(e.getMessage());
		} catch (QueryTimeoutException e) {
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException(e.getMessage());
		}
	}
	
	@Transactional(rollbackFor={Exception.class})
	public boolean isLinkExists(String link) throws DalException {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("select id from Song where link=:link");
			query.setParameter("link", link);
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

	@Transactional(rollbackFor={Exception.class})
	public List<Song> getSongList(String userId) throws DalException {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from Song where userId=:userId");
			query.setParameter("userId", userId);
			List<Song> res = query.getResultList();
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
