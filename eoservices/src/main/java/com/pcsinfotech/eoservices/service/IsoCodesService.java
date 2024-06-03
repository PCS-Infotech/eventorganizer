package com.pcsinfotech.eoservices.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.pcsinfotech.eoservices.model.ErrorCode;
import com.pcsinfotech.eoservices.model.IsoCode;
import com.pcsinfotech.eoservices.model.IsoCodesList;
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
	
	public IsoCodesList getIsoCodesByCountryAndIsoCode(String country, String isoCode) {	
		IsoCodesList isoCodesList = new IsoCodesList();
		List<IsoCode> isoCodes = new ArrayList<IsoCode>();
		isoCodesList.setList(isoCodes);
		
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
			else {
				isoCodesList.setError(ErrorCode.PCS_13);
				return isoCodesList;
			}
		}
		else {
			//Validate country in request is populated. 
			if (!StringUtils.hasText(country)) {
				isoCodesList.setError(ErrorCode.PCS_11);
				return isoCodesList;
			}
			
			//Validate ISO Code in request is populated
			if (!StringUtils.hasText(isoCode)) {
				isoCodesList.setError(ErrorCode.PCS_12);
				return isoCodesList;
			}
		}
		return isoCodesList;
	}
}
