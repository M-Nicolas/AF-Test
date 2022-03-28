package com.af.test.spring.users.exceptions.enums;

import lombok.Getter;

/**
 * An enum representing the various errors that can happen in retrieval or creation of a user.
 */
@Getter
public enum ErrorState {
	USER_MINOR("The user couldn't be created because the user is minor"), 
	USER_ALREADY_EXIST("The user couldn't be created because the user already exists"),
	USER_NOT_FOUND("The user couldn't be found");
	
	private String message;
	
	private ErrorState(String message) {
		this.message = message;
	}
}
