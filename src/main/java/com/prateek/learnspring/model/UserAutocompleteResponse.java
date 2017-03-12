package com.prateek.learnspring.model;

import java.util.List;

public class UserAutocompleteResponse extends ServiceResponse{
	
	private List<UserSearch> user;

	public UserAutocompleteResponse(int status, String message, List<UserSearch> user) {
		super(status, message);
		this.user = user;
	}
	
	public UserAutocompleteResponse(int status, String message) {
		super(status, message);
	}

	public List<UserSearch> getUser() {
		return user;
	}

	public void setUser(List<UserSearch> user) {
		this.user = user;
	}

}
