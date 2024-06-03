package com.pcsinfotech.eoservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pcsinfotech.eoservices.model.ErrorCode;
import com.pcsinfotech.eoservices.model.IsoCodesList;
import com.pcsinfotech.eoservices.model.OTP;

@Service
public class OTPService {
	
	@Autowired
	private IsoCodesService isoCodesService;
	
	public OTP sendOTP(String country, String isoCode, String mobile) {
		
		OTP returnValue = new OTP();
		returnValue.setOtpExpirationInSecs(0);
		returnValue.setError(ErrorCode.PCS_0);
		
		//Validate Mobile Number is Valid
		if ((!StringUtils.hasText(mobile)) || (mobile.length() != 10) || (!mobile.matches("\\d{10}"))) {
			returnValue.setError(ErrorCode.PCS_1);
			return returnValue;
		}
		
		//Validate Country and IsoCode Match and Exist
		IsoCodesList isoCodesList = this.isoCodesService.getIsoCodesByCountryAndIsoCode(country, isoCode);
		if (isoCodesList.getList().size() == 0) {
			returnValue.setError(isoCodesList.getError());
			return returnValue;
		}
		
		String otp = mobile.substring(0, 5);
		//To do: Insert OTP into the database
		
		OTP response = new OTP();
		response.setOtp(otp);
		response.setOtpExpirationInSecs(60);
		return response;
	}
}
