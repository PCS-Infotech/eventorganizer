package com.pcsinfotech.eoapi.v1.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pcsinfotech.eoapi.v1.Mapping.ServiceToControllerMapper;
import com.pcsinfotech.eoapi.v1.model.IsoCodesResponse;
import com.pcsinfotech.eoservices.model.IsoCode;
import com.pcsinfotech.eoservices.service.IsoCodesService;

public class IsoCodesControllerTest {

    @Mock
    private IsoCodesService isoCodesService;

    @Mock
    private ServiceToControllerMapper mapper;

    @InjectMocks
    private IsoCodesController isoCodesController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetIsoCodes() {
        
        IsoCode isoCode1 = new IsoCode();
        isoCode1.setCountryDBId(1L);
        isoCode1.setCountry("USA");
        isoCode1.setIsoCode("US");

        IsoCode isoCode2 = new IsoCode();
        isoCode2.setCountryDBId(2L);
        isoCode2.setCountry("INDIA");
        isoCode2.setIsoCode("IND");

        List<IsoCode> isoCodesSM = Arrays.asList(isoCode1, isoCode2);

        com.pcsinfotech.eoapi.v1.model.IsoCode apiIsoCode1 = new com.pcsinfotech.eoapi.v1.model.IsoCode();
        apiIsoCode1.setCountry("USA");
        apiIsoCode1.setIsoCode("US");

        com.pcsinfotech.eoapi.v1.model.IsoCode apiIsoCode2 = new com.pcsinfotech.eoapi.v1.model.IsoCode();
        apiIsoCode2.setCountry("INDIA");
        apiIsoCode2.setIsoCode("IND");

        List<com.pcsinfotech.eoapi.v1.model.IsoCode> isoCodes = Arrays.asList(apiIsoCode1, apiIsoCode2);

        
        when(isoCodesService.getIsoCodes()).thenReturn(isoCodesSM);
        when(mapper.listofIsoCodeToListOfIsoCode(isoCodesSM)).thenReturn(isoCodes);

        
        IsoCodesResponse response = isoCodesController.getIsoCodes();

        
        assertNotNull(response);
        assertEquals(2, response.getIsoCodes().size());
        assertEquals("USA", response.getIsoCodes().get(0).getCountry());
        assertEquals("US", response.getIsoCodes().get(0).getIsoCode());
        assertEquals("INDIA", response.getIsoCodes().get(1).getCountry());
        assertEquals("IND", response.getIsoCodes().get(1).getIsoCode());
    }
}

