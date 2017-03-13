package com.prateek.learnspring.model;

import java.util.List;

public class UserAutocompleteResponse extends ServiceResponse{
	
	private List<UserSearch> userlist;

	public UserAutocompleteResponse(int status, String message, List<UserSearch> userlist) {
		super(status, message);
		this.userlist = userlist;
	}
	
	public UserAutocompleteResponse(int status, String message) {
		super(status, message);
	}

	public List<UserSearch> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<UserSearch> userlist) {
		this.userlist = userlist;
	}

}
