package com.pcsinfotech.eoapi.v1.controller;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pcsinfotech.eoapi.v1.Mapping.ServiceToControllerMapper;
import com.pcsinfotech.eoapi.v1.controller.OTPController;
import com.pcsinfotech.eoapi.v1.model.OTPRequest;
import com.pcsinfotech.eoapi.v1.model.OTPRequestResponse;
import com.pcsinfotech.eoservices.model.OTP;
import com.pcsinfotech.eoservices.service.OTPService;

public class OTPControllerTest {

    @Mock
    private OTPService otpService;

    @Mock
    private ServiceToControllerMapper mapper;

    @InjectMocks
    private OTPController otpController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRequestOTP() {
        
        OTPRequest otpRequest = new OTPRequest();
        otpRequest.setCountry("INDIA");
        otpRequest.setIsoCode("+91");
        otpRequest.setMobile("1234567890");

        
        OTP otp = new OTP();
        otp.setOtp("12345");
        otp.setOtpExpirationInSecs(60);

        OTPRequestResponse otpRequestResponse = new OTPRequestResponse();
        otpRequestResponse.setOtpValidTimeoutInSecs(60);

        when(otpService.sendOTP(any(String.class), any(String.class), any(String.class))).thenReturn(otp);
        when(mapper.otpToOTPRequestResponse(any(OTP.class))).thenReturn(otpRequestResponse);

        OTPRequestResponse response = otpController.requestOTP(otpRequest);

        assertEquals(otpRequestResponse, response);
    }
}
