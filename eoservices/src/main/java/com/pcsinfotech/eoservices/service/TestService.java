package com.pcsinfotech.eoservices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.pcsinfotech.eodata.repositories.UserRepository;
import com.pcsinfotech.eoservices.model.User;

@Service
public class TestService {
	
	@Autowired
	private UserRepository userRepository;

	public List<User> getUsers(){
		List<User> usersList = new ArrayList<User>();
		
		List<com.pcsinfotech.eodata.entities.User> dbUsers = userRepository.getUsers();
		if(!CollectionUtils.isEmpty(dbUsers)) {
			dbUsers.stream().forEach(t->{
				User u = new User();
				u.setFirstName(t.getFirstName());
				u.setLastName(t.getLastName());
				usersList.add(u);
			});
		}
		
		return usersList;
	}
}
