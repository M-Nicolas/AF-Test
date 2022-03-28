package com.af.test.spring.users.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Username is Mandatory")
	private String username;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@NotNull(message = "Birthdate is Mandatory")
	private Date birthdate;
	
	@NotBlank(message = "CountryCode is Mandatory")
	private String country;
	
	private String gender;
	private String phoneNumber;
	
	public User() {}
	
	public User(String username, Date birthdate, String countryCode) {
		this.username = username;
		this.birthdate = birthdate;
		this.country = countryCode;
	}
}
