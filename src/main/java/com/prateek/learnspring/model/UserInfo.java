package com.prateek.learnspring.model;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.prateek.learnspring.util.FlashMessage;

public class UserInfo {
	
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private List<GrantedAuthority> auths;
	
	private FlashMessage flashMessage;
	
	public UserInfo(String id, String firstName, String lastName, String email, List<GrantedAuthority> auths) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.auths = auths;
		this.flashMessage = new FlashMessage();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFlashMessage(String message) {
		flashMessage.setMessage(message);
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public List<GrantedAuthority> getAuths() {
		return auths;
	}
	
	public String getFlashMessage() {
		return flashMessage.getMessage();
	}
}
