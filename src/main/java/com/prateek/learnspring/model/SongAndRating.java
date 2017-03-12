package com.prateek.learnspring.model;

public class SongAndRating {
	
	private String ratingId;
	
	private String songId;
	
	private String songName;
	
	private String link;
	
	private String resource;
	
	private float rating;

	public SongAndRating(String ratingId, String songId, String songName, String link, String resource, float rating) {
		super();
		this.ratingId = ratingId;
		this.songId = songId;
		this.songName = songName;
		this.link = link;
		this.resource = resource;
		this.rating = rating;
	}

	public String getRatingId() {
		return ratingId;
	}

	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}

	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

}
