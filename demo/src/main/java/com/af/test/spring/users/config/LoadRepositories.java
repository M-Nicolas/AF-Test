package com.af.test.spring.users.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.af.test.spring.users.entities.User;
import com.af.test.spring.users.repositories.UserRepository;

@Configuration
public class LoadRepositories {

	private static final Logger log = LoggerFactory.getLogger(LoadRepositories.class);
	
	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository) {
		return args -> {
			Date birthdate = new Date();
			birthdate.setYear(95);
			birthdate.setMonth(11);
			birthdate.setDate(5);
			User jeanMichel = new User("Jean Michel", birthdate, "France");
			log.info("Preloading " + userRepository.save(jeanMichel));
			birthdate.setYear(84);
			User jeanJacques = new User("Jean Jacques", birthdate, "Belgium");
			log.info("Preloading " + userRepository.save(jeanJacques));
		};
	}
}
