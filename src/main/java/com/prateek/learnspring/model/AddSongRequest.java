package com.prateek.learnspring.model;

public class AddSongRequest {
	
	private String name;
	
	private String link;
	
	private String userId;
	
	private float rating;

	public AddSongRequest(String name, String link, String userId, float rating) {
		super();
		this.name = name;
		this.link = link;
		this.userId = userId;
		this.rating = rating;
	}

	public AddSongRequest() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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