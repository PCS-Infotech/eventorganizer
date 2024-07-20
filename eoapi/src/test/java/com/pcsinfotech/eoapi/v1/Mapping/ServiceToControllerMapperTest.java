package com.pcsinfotech.eoapi.v1.Mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.pcsinfotech.eoapi.v1.model.IsoCode;
import com.pcsinfotech.eoapi.v1.model.OTPRequestResponse;
import com.pcsinfotech.eoservices.model.ErrorCode;
import com.pcsinfotech.eoservices.model.OTP;

public class ServiceToControllerMapperTest {

    @InjectMocks
    private ServiceToControllerMapper mapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testOtpToOTPRequestResponse_Success() {
        OTP otp = new OTP();
        otp.setOtpExpirationInSecs(300);
        otp.setError(null); 

        OTPRequestResponse response = mapper.otpToOTPRequestResponse(otp);

        assertTrue(response.getSuccess());
        assertEquals(300, response.getOtpValidTimeoutInSecs());
        assertEquals(null, response.getErrorCode()); 
        assertEquals(null, response.getErrorMessage()); 
    }

    @Test
    public void testOtpToOTPRequestResponse_Error() {
        OTP otp = new OTP();
        otp.setOtpExpirationInSecs(300);
        otp.setError(ErrorCode.PCS_1);

        OTPRequestResponse response = mapper.otpToOTPRequestResponse(otp);

        assertFalse(response.getSuccess());
        assertEquals(ErrorCode.PCS_1.getErrorCode(), response.getErrorCode());
        assertEquals(ErrorCode.PCS_1.getErrorMessage(), response.getErrorMessage());
    }
    
    @Test
    public void testListofIsoCodeToListOfIsoCode() {
        
        com.pcsinfotech.eoservices.model.IsoCode isoCode1 = new com.pcsinfotech.eoservices.model.IsoCode();
        isoCode1.setCountry("INDIA");  
        isoCode1.setIsoCode("+91");

        com.pcsinfotech.eoservices.model.IsoCode isoCode2 = new com.pcsinfotech.eoservices.model.IsoCode();
        isoCode2.setCountry("USA"); 
        isoCode2.setIsoCode("+1");

        List<com.pcsinfotech.eoservices.model.IsoCode> isoCodeSMList = Arrays.asList(isoCode1, isoCode2);

        List<IsoCode> isoCodes = mapper.listofIsoCodeToListOfIsoCode(isoCodeSMList);
 
        assertEquals("INDIA", isoCodes.get(0).getCountry());
        assertEquals("+91", isoCodes.get(0).getIsoCode());
        assertEquals("USA", isoCodes.get(1).getCountry());
        assertEquals("+1", isoCodes.get(1).getIsoCode());
    }
}