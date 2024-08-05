package com.pcsinfotech.eoapi.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pcsinfotech.eoapi.v1.Mapping.ServiceToControllerMapper;
import com.pcsinfotech.eoapi.v1.model.CreateCustomerResponse;
import com.pcsinfotech.eoapi.v1.model.CustomerRequest;
import com.pcsinfotech.eoservices.model.Customer;
import com.pcsinfotech.eoservices.service.CustomerService;


@RestController
@RequestMapping(value="/v1")

public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ServiceToControllerMapper mapper; 
	
	@PostMapping(value="/customer", produces="application/json")
	public CreateCustomerResponse createCustomer(@RequestBody CustomerRequest request ) {
		Customer customer = customerService.createCountryisoCode(mapper.CustomerRequestToCustomer(request));
		Customer customer1 = customerService.createtimeZone(customer);
		Customer customer2 = customerService.create(customer1);
		CreateCustomerResponse resp = mapper.CustomerToCreateCustomerResponse(customer2); 
		return resp;
	}
}
