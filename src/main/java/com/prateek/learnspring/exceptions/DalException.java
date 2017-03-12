package com.prateek.learnspring.exceptions;

public class DalException extends Exception {
	public DalException() {
		super();
	}
	
	public DalException(String message) {
		super(message);
	}
	
	public DalException(String message, Throwable t) {
		super(message, t);
	}
}
