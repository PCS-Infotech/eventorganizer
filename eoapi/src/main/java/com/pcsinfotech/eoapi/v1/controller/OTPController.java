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
		String reqCountry = otpRequest.getCountry();
		String reqMobile = otpRequest.getMobile();
		String reqIsoCode = otpRequest.getIsoCode();
		
		OTPRequestResponse resp;
		
		try {
			OTP sendOtpResp = otpService.sendOTP(reqCountry, reqIsoCode, reqMobile);
			resp = mapper.otpToOTPRequestResponse(sendOtpResp);
			return resp;
		}
		catch (Exception e) {
			resp =  new OTPRequestResponse();
			resp.setSuccess(false);
			resp.setErrorCode(ErrorCode.PCS_3.getErrorCode());
			resp.setErrorMessage(ErrorCode.PCS_3.getErrorMessage());
			resp.setOtpValidTimeoutInSecs(0);
		}
		return resp;
	}

}
