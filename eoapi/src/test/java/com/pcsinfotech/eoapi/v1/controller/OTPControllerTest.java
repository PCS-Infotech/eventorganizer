package com.pcsinfotech.eoapi.v1.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.pcsinfotech.eoapi.v1.model.VerifyOTPRequest;
import com.pcsinfotech.eoapi.v1.model.VerifyOTPResponse;
import com.pcsinfotech.eoservices.model.AuthToken;
import com.pcsinfotech.eoservices.service.OTPService;

class OTPControllerTest {

	@InjectMocks
	private OTPController otpController;

	@Mock
	private VerifyOTPRequest verifyOTPRequest;

	@Mock
	private VerifyOTPResponse verifyOTPResponse;

	@Mock
	private AuthToken authToken;

	@Mock
	private OTPService otpService;

	@Test
	void test1() {
		VerifyOTPRequest verifyOTPRequest = new VerifyOTPRequest();
		VerifyOTPResponse verifyOTPResponse = new VerifyOTPResponse();
		verifyOTPRequest.setIsoCode("+91");
		verifyOTPRequest.setMobile("9490013391");
		verifyOTPRequest.setOtpCode("789012");

		AuthToken authToken = new AuthToken();
		authToken.setAuthToken("ABCDEFG");
		OTPService otpService = new OTPService();
		when(otpService.verifyOtp("789012", "+91", "9490013391")).thenReturn(authToken);

		// verifyOTPResponse = otpController.verifyOTP(verifyOTPRequest);

	}

}
