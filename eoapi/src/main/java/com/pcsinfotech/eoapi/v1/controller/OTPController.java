package com.pcsinfotech.eoapi.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pcsinfotech.eoapi.v1.model.*;
import com.pcsinfotech.eoservices.model.*;
import com.pcsinfotech.eoservices.model.IsoCode;
import com.pcsinfotech.eoservices.service.IsoCodesService;
import com.pcsinfotech.eoservices.service.OTPService;

@RestController
@RequestMapping(value="/v1")
public class OTPController {
	
	@Autowired
	private IsoCodesService isoCodesService;
	
	@Autowired
	private OTPService otpService;
	
	
	@PostMapping(value = "/requestOTP", produces = "application/json")
	public OTPRequestResponse requestOTP(@RequestBody OTPRequest otpRequest) {
		
		String reqCountry = otpRequest.getCountry();
		String reqMobile = otpRequest.getMobile();
		String reqIsoCode = otpRequest.getIsoCode();
		
		OTPRequestResponse resp = new OTPRequestResponse();
		resp.setSuccess(false);
		resp.setErrorCode(ErrorCode.PCS_0.getErrorCode());
		resp.setErrorMessage(ErrorCode.PCS_0.getErrorMessage());
		resp.setOtpValidTimeoutInSecs(0);
		
		//Validate country in request is populated. 
		if (!StringUtils.hasText(reqCountry)) {
			resp.setErrorCode(ErrorCode.PCS_11.getErrorCode());
			resp.setErrorMessage(ErrorCode.PCS_11.getErrorMessage());
			return resp;
		}
		
		//Validate ISO Code in request is populated
		if (!StringUtils.hasText(reqIsoCode)) {
			resp.setErrorCode(ErrorCode.PCS_12.getErrorCode());
			resp.setErrorMessage(ErrorCode.PCS_12.getErrorMessage());
			return resp;
		}
		
		//Validate  mobile in request is populated and is 10 characters.  
		if ((!StringUtils.hasText(reqMobile)) || (reqMobile.length() != 10) || (!reqMobile.matches("\\d{10}"))) {
			resp.setErrorCode(ErrorCode.PCS_1.getErrorCode());
			resp.setErrorMessage(ErrorCode.PCS_1.getErrorMessage());
			return resp;
		}
		
		//Check if ISO Code matches for the requested Country. 
		List<IsoCode> isoCodesSM = isoCodesService.getIsoCodesByCountryAndIsoCode(reqCountry, reqIsoCode);
		if (isoCodesSM.size() == 0) {
			resp.setErrorCode(ErrorCode.PCS_13.getErrorCode());
			resp.setErrorMessage(ErrorCode.PCS_13.getErrorMessage());
			return resp;
		}
		
		
		try {
			int expirationTime = otpService.sendOTP(reqCountry, reqIsoCode, reqMobile);
			resp.setSuccess(true);
			resp.setErrorCode(null);
			resp.setErrorMessage(null);
			resp.setOtpValidTimeoutInSecs(expirationTime);
		}
		catch (Exception e) {
			resp.setErrorCode(ErrorCode.PCS_3.getErrorCode());
			resp.setErrorMessage(ErrorCode.PCS_3.getErrorMessage());
		}
		
		return resp;
	}

}
