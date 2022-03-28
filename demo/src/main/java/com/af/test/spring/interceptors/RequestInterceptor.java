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

	@After(value = "execution(* com.af.test.spring.users.controllers.UserController.getUser(..)) and args(id)")
	public void afterAdvice(JoinPoint joinPoint, Long id) {
		System.out.println("After method:" + joinPoint.getSignature());

		Long execTime = (startTimeGetWithId.get(id).getTime() - new Date().getTime());
		System.out.println("Finished get User with id - " + id + " in " + execTime + " ms");
		startTimeGetWithId.remove(id);
	}
	
	private Map<String, Date> startTimeWithUsername = new HashMap<>();
	@Before(value = "execution(* com.af.test.spring.users.controllers.UserController.getUserByUsername(..)) and args(username)")
	public void beforeAdvice(JoinPoint joinPoint, String username) {
		System.out.println("Before method:" + joinPoint.getSignature());

		startTimeWithUsername.put(username, new Date());
		System.out.println("Searching User with username - " + username + " at time: " + startTimeWithUsername.get(username));
	}
	
	@After(value = "execution(* com.af.test.spring.users.controllers.UserController.getUserByUsername(..)) and args(username)")
	public void afterAdvice(JoinPoint joinPoint, String username) {
		System.out.println("After method:" + joinPoint.getSignature());

		Long execTime = (startTimeWithUsername.get(username).getTime() - new Date().getTime());
		System.out.println("Searching User with username - " + username + " in " + execTime + " ms");
		startTimeWithUsername.remove(username);
	}
	
	private Map<String, Date> startTimeRegistering = new HashMap<>();
	@Before(value = "execution(* com.af.test.spring.users.controllers.UserController.registerUser(..)) and args(userModel)")
	public void beforeAdvice(JoinPoint joinPoint, User userModel) {
		System.out.println("Before method:" + joinPoint.getSignature());

		startTimeRegistering.put(userModel.getUsername(), new Date());
		System.out.println("Registering User with username - " + userModel.getUsername() + " at time: " + startTimeRegistering.get(userModel.getUsername()));
	}
	
	@After(value = "execution(* com.af.test.spring.users.controllers.UserController.registerUser(..)) and args(userModel)")
	public void afterAdvice(JoinPoint joinPoint, User userModel) {
		System.out.println("After method:" + joinPoint.getSignature());

		Long execTime = (startTimeRegistering.get(userModel.getUsername()).getTime() - new Date().getTime());
		System.out.println("Registering User with username - " + userModel.getUsername() + " in " + execTime + " ms");
		startTimeRegistering.remove(userModel);
	}
	
	private Map<String, Date> startTimeUpdating = new HashMap<>();
	@Before(value = "execution(* com.af.test.spring.users.controllers.UserController.updateUser(..)) and args(id, userModel)")
	public void beforeAdvice(JoinPoint joinPoint, Long id, User userModel) {
		System.out.println("Before method:" + joinPoint.getSignature());

		startTimeUpdating.put(userModel.getUsername(), new Date());
		System.out.println("Updating User with id - " + id + " at time: " + startTimeUpdating.get(userModel.getUsername()));
	}
	
	@After(value = "execution(* com.af.test.spring.users.controllers.UserController.updateUser(..)) and args(id, userModel)")
	public void afterAdvice(JoinPoint joinPoint, Long id, User userModel) {
		System.out.println("After method:" + joinPoint.getSignature());

		Long execTime = (startTimeUpdating.get(userModel.getUsername()).getTime() - new Date().getTime());
		System.out.println("Updating User with id - " + id + " in " + execTime + " ms");
		startTimeUpdating.remove(userModel);
	}
	
	private Map<Long, Date> startTimeDeleteWithId = new HashMap<>();
	@Before(value = "execution(* com.af.test.spring.users.controllers.UserController.deleteUser(..)) and args(id)")
	public void beforeDeleteAdvice(JoinPoint joinPoint, Long id) {
		System.out.println("Before method:" + joinPoint.getSignature());

		startTimeDeleteWithId.put(id, new Date());
		System.out.println("Deleting User with id - " + id + " at time: " + startTimeDeleteWithId.get(id));
	}

	@After(value = "execution(* com.af.test.spring.users.controllers.UserController.deleteUser(..)) and args(id)")
	public void afterDeleteAdvice(JoinPoint joinPoint, Long id) {
		System.out.println("After method:" + joinPoint.getSignature());

		Long execTime = (startTimeDeleteWithId.get(id).getTime() - new Date().getTime());
		System.out.println("Deleting get User with id - " + id + " in " + execTime + " ms");
		startTimeDeleteWithId.remove(id);
	}
}
