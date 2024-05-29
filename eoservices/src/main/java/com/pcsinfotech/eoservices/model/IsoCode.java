package com.pcsinfotech.eoservices.model;

public class IsoCode {
	
	private String country;
	private String isoCode;
	
	public String getCountry() {
		return this.country;
	}
	
	public String getIsoCode() {
		return this.isoCode;
	}
	
	public void setCountry(String countryValue) {
		if (countryValue == null || countryValue.isBlank() || countryValue.isEmpty()) {
			this.country = null;
		}
		else {
			this.country = countryValue.toUpperCase();
		}
	}
	
	public void setIsoCode(String isoCodeValue) {
		if (isoCodeValue == null || isoCodeValue.isBlank() || isoCodeValue.isEmpty()) {
			this.isoCode = null;
		}
		else {
			this.isoCode = isoCodeValue;
		}
	}

}
