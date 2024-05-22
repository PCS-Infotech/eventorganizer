package com.pcsinfotech.eodata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pcsinfotech.eodata.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u")
    List<User> getUsers();
}
