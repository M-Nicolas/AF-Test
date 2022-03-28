package com.af.test.spring.users.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.af.test.spring.users.exceptions.UserControllerException;
import com.af.test.spring.users.exceptions.enums.ErrorState;

/**
 * A {@link ControllerAdvice} allowing to handle the {@link UserControllerException} that are unhandled in the application.
 * Is meant to return a 404 code if the error is caused by the lack of user. Will return a 500 error is the exception 
 * is another one.
 *
 */
@ControllerAdvice
public class UserExceptionAdvice {

	@ResponseBody
	@ExceptionHandler(UserControllerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> handlingUserException(UserControllerException e) {
		if (e.getErrorState().equals(ErrorState.USER_NOT_FOUND)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorState().getMessage());
		}
		return ResponseEntity.internalServerError().body(e.getErrorState().getMessage());
	}
	
}
