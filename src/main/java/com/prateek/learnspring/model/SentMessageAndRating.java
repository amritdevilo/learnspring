package com.prateek.learnspring.model;

public class SentMessageAndRating {
	
	private String link;
	
	private String name;
	
	private String firstName;
	
	private String lastName;
	
	private float rating;

	public SentMessageAndRating() {
		super();
	}

	public SentMessageAndRating(String link, String name, String firstName, String lastName, float rating) {
		super();
		this.link = link;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.rating = rating;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
