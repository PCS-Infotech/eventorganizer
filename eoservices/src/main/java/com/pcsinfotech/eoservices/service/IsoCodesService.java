package com.pcsinfotech.eoservices.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.pcsinfotech.eoservices.model.IsoCode;
import com.pcsinfotech.eodata.entities.*;
import com.pcsinfotech.eodata.repositories.*;

@Service
public class IsoCodesService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	public List<IsoCode> getActiveIsoCodes() {
		
		List<IsoCode> isoCodes = new ArrayList<IsoCode>();
		
		List<Country> dbIsoCodes = countryRepository.getIsoCodes();
		if (!CollectionUtils.isEmpty(dbIsoCodes)) {
			dbIsoCodes.stream().forEach(t -> {
				if (t.getStatus() == CountryStatus.ACTIVE) {
					IsoCode code = new IsoCode();
					code.setCountry(t.getCountry());
					code.setIsoCode(t.getIsoCode());
					isoCodes.add(code);
				}
				else {
					//Do Nothing
				}
			});
		}
		else {
			//Do Nothing
		}
	
		return isoCodes;
	}

}
