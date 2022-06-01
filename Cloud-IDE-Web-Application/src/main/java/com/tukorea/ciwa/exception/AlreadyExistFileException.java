package com.tukorea.ciwa.exception;

public class AlreadyExistFileException extends Exception {
	private static final long serialVersionUID = 1000L;

	public AlreadyExistFileException() {
	}

	public AlreadyExistFileException(String msg) {
		super(msg);
	}

	public AlreadyExistFileException(Throwable th) {
		super(th);
	}
}
