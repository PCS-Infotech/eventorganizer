package com.pcsinfotech.eoservices.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pcsinfotech.eoservices.model.ErrorCode;
import com.pcsinfotech.eoservices.model.IsoCode;
import com.pcsinfotech.eoservices.model.IsoCodesList;
import com.pcsinfotech.eodata.entities.Country;
import com.pcsinfotech.eodata.repositories.CountryRepository;

public class IsoCodesServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private IsoCodesService isoCodesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetIsoCodes() {
        Country country1 = new Country();
        country1.setId(1L);
        country1.setCountry("USA");
        country1.setIsoCode("US");

        Country country2 = new Country();
        country2.setId(2L);
        country2.setCountry("INDIA");
        country2.setIsoCode("IND");

        when(countryRepository.getIsoCodes()).thenReturn(Arrays.asList(country1, country2));

        List<IsoCode> isoCodes = isoCodesService.getIsoCodes();

        assertNotNull(isoCodes);
        assertEquals(2, isoCodes.size());
        assertEquals("USA", isoCodes.get(0).getCountry());
        assertEquals("US", isoCodes.get(0).getIsoCode());
        assertEquals("INDIA", isoCodes.get(1).getCountry());
        assertEquals("IND", isoCodes.get(1).getIsoCode());
    }

    @Test
    public void testGetIsoCodesByCountryAndIsoCode() {
        Country country = new Country();
        country.setId(1L);
        country.setCountry("USA");
        country.setIsoCode("US");

        when(countryRepository.findCountriesByCountryAndIsoCode("USA", "US")).thenReturn(Collections.singletonList(country));

        IsoCodesList isoCodesList = isoCodesService.getIsoCodesByCountryAndIsoCode("USA", "US");

        assertNotNull(isoCodesList);
        assertNull(isoCodesList.getError());
        assertEquals(1, isoCodesList.getList().size());
        assertEquals("USA", isoCodesList.getList().get(0).getCountry());
        assertEquals("US", isoCodesList.getList().get(0).getIsoCode());
    }

    @Test
    public void testGetIsoCodesByCountryAndIsoCode_withEmptyCountry() {
        IsoCodesList isoCodesList = isoCodesService.getIsoCodesByCountryAndIsoCode("", "US");

        assertNotNull(isoCodesList);
        assertEquals(ErrorCode.PCS_11, isoCodesList.getError());
    }

    @Test
    public void testGetIsoCodesByCountryAndIsoCode_withEmptyIsoCode() {
        IsoCodesList isoCodesList = isoCodesService.getIsoCodesByCountryAndIsoCode("USA", "");

        assertNotNull(isoCodesList);
        assertEquals(ErrorCode.PCS_12, isoCodesList.getError());
    }

    @Test
    public void testGetIsoCodesByCountryAndIsoCode_withNoMatches() {
        when(countryRepository.findCountriesByCountryAndIsoCode("USA", "US")).thenReturn(Collections.emptyList());

        IsoCodesList isoCodesList = isoCodesService.getIsoCodesByCountryAndIsoCode("USA", "US");

        assertNotNull(isoCodesList);
        assertEquals(ErrorCode.PCS_13, isoCodesList.getError());
    }
}




