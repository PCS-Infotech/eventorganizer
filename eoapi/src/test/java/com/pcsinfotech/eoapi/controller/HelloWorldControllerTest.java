package com.pcsinfotech.eoapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import com.pcsinfotech.eoservices.model.User;
import com.pcsinfotech.eoservices.service.TestService;

@ExtendWith(MockitoExtension.class)
public class HelloWorldControllerTest {
	
	
	  @Mock 
	  private TestService testService;
	 
	
	@InjectMocks
	private HelloWorldController helloWorldController;
	
	
	@Test 
	void getUsersByFirstName_return_empty_list() {
	  when(testService.getUsersByFirstName(anyString())).thenReturn(new ArrayList<>()); 
	  List<User>
	  users=helloWorldController.getUsersByFirstName("Test");
	  assertTrue(CollectionUtils.isEmpty(users)); 
	}
	 
	
	
	  @Test 
	  void getUsersByFirstName_return_list_not_empty() {
	  
	  User mockUser = new User();
	  List mockUsers = new ArrayList<>();
	  mockUsers.add(mockUser);
	  
	  when(testService.getUsersByFirstName("Test")).thenReturn(mockUsers); 

	  List<User> users=helloWorldController.getUsersByFirstName("Test");
	  assertTrue(!CollectionUtils.isEmpty(users)); 
	  assertTrue(1 == users.size());
	  //assertEquals(2, users.size()); 
	  }

}
