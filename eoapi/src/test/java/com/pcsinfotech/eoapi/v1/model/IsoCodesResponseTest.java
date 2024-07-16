package com.pcsinfotech.eoapi.v1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IsoCodesResponseTest {

    @Mock
    private List<IsoCode> mockIsoCodes;

    @InjectMocks
    private IsoCodesResponse isoCodesResponse;

    @BeforeEach
    void setUp() {
        isoCodesResponse = new IsoCodesResponse();
    }

    @Test
    void testSetAndGetIsoCodes() {

        List<IsoCode> isoCodesValues = new ArrayList<>();
        IsoCode isoCode1 = new IsoCode();
        isoCode1.setCountry("India");
        isoCode1.setIsoCode("IND");

        IsoCode isoCode2 = new IsoCode();
        isoCode2.setCountry("USA");
        isoCode2.setIsoCode("USA");

        isoCodesValues.add(isoCode1);
        isoCodesValues.add(isoCode2);

        isoCodesResponse.setIsoCodes(isoCodesValues);

        assertEquals(isoCodesValues, isoCodesResponse.getIsoCodes());
    }
}