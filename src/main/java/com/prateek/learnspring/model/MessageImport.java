package com.prateek.learnspring.model;

public class MessageImport {
	
	private String songId;
	
	private String userId;
	
	private float rating;
	
	public MessageImport() {
		super();
	}

	public MessageImport(String songId, String userId, float rating) {
		super();
		this.songId = songId;
		this.userId = userId;
		this.rating = rating;
	}

	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	
}
