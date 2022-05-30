package com.tukorea.ciwa.exception;

public class AlreadyExistUserException extends Exception {
	private static final long serialVersionUID = 1000L;

	public AlreadyExistUserException() {
	}

	public AlreadyExistUserException(String msg) {
		super(msg);
	}

	public AlreadyExistUserException(Throwable th) {
		super(th);
	}
}
