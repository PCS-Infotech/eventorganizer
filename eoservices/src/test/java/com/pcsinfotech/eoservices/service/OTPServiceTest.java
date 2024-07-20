package com.pcsinfotech.eoservices.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pcsinfotech.eodata.entities.OtpCode;
import com.pcsinfotech.eodata.repositories.OtpCodeRepository;
import com.pcsinfotech.eoservices.model.ErrorCode;
import com.pcsinfotech.eoservices.model.IsoCodesList;
import com.pcsinfotech.eoservices.model.OTP;
import com.pcsinfotech.eoservices.service.IsoCodesService;
import com.pcsinfotech.eoservices.service.OTPService;

public class OTPServiceTest {

    @Mock
    private IsoCodesService isoCodesService;

    @Mock
    private OtpCodeRepository otpCodeRepo;

    @InjectMocks
    private OTPService otpService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendOTP_InvalidMobileNumber() {
        OTP otp = otpService.sendOTP("INDIA", "+91", "invalid");
        assertEquals(ErrorCode.PCS_1, otp.getError());
        assertEquals(0, otp.getOtpExpirationInSecs());
    }
    
    @Test
    public void testSendOTP_NullMobileNumber() {
        OTP otp = otpService.sendOTP("INDIA", "+91", null);
        assertEquals(ErrorCode.PCS_1, otp.getError());
        assertEquals(0, otp.getOtpExpirationInSecs());
    }

    @Test
    public void testSendOTP_EmptyMobileNumber() {
        OTP otp = otpService.sendOTP("INDIA", "+91", "");
        assertEquals(ErrorCode.PCS_1, otp.getError());
        assertEquals(0, otp.getOtpExpirationInSecs());
    }

    @Test
    public void testSendOTP_ShortMobileNumber() {
        OTP otp = otpService.sendOTP("INDIA", "+91", "12345");
        assertEquals(ErrorCode.PCS_1, otp.getError());
        assertEquals(0, otp.getOtpExpirationInSecs());
    }

    @Test
    public void testSendOTP_LongMobileNumber() {
        OTP otp = otpService.sendOTP("INDIA", "+91", "12345678901");
        assertEquals(ErrorCode.PCS_1, otp.getError());
        assertEquals(0, otp.getOtpExpirationInSecs());
    }

    @Test
    public void testSendOTP_NonDigitMobileNumber() {
        OTP otp = otpService.sendOTP("INDIA", "+91", "12345abcde");
        assertEquals(ErrorCode.PCS_1, otp.getError());
        assertEquals(0, otp.getOtpExpirationInSecs());
    }

    @Test
    public void testSendOTP_ValidRequest() {
        
        IsoCodesList isoCodesList = new IsoCodesList();
        isoCodesList.setList(Collections.singletonList(new com.pcsinfotech.eoservices.model.IsoCode()));
        when(isoCodesService.getIsoCodesByCountryAndIsoCode(anyString(), anyString())).thenReturn(isoCodesList);

        
        OTP otp = otpService.sendOTP("INDIA", "+91", "1234567890");

        
        assertNull(otp.getError());
        assertNotNull(otp.getOtp());
        assertEquals(60, otp.getOtpExpirationInSecs());
    }

    @Test
    public void testSendOTP_IsoCodeNotFound() {
        
        IsoCodesList isoCodesList = new IsoCodesList();
        isoCodesList.setList(Collections.emptyList());
        isoCodesList.setError(ErrorCode.PCS_13);
        when(isoCodesService.getIsoCodesByCountryAndIsoCode(anyString(), anyString())).thenReturn(isoCodesList);

        
        OTP otp = otpService.sendOTP("INDIA", "+91", "1234567890");

        
        assertNotNull(otp.getError(), "Error should not be null when ISO code is not found");
        assertEquals(ErrorCode.PCS_13, otp.getError());
        assertEquals(0, otp.getOtpExpirationInSecs());
    }

    @Test
    public void testSendOTP_DatabaseException() {
        
        IsoCodesList isoCodesList = new IsoCodesList();
        isoCodesList.setList(Collections.singletonList(new com.pcsinfotech.eoservices.model.IsoCode()));
        when(isoCodesService.getIsoCodesByCountryAndIsoCode(anyString(), anyString())).thenReturn(isoCodesList);
        
        when(otpCodeRepo.save(any(OtpCode.class))).thenThrow(new RuntimeException());

        OTP otp = otpService.sendOTP("INDIA", "+91", "1234567890");

        assertEquals(ErrorCode.PCS_3, otp.getError());
        assertEquals(0, otp.getOtpExpirationInSecs());
    }
}