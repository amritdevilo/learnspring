package com.prateek.learnspring.model;

public class SongAndRating {
	
	private String songId;
	
	private String songName;
	
	private String link;
	
	private float rating;
	
	public SongAndRating() {
		super();
	}

	public SongAndRating(String songId, String songName, String link, float rating) {
		super();
		this.songId = songId;
		this.songName = songName;
		this.link = link;
		this.rating = rating;
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

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

}
