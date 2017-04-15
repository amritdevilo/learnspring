package com.prateek.learnspring.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.prateek.learnspring.entity.Rating;
import com.prateek.learnspring.entity.Song;
import com.prateek.learnspring.exceptions.DalException;
import com.prateek.learnspring.model.AddSongRequest;
import com.prateek.learnspring.model.SongAndRating;

public class SongsDaoImpl implements SongsDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(rollbackFor={Exception.class})
	public List<SongAndRating> getSongsAndRatingsbyUser(String userId) throws DalException {
		try {
			Query query = sessionFactory.getCurrentSession().createNativeQuery("select s.id as songId, s.name as songName, s.link, IFNULL(r.rating, 0.0) as rating "
					+ "from song s left join rating r on r.songId=s.id where s.userId=:userId", "SongAndRatingDtoMapping");
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
	public void deleteSong(String songId, String userId) throws DalException {
		try {
			Query query  = sessionFactory.getCurrentSession().createQuery("delete from Rating where songId=:songId and userId=:userId");
			query.setParameter("songId", songId);
			query.setParameter("userId", userId);
			query.executeUpdate();
			
			query  = sessionFactory.getCurrentSession().createQuery("delete from Song where id=:songId and userId=:userId");
			query.setParameter("songId", songId);
			query.setParameter("userId", userId);
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
	public Song addSong(AddSongRequest song) throws DalException {
		try {
			if (song == null || song.getUserId() == null || song.getUserId().length() == 0 || isLinkExists(song.getLink(), song.getUserId())) {
				return null;
			}
			
			Song s = new Song(song.getName(), song.getLink(), song.getUserId());
			sessionFactory.getCurrentSession().persist(s);
			Query query = sessionFactory.getCurrentSession().createQuery("from Song where link=:link and userId=:userId");
			query.setParameter("link", song.getLink());
			query.setParameter("userId", song.getUserId());
			Song res = (Song)query.getSingleResult();
			
			Rating rating = new Rating(res.getId(), res.getUserId(), song.getRating());
			sessionFactory.getCurrentSession().save(rating);
			return s;
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
			Query query  = sessionFactory.getCurrentSession().createQuery("update Song set name=:name, link=:link where id=:id");
			query.setParameter("name", song.getName());
			query.setParameter("link", song.getLink());
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
	public boolean isLinkExists(String link, String userId) throws DalException {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("select id from Song where link=:link and userId=:userId");
			query.setParameter("link", link);
			query.setParameter("userId", userId);
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
	
	@Transactional(rollbackFor={Exception.class})
	public SongAndRating getSongAndRatingbyUser(String userId, String songId) throws DalException {
		try {
			Query query = sessionFactory.getCurrentSession().createNativeQuery("select s.id as songId, s.name as songName, s.link, IFNULL(r.rating, 0.0) as rating "
					+ "from song s left join rating r on r.songId=s.id where s.userId=:userId and s.id=:songId", "SongAndRatingDtoMapping");
			query.setParameter("userId", userId);
			query.setParameter("songId", songId);
			
			SongAndRating res = (SongAndRating) query.getSingleResult();
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
