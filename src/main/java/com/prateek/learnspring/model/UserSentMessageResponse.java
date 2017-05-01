package com.prateek.learnspring.model;

import java.util.List;

public class UserSentMessageResponse extends ServiceResponse {
	
	private List<SentMessageAndRating> response;

	public UserSentMessageResponse(int status, String message) {
		super(status, message);
	}
	
	public UserSentMessageResponse(int status, String message, List<SentMessageAndRating> response) {
		super(status, message);
		this.response = response;
	}

	public List<SentMessageAndRating> getResponse() {
		return response;
	}

	public void setResponse(List<SentMessageAndRating> response) {
		this.response = response;
	}
}
