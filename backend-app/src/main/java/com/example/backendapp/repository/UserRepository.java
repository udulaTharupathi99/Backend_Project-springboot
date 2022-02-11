package com.example.backendapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.backendapp.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query (value = "SELECT u.password FROM user u WHERE u.email=?1", nativeQuery = true)
	String checkpassword(String email);

}
