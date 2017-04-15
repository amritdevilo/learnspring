package com.prateek.learnspring.dao;

import java.util.List;

import com.prateek.learnspring.entity.Song;
import com.prateek.learnspring.exceptions.DalException;
import com.prateek.learnspring.model.AddSongRequest;
import com.prateek.learnspring.model.SongAndRating;

public interface SongsDao {
	
	public List<SongAndRating> getSongsAndRatingsbyUser(String userId) throws DalException;
	
	public void insertRating(String userId, String songId, float rating) throws DalException;
	
	public void updateRating(String ratingId, float rating) throws DalException;
	
	public void deleteSong(String songId, String userId) throws DalException;

	public Song addSong(AddSongRequest song) throws DalException;
	
	public void updateSong(Song song) throws DalException;
	
	public boolean isLinkExists(String link, String userId) throws DalException;
	
	public List<Song> getSongList(String userId) throws DalException;
	
	public SongAndRating getSongAndRatingbyUser(String userId, String songId) throws DalException;
}
