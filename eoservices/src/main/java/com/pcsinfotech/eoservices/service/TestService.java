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
	
	public List<User> getUsersByFirstName(String firstName){
		List<User> usersList = new ArrayList<User>();
		
		List<com.pcsinfotech.eodata.entities.User> dbUsers = userRepository.getUsersByFirstName(firstName);
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
	
	public Integer insertUser(User user){
		com.pcsinfotech.eodata.entities.User dbUser = new com.pcsinfotech.eodata.entities.User();
		dbUser.setFirstName(user.getFirstName());
		dbUser.setLastName(user.getLastName());
		userRepository.save(dbUser);
		return dbUser.getId();
	}
	
	public Integer updateUser(Integer id, String firstName){
		com.pcsinfotech.eodata.entities.User dbUser = userRepository.findById(id).get();
		dbUser.setFirstName(firstName);
		userRepository.save(dbUser);
		return dbUser.getId();
	}
}
