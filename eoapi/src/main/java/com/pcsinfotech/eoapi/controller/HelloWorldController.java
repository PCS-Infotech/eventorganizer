package com.pcsinfotech.eoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping(value = "/User/{id}", produces = "application/json")
	public Integer getUsers(@PathVariable Integer id, @RequestParam (required = true) String firstName) {
		return testService.updateUser(id,firstName);
	}
	
	@GetMapping(value = "/User/{firstName}", produces = "application/json")
	public List<User> getUsersByFirstName(@PathVariable String firstName) {
		return testService.getUsersByFirstName(firstName);
	}
	
	@PostMapping(value = "/User", produces = "application/json")
	public Integer insertUser(@RequestBody User user) {
		return testService.insertUser(user);
	}
	
	@GetMapping(value = "/echo/{text}/{userid}", produces = "application/json")
	public String echoMyText(@PathVariable String text,@PathVariable Integer userid,@RequestParam (required = false) String param1,@RequestParam (required = false) String param2) {
		return "Hi "+text+" .. "+String.valueOf(userid)+" .."+param1+" .."+param2;
	}
	
}
