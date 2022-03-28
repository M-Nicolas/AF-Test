package com.af.test.spring.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.af.test.spring.users.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
