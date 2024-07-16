package com.pcsinfotech.eoapi.v1.Mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pcsinfotech.eoapi.v1.model.OTPRequestResponse;
import com.pcsinfotech.eoapi.v1.model.VerifyOTPResponse;
import com.pcsinfotech.eoservices.model.OTP;

@Service
public class ServiceToControllerMapper {

	public OTPRequestResponse otpToOTPRequestResponse(OTP otp) {

		OTPRequestResponse resp = new OTPRequestResponse();
		resp.setOtpValidTimeoutInSecs(otp.getOtpExpirationInSecs());
		// Check for Errors
		if (otp.getError() != null) {
			resp.setSuccess(false);
			resp.setErrorCode(otp.getError().getErrorCode());
			resp.setErrorMessage(otp.getError().getErrorMessage());
		} else {
			resp.setSuccess(true);
		}
		return resp;
	}

	public List<com.pcsinfotech.eoapi.v1.model.IsoCode> listofIsoCodeToListOfIsoCode(
			List<com.pcsinfotech.eoservices.model.IsoCode> isoCodeSMList) {
		// Build the IsoCode Collection for the response object
		List<com.pcsinfotech.eoapi.v1.model.IsoCode> isoCodes = new ArrayList<com.pcsinfotech.eoapi.v1.model.IsoCode>();
		for (com.pcsinfotech.eoservices.model.IsoCode isoCode : isoCodeSMList) {
			com.pcsinfotech.eoapi.v1.model.IsoCode item = new com.pcsinfotech.eoapi.v1.model.IsoCode();
			item.setCountry(isoCode.getCountry());
			item.setIsoCode(isoCode.getIsoCode());
			isoCodes.add(item);
		}
		return isoCodes;
	}

	public VerifyOTPResponse authTokenToVerifyOtpResponse(com.pcsinfotech.eoservices.model.AuthToken authToken) {
		VerifyOTPResponse verifyOTPResponse = new VerifyOTPResponse();

		// Assigning values to verifyOTPResponse based on error
		if (authToken.getError() == null) {
			verifyOTPResponse.setAuthToken(authToken.getAuthToken());
			verifyOTPResponse.setCustomerType(authToken.getCustomerType().getType());
			verifyOTPResponse.setSuccess(true);
			verifyOTPResponse.setErrorCode(null);
			verifyOTPResponse.setErrorMessage(null);
		} else {
			verifyOTPResponse.setAuthToken(null);
			verifyOTPResponse.setCustomerType(null);
			verifyOTPResponse.setSuccess(false);
			verifyOTPResponse.setErrorCode(authToken.getError().getErrorCode());
			verifyOTPResponse.setErrorMessage(authToken.getError().getErrorMessage());
		}

		return verifyOTPResponse;
	}

}
