package com.prateek.learnspring.model;

public class SongAndRatingResponse extends ServiceResponse {
	
	private SongAndRating song;

	public SongAndRatingResponse(int status, String message) {
		super(status, message);
	}
	
	public SongAndRatingResponse(int status, String message, SongAndRating song) {
		super(status, message);
		this.song = song;
	}

	public SongAndRating getSong() {
		return this.song;
	}

	public void setSong(SongAndRating song) {
		this.song = song;
	}
}
