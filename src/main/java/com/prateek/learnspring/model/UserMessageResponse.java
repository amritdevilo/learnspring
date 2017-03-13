package com.prateek.learnspring.model;

import java.util.List;

public class UserMessageResponse extends ServiceResponse {
	
	List<UserMessage> userMessage;

	public UserMessageResponse(int status, String message, List<UserMessage> userMessage) {
		super(status, message);
		this.userMessage = userMessage;
	}
	
	public UserMessageResponse(int status, String message) {
		super(status, message);
	}

	public List<UserMessage> getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(List<UserMessage> userMessage) {
		this.userMessage = userMessage;
	}

}
