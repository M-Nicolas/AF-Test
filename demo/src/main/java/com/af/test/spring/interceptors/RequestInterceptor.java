package com.af.test.spring.interceptors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.af.test.spring.users.entities.User;

@Aspect
@Component
public class RequestInterceptor {

	private Map<Long, Date> startTimeGetWithId = new HashMap<>();
	
	@Before(value = "execution(* com.af.test.spring.users.controllers.UserController.getUser(..)) and args(id)")
	public void beforeAdvice(JoinPoint joinPoint, Long id) {
		System.out.println("Before method:" + joinPoint.getSignature());

		startTimeGetWithId.put(id, new Date());
		System.out.println("Searching User with id - " + id + " at time: " + startTimeGetWithId.get(id));
	}

	@After(value = "execution(* com.af.test.spring.users.controllers.UserController.*(..)) and args(id)")
	public void afterAdvice(JoinPoint joinPoint, Long id) {
		System.out.println("After method:" + joinPoint.getSignature());

		Long execTime = (startTimeGetWithId.get(id).getTime() - new Date().getTime());
		System.out.println("Finished get User with id - " + id + " in " + execTime + " ms");
		startTimeGetWithId.remove(id);
	}
	
	private Map<String, Date> startTimeWithUsername = new HashMap<>();
	@Before(value = "execution(* com.af.test.spring.users.controllers.UserController.getUser(..)) and args(username)")
	public void beforeAdvice(JoinPoint joinPoint, String username) {
		System.out.println("Before method:" + joinPoint.getSignature());

		startTimeWithUsername.put(username, new Date());
		System.out.println("Searching User with username - " + username + " at time: " + startTimeWithUsername.get(username));
	}
	
	@After(value = "execution(* com.af.test.spring.users.controllers.UserController.*(..)) and args(username)")
	public void afterAdvice(JoinPoint joinPoint, String username) {
		System.out.println("After method:" + joinPoint.getSignature());

		Long execTime = (startTimeWithUsername.get(username).getTime() - new Date().getTime());
		System.out.println("Searching User with username - " + username + " in " + execTime + " ms");
		startTimeWithUsername.remove(username);
	}
	
	private Map<User, Date> startTimeRegistering = new HashMap<>();
	@Before(value = "execution(* com.af.test.spring.users.controllers.UserController.getUser(..)) and args(userModel)")
	public void beforeAdvice(JoinPoint joinPoint, User userModel) {
		System.out.println("Before method:" + joinPoint.getSignature());

		startTimeRegistering.put(userModel, new Date());
		System.out.println("Registering User with username - " + userModel.getUsername() + " at time: " + startTimeRegistering.get(userModel));
	}
	
	@After(value = "execution(* com.af.test.spring.users.controllers.UserController.*(..)) and args(userModel)")
	public void afterAdvice(JoinPoint joinPoint, User userModel) {
		System.out.println("After method:" + joinPoint.getSignature());

		Long execTime = (startTimeRegistering.get(userModel).getTime() - new Date().getTime());
		System.out.println("Registering User with username - " + userModel.getUsername()r + " in " + execTime + " ms");
		startTimeRegistering.remove(userModel);
	}
}
