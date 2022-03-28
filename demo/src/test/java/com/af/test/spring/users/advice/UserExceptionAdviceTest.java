package com.af.test.spring.users.advice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.af.test.spring.users.exceptions.UserControllerException;
import com.af.test.spring.users.exceptions.enums.ErrorState;

public class UserExceptionAdviceTest {
	
	private UserExceptionAdvice toTest = new UserExceptionAdvice();
	
	@Test
	public void testHandlingUserException_UserNotFound() {
		UserControllerException ex = new UserControllerException(ErrorState.USER_NOT_FOUND);
		ResponseEntity<?> entity = toTest.handlingUserException(ex);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(entity.getBody()).isEqualTo(ErrorState.USER_NOT_FOUND.getMessage());
	}
	
	@Test
	public void testHandlingUserException_UserAlreadyExist() {
		UserControllerException ex = new UserControllerException(ErrorState.USER_ALREADY_EXIST);
		ResponseEntity<?> entity = toTest.handlingUserException(ex);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(entity.getBody()).isEqualTo(ErrorState.USER_ALREADY_EXIST.getMessage());
	}
	
	@Test
	public void testHandlingUserException_UserMinor() {
		UserControllerException ex = new UserControllerException(ErrorState.USER_MINOR);
		ResponseEntity<?> entity = toTest.handlingUserException(ex);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(entity.getBody()).isEqualTo(ErrorState.USER_MINOR.getMessage());
	}
}
