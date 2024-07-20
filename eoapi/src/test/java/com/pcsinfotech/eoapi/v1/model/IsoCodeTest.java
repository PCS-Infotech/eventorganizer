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
public class IsoCodeTest{
	
	@Mock
	private IsoCode mockIsoCode;
	
	@InjectMocks
	private IsoCode isoCode;
	
	@BeforeEach
	void setup() {
		isoCode = new IsoCode();
	}
	
	@Test
	void testGetCountry() {

        isoCode.setCountry("India");

        String country = isoCode.getCountry();

        assertEquals("India", country);
    }

    @Test
    void testGetIsoCode() {

        isoCode.setIsoCode("IND");

        String isoCodeValue = isoCode.getIsoCode();

        assertEquals("IND", isoCodeValue);
    }

	@Test
    void testSetCountry() {
    	
        String countryValue = "India";

        isoCode.setCountry(countryValue);

        assertEquals(countryValue, isoCode.getCountry());
    }

    @Test
    void testSetIsoCode() {

        String isoCodeValue = "IND";

        isoCode.setIsoCode(isoCodeValue);

        assertEquals(isoCodeValue, isoCode.getIsoCode());
    }
}