package com.prateek.learnspring.model;

import com.prateek.learnspring.entity.Song;

public class AddSongResponse extends ServiceResponse {
	
	private Song song;
	
	public AddSongResponse(int status, String message, Song song) {
		super(status, message);
		this.song = song;
	}

	public AddSongResponse(int status, String message) {
		super(status, message);
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

}
