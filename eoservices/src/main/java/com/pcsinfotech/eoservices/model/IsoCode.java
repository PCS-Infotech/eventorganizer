package com.pcsinfotech.eoservices.model;

import org.springframework.util.StringUtils;

public class IsoCode {
	
	private Long countryDBId;
	private String country;
	private String isoCode;
	
	public Long getCountryDBId() {
		return this.countryDBId;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public String getIsoCode() {
		return this.isoCode;
	}
	
	public void setCountryDBId(Long value) {
		this.countryDBId = value;
	}
	
	public void setCountry(String countryValue) {
		if (StringUtils.hasText(countryValue)) {
			this.country = countryValue.toUpperCase();
		}
		else {
			this.country = null;
		}
	}
	
	public void setIsoCode(String isoCodeValue) {
		
		if (StringUtils.hasText(isoCodeValue)) {
			this.isoCode = isoCodeValue;
		}
		else {
			this.isoCode = null;
		}
	}

}
