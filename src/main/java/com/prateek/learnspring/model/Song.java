package com.prateek.learnspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="song")
public class Song {
	
	@Id @GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy="uuid2")
	private String id;
	
	private String name;
	
	@Column(unique=true)
	private String link;
	
	private String resource;
	
	private String userId;
	
	public Song() {
		super();
	}
	
	public Song(String id, String name, String link, String resource, String userId) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.resource = resource;
		this.userId = userId;
	}
	
	public Song(String id, String name, String link, String userId) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
