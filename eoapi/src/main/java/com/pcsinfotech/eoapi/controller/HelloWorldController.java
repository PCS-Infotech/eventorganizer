package com.pcsinfotech.eoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcsinfotech.eoservices.model.User;
import com.pcsinfotech.eoservices.service.TestService;

@RestController
public class HelloWorldController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(value="/")
	public String hello() {
		return "Hello World!";
	}
	
	@GetMapping(value = "/User", produces = "application/json")
	public List<User> getUsers() {
		return testService.getUsers();
	}
}
