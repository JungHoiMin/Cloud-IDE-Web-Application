package com.tukorea.ciwa.exception;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 1000L;

	public UserNotFoundException() {
	}

	public UserNotFoundException(String msg) {
		super(msg);
	}

	public UserNotFoundException(Throwable th) {
		super(th);
	}
}
