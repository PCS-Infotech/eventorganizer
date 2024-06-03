package com.pcsinfotech.eoapi.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcsinfotech.eoapi.v1.Mapping.ServiceToControllerMapper;
import com.pcsinfotech.eoapi.v1.model.*;
import com.pcsinfotech.eoservices.model.*;
import com.pcsinfotech.eoservices.service.OTPService;

@RestController
@RequestMapping(value="/v1")
public class OTPController {
	
	@Autowired
	private OTPService otpService;
	
	@Autowired
	private ServiceToControllerMapper mapper;
	
	@PostMapping(value = "/requestOTP", produces = "application/json")
	public OTPRequestResponse requestOTP(@RequestBody OTPRequest otpRequest) {
		OTPRequestResponse resp;
		OTP sendOtpResp = otpService.sendOTP(otpRequest.getCountry(), otpRequest.getIsoCode(), otpRequest.getMobile());
		resp = mapper.otpToOTPRequestResponse(sendOtpResp);
		return resp;
	}

}
