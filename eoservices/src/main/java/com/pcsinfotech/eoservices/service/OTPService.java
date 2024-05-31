package com.pcsinfotech.eoservices.service;

import org.springframework.stereotype.Service;

@Service
public class OTPService {
	
	public int sendOTP(String country, String isoCode, String mobile) {
		
		int otpExpirationTimeInSecs = 60;
		String otp = mobile.substring(0, 5);
		//To do: Insert OTP into the database
		return otpExpirationTimeInSecs;
	}
}
