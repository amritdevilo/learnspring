package com.prateek.learnspring.model;

import java.util.List;

public class SongsAndRatingsResponse extends ServiceResponse {
	
	private List<SongAndRating> songList;
	
	public SongsAndRatingsResponse(int status, String message) {
		super(status, message);
	}
	
	public SongsAndRatingsResponse(int status, String message, List<SongAndRating> songList) {
		super(status, message);
		this.songList = songList;
	}

	public List<SongAndRating> getSongList() {
		return songList;
	}

	public void setSongList(List<SongAndRating> songList) {
		this.songList = songList;
	}
	
}
