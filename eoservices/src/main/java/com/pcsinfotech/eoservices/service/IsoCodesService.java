package com.pcsinfotech.eoservices.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.pcsinfotech.eoservices.model.IsoCode;
import com.pcsinfotech.eodata.entities.*;
import com.pcsinfotech.eodata.repositories.*;

@Service
public class IsoCodesService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	public List<IsoCode> getIsoCodes() {
		
		List<IsoCode> isoCodes = new ArrayList<IsoCode>();
		
		List<Country> dbIsoCodes = countryRepository.getIsoCodes();
		if (!CollectionUtils.isEmpty(dbIsoCodes)) {
			dbIsoCodes.stream().forEach(t -> {
					IsoCode code = new IsoCode();
					code.setCountry(t.getCountry());
					code.setIsoCode(t.getIsoCode());
					isoCodes.add(code);
			});
		}
		return isoCodes;
	}
	
	
	
	public List<IsoCode> getIsoCodesByCountryAndIsoCode(String country, String isoCode) {
		List<IsoCode> isoCodes = new ArrayList<IsoCode>();
		if (StringUtils.hasText(country) && StringUtils.hasText(isoCode)) { 
			List<Country> dbIsoCodes = countryRepository.findCountriesByCountryAndIsoCode(country, isoCode);
			if (!CollectionUtils.isEmpty(dbIsoCodes)) {
				dbIsoCodes.stream().forEach(t -> {
						IsoCode code = new IsoCode();
						code.setCountry(t.getCountry());
						code.setIsoCode(t.getIsoCode());
						isoCodes.add(code);
				});
			}
		}
		return isoCodes;
	}

}
