package com.pcsinfotech.eoapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcsinfotech.eoapi.model.IsoCode;
import com.pcsinfotech.eoapi.model.IsoCodesResponse;
import com.pcsinfotech.eoapi.model.Success;
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
	
	@GetMapping(value = "/v1/isoCodes", produces = "application/json")
	public IsoCodesResponse getIsoCodes() {
		
		IsoCodesResponse response = new IsoCodesResponse();
		response.setSuccess(true);
		response.setErrorCode(null);
		response.setErrorMessage(null);
		
		IsoCode isoCodeIndia = new IsoCode();
		isoCodeIndia.setCountry("INDIA");
		isoCodeIndia.setIsoCode("+91");
		
		IsoCode isoCodeUSA = new IsoCode();
		isoCodeUSA.setCountry("USA");
		isoCodeUSA.setIsoCode("+1");
		
		List<IsoCode> isoCodes = new ArrayList<IsoCode>();
		isoCodes.add(isoCodeUSA);
		isoCodes.add(isoCodeIndia);
		
		response.setIsoCodes(isoCodes);
		return response;
	}
}
