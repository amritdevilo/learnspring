package com.prateek.learnspring.model;

public class ServiceResponse {
	
	private String message;
	
	private int status;
	
	public ServiceResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
