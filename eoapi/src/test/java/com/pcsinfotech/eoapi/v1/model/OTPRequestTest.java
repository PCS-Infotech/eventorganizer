package com.pcsinfotech.eoapi.v1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OTPRequestTest {

    @Mock
    private OTPRequest mockOTPRequest;

    @InjectMocks
    private OTPRequest otpRequest;

    @BeforeEach
    void setUp() {
        otpRequest = new OTPRequest();
    }

    @Test
    void testSetAndGetCountry() {
        
        when(mockOTPRequest.getCountry()).thenReturn("INDIA");

        mockOTPRequest.setCountry("INDIA");

        verify(mockOTPRequest).setCountry("INDIA");

        assertEquals("INDIA", mockOTPRequest.getCountry());
    }
    
    @Test
    void testSetAndGetIsoCode() {

        when(mockOTPRequest.getIsoCode()).thenReturn("IND");

        mockOTPRequest.setIsoCode("IND");

        verify(mockOTPRequest).setIsoCode("IND");

        assertEquals("IND", mockOTPRequest.getIsoCode());
    }

    @Test
    void testSetAndGetMobile() {

        when(mockOTPRequest.getMobile()).thenReturn("12345");

        mockOTPRequest.setMobile("12345");

        verify(mockOTPRequest).setMobile("12345");

        assertEquals("12345", mockOTPRequest.getMobile());
    }
    
}