package com.af.test.spring.users.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.af.test.spring.users.entities.User;
import com.af.test.spring.users.exceptions.UserControllerException;
import com.af.test.spring.users.exceptions.enums.ErrorState;
import com.af.test.spring.users.services.UserService;

/**
 * A Rest controller allowing the creation an retrieval of a {@link User} data.
 *
 */
@RestController
public class UserController {
	
	private final UserService userService;
	
	UserController(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * A method to create a {@link User} by providing the needed data, the body of the request must provide theses fields :
	 * username, birthdate and country. The gender and phoneNumber fields are not mandatory.
	 * 
	 * @param username the username of the user we are searching for. 
	 * @return a {@link ResponseEntity} containing a {@link User} in its body.
	 * @throws UserControllerException an exception occuring if we can't find the user we are searching for.
	 */
	@PutMapping("/user")
	public ResponseEntity<Long> registerUser(@Valid @RequestBody User userModel) throws UserControllerException {
		User user = userService.registerUser(userModel);
		if (user != null && user.getId() != null) {
			return ResponseEntity.ok(user.getId());
		} else {
			throw new UserControllerException(ErrorState.USER_NOT_FOUND);
		}
	}
	
	/**
	 * A method to get a {@link User} by it's ID.
	 * @param id the Id of the {@link User}
	 * @return a {@link ResponseEntity} containing a {@link User} in its body.
	 * @throws UserControllerException an exception occuring if we can't find the user we are searching for.
	 */
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) throws UserControllerException {
		return ResponseEntity.ok(userService.getUser(id));
	}
	
	/**
	 * A method to get a {@link User} by it's username.
	 * @param username the username of the user we are searching for. 
	 * @return a {@link ResponseEntity} containing a {@link User} in its body.
	 * @throws UserControllerException an exception occuring if we can't find the user we are searching for.
	 */
	@GetMapping("/user")
	public ResponseEntity<User> getUserByUsername(@RequestParam String username) throws UserControllerException {
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}
	
	/**
	 * A method to update a {@link User} with the provided values.
	 * @param id the id of the user we are searching for. 
	 * @param userModel the new values you want to store in the user.
	 * @return a {@link ResponseEntity} containing a {@link User} in its body.
	 * @throws UserControllerException an exception occuring if we can't find the user we are searching for.
	 */
	@PostMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userModel) throws UserControllerException {
		return ResponseEntity.ok(userService.updateUser(id, userModel));
	}
	
	/**
	 * A method to delete a {@link User} with the provided id.
	 * @param username the username of the user we are searching for. 
	 * @return the id of the deleted user.
	 * @throws UserControllerException an exception occuring if we can't find the user we are searching for.
	 */
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Long> deleteUser(@PathVariable Long id) throws UserControllerException {
		userService.deleteUser(id);
		return ResponseEntity.ok(id);
	}
}
