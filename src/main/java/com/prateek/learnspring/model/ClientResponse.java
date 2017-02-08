package com.prateek.learnspring.model;

public class ClientResponse extends ServiceResponse {
	
	private UserInfo userInfo;
	
	public ClientResponse(int status, String message, UserInfo userInfo) {
		super(status, message);
		this.userInfo = userInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
