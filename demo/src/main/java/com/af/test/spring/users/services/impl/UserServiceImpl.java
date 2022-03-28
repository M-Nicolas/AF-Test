package com.af.test.spring.users.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.af.test.spring.users.entities.User;
import com.af.test.spring.users.exceptions.UserControllerException;
import com.af.test.spring.users.exceptions.enums.ErrorState;
import com.af.test.spring.users.repositories.UserRepository;
import com.af.test.spring.users.services.UserService;

@Service
public class UserServiceImpl  implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) throws UserControllerException {
		Calendar majorityCalendar = Calendar.getInstance();
		majorityCalendar.add(Calendar.YEAR, -18);
		majorityCalendar.add(Calendar.DATE, -1);
		Date majorityDate = majorityCalendar.getTime();
		if (user.getBirthdate().before(majorityDate)) {
			Example<User> example = Example.of(new User(user.getUsername(), user.getBirthdate(), user.getCountry()));
			List<User> users = userRepository.findAll(example, Sort.by("username"));
			if (users.size() <= 0) {
				log.info("creating user: " + user);
				return userRepository.save(user);
			} else {
				throw new UserControllerException(ErrorState.USER_ALREADY_EXIST);
			}
		} else {
			throw new UserControllerException(ErrorState.USER_MINOR);
		}
	}

	@Override
	public User getUser(Long id) throws UserControllerException {
		return userRepository.findById(id).orElseThrow(() -> new UserControllerException(ErrorState.USER_NOT_FOUND));
	}

	@Override
	public User getUserByUsername(String username) throws UserControllerException {
		User exampleUser = new User();
		exampleUser.setUsername(username);
		Example<User> example = Example.of(exampleUser);
		List<User> users = userRepository.findAll(example, Sort.by("username"));
		if (users.size() > 0) {
			return users.get(0);
		} else {
			throw new UserControllerException(ErrorState.USER_NOT_FOUND);
		}
	}

	@Override
	public User updateUser(Long id, User userModel) throws UserControllerException {
		User user = getUser(id);
		if (user != null && user.getId() != null) {
			user.setUsername(userModel.getUsername());
			user.setBirthdate(userModel.getBirthdate());
			user.setCountry(userModel.getCountry());
			user.setGender(userModel.getGender());
			user.setPhoneNumber(userModel.getPhoneNumber());
			return userRepository.save(user);
		}
		throw new UserControllerException(ErrorState.USER_NOT_FOUND);
	}

	@Override
	public void deleteUser(Long id) throws UserControllerException {
		User user = getUser(id);
		if (user != null && user.getId() != null) {
			userRepository.delete(user);
		} else {
			throw new UserControllerException(ErrorState.USER_NOT_FOUND);
		}
	}
}
