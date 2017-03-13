package com.prateek.learnspring.model;

import java.sql.Timestamp;

public class UserMessage {
	
	private String messageId;
	
	private String fromId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String songId;
	
	private String name;
	
	private String link;
	
//	private Timestamp ts;

	public UserMessage() {
		super();
	}
	
	public UserMessage(String messageId, String fromId, String firstName, String lastName, String email, String songId,
			String name, String link/*, Timestamp ts*/) {
		super();
		this.messageId = messageId;
		this.fromId = fromId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.songId = songId;
		this.name = name;
		this.link = link;
//		this.ts = ts;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
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

//	public Timestamp getTs() {
//		return ts;
//	}
//
//	public void setTs(Timestamp ts) {
//		this.ts = ts;
//	}
	
}
