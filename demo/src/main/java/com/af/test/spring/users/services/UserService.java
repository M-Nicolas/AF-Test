package com.af.test.spring.users.services;

import com.af.test.spring.users.entities.User;
import com.af.test.spring.users.exceptions.UserControllerException;

/**
 * A service meant to do the base actions on the 
 *
 */
public interface UserService {

	/**
	 * A method to register a user in the application.
	 * @param user the {@link User we want to register
	 * @return the {@link User} registered
	 * @throws UserControllerException exception sent if the user wasn't registered
	 */
	User registerUser(User user) throws UserControllerException;
	
	/**
	 * A method to retrieve a user providing an id.
	 * @param id the id of the user
	 * @return a representation of the user as a {@link User}
	 * @throws UserControllerException 
	 */
	User getUser(Long id) throws UserControllerException;

	/**
	 * A method to retrieve a user providing a username.
	 * @param username the username of the user
	 * @return a representation of the user as a {@link User}
	 * @throws UserControllerException 
	 */
	User getUserByUsername(String username) throws UserControllerException;
	
	/**
	 * A method to update an existing user.
	 * @param id the id of the user we are searching to update.
	 * @param userModel the new values to store in the user.
	 * @return the updated user
	 * @throws UserControllerException an exception can be sent if the user didn't exist.
	 */
	User updateUser(Long id, User userModel) throws UserControllerException;
	
	/**
	 * A method to delete an existing user.
	 * @param id the id of the user we want to delete.
	 * @throws UserControllerException an exception can be sent if the user couldn't be updated.
	 */
	void deleteUser(Long id) throws UserControllerException;
}
