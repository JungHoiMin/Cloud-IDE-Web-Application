package com.tukorea.ciwa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ControllerAdvice
public class CIWAControllerAdvice {
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Exception e) {
		e.printStackTrace();
		return "error/error";
	}

	@ExceptionHandler(UserNotFoundException.class)
	public String handelException(UserNotFoundException e) {
		e.printStackTrace();
		return "error/user_not_found";
	}
	
	@ExceptionHandler(AlreadyExistUserException.class)
	public String handelException(AlreadyExistUserException e) {
		e.printStackTrace();
		return "error/user_already_exist";
	}
}
