package com.af.test.spring.users.exceptions;

import com.af.test.spring.users.exceptions.enums.ErrorState;

import lombok.Getter;
import lombok.Setter;

/**
 * An {@link Exception} throwed when trying to create and store a {@link User}, but creating couldn't be processed forward.
 *
 */
@Getter
@Setter
public class UserControllerException extends Exception {

	private static final long serialVersionUID = 3018365095072029140L;
	
	/**
	 * The {@link ErrorState} representing the error that occured.
	 */
	private ErrorState errorState;

	public UserControllerException(ErrorState errorState) {
		this.errorState = errorState;
	}
}
