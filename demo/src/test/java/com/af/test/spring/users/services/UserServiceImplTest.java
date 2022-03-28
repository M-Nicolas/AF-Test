package com.af.test.spring.users.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import com.af.test.spring.users.entities.User;
import com.af.test.spring.users.exceptions.UserControllerException;
import com.af.test.spring.users.exceptions.enums.ErrorState;
import com.af.test.spring.users.repositories.UserRepository;
import com.af.test.spring.users.services.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository userRepositoryMock;
	
	@Test
	public void testRegisterUser_tooYoung() {
		Date date = new Date();
		User user = new User();
		user.setUsername("test");
		user.setBirthdate(date);
		user.setCountry("FR");
		
		UserControllerException ex = assertThrows(UserControllerException.class, () -> {
			userServiceImpl.registerUser(user);
		});
		
		assertThat(ex.getErrorState()).isEqualTo(ErrorState.USER_MINOR);
	}
	
	@Test
	public void testRegisterUser_AlreadyExists() {
		List<User> users = new ArrayList<>();
		Date date = new Date();
		date.setYear(91);
		User user = new User();
		user.setUsername("test");
		user.setBirthdate(date);
		user.setCountry("FR");
		users.add(user);
		
		when(userRepositoryMock.findAll(any(Example.class), any(Sort.class))).thenReturn(users);
		
		UserControllerException ex = assertThrows(UserControllerException.class, () -> {
			userServiceImpl.registerUser(user);
		});

		assertThat(ex.getErrorState()).isEqualTo(ErrorState.USER_ALREADY_EXIST);
	}
	
	@Test
	public void testRegisterUser_works() {
		Date date = new Date();
		date.setYear(91);
		User user = new User();
		user.setUsername("test");
		user.setBirthdate(date);
		user.setCountry("FR");
		User user2 = user;
		user2.setId(5l);
		
		when(userRepositoryMock.findAll(any(Example.class), any(Sort.class))).thenReturn(new ArrayList<User>());

		when(userRepositoryMock.save(any(User.class))).thenReturn(user2);
		
		User registeredUser;
		try {
			registeredUser = userServiceImpl.registerUser(user);
			assertThat(registeredUser.getId()).isEqualTo(5l);
		} catch (UserControllerException e) {
			fail();
		}
	}
	
	@Test
	public void testGetUser_userNotFound() {
		when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

		UserControllerException ex = assertThrows(UserControllerException.class, () -> {
			userServiceImpl.getUser(5l);
		});

		assertThat(ex.getErrorState()).isEqualTo(ErrorState.USER_NOT_FOUND);
	}
	
	@Test
	public void testGetUser_userFound() {
		Date date = new Date();
		date.setYear(91);
		User user = new User();
		user.setUsername("test");
		user.setBirthdate(date);
		user.setCountry("FR");
		
		when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(user));

		try {
			User userGot = userServiceImpl.getUser(5l);
			assertThat(userGot.getUsername()).isEqualTo("test");
			assertThat(userGot.getBirthdate()).isEqualTo(date);
			assertThat(userGot.getCountry()).isEqualTo("FR");
		} catch (UserControllerException e) {
			fail();
		}
	}
	
	@Test
	public void testGetUserByUsername_userNotFound() {
		
		when(userRepositoryMock.findAll(any(Example.class), any(Sort.class))).thenReturn(new ArrayList<User>());
		
		UserControllerException ex = assertThrows(UserControllerException.class, () -> {
			userServiceImpl.getUserByUsername("test");
		});

		assertThat(ex.getErrorState()).isEqualTo(ErrorState.USER_NOT_FOUND);
	}
	
	@Test
	public void testGetUserByUserName_userFound() {
		List<User> users = new ArrayList<>();
		Date date = new Date();
		date.setYear(91);
		User user = new User();
		user.setUsername("test");
		user.setBirthdate(date);
		user.setCountry("FR");
		users.add(user);
		
		when(userRepositoryMock.findAll(any(Example.class), any(Sort.class))).thenReturn(users);
		
		try {
			User userGot = userServiceImpl.getUserByUsername("test");
			assertThat(userGot.getUsername()).isEqualTo("test");
			assertThat(userGot.getBirthdate()).isEqualTo(date);
			assertThat(userGot.getCountry()).isEqualTo("FR");
		} catch (UserControllerException e) {
			fail();
		}
	}

	@Test
	public void testUpdateUser_userNotFound() {
		Date date = new Date();
		date.setYear(91);
		User user = new User();
		user.setUsername("test");
		user.setBirthdate(date);
		user.setCountry("FR");
		
		when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

		UserControllerException ex = assertThrows(UserControllerException.class, () -> {
			userServiceImpl.updateUser(5l, user);
		});

		assertThat(ex.getErrorState()).isEqualTo(ErrorState.USER_NOT_FOUND);
	}
	
	@Test
	public void testUpdateUser_userFound() {
		Date date = new Date();
		date.setYear(91);
		User user = new User();
		user.setUsername("test");
		user.setBirthdate(date);
		user.setCountry("FR");
		User user2 = user;
		user2.setId(5l);
		
		when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(user2));
		
		when (userRepositoryMock.save(any())).thenReturn(user2);

		try {
			User userUpdated = userServiceImpl.updateUser(5l, user);
			assertThat(userUpdated.getUsername()).isEqualTo("test");
			assertThat(userUpdated.getBirthdate()).isEqualTo(date);
			assertThat(userUpdated.getCountry()).isEqualTo("FR");
			assertThat(userUpdated.getId()).isEqualTo(5l);
		} catch (UserControllerException e) {
			fail();
		}
	}
	
	@Test
	public void testDeleteUser_userNotFound() {
		when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

		UserControllerException ex = assertThrows(UserControllerException.class, () -> {
			userServiceImpl.deleteUser(5l);
		});

		assertThat(ex.getErrorState()).isEqualTo(ErrorState.USER_NOT_FOUND);
	}
	
	@Test
	public void testDeleteUser_userFound() {
		Date date = new Date();
		date.setYear(91);
		User user = new User();
		user.setUsername("test");
		user.setBirthdate(date);
		user.setCountry("FR");
		user.setId(5l);
		
		when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(user));

		try {
			userServiceImpl.deleteUser(5l);
		} catch (UserControllerException e) {
			fail();
		}
	}
}
