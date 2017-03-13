package com.prateek.learnspring.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="message")
public class Message {
	
	@Id @GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy="uuid2")
	private String id;
	
	private String fromId;
	
	private String toId;
	
	private String songId;
	
	private Timestamp ts;

	public Message(String id, String fromId, String toId, String songId, Timestamp ts) {
		super();
		this.id = id;
		this.fromId = fromId;
		this.toId = toId;
		this.songId = songId;
		this.ts = ts;
	}
	
	public Message(String toId, String songId) {
		super();
		this.toId = toId;
		this.songId = songId;
	}
	
	public Message() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

	public Timestamp getTs() {
		return ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}
	
}
