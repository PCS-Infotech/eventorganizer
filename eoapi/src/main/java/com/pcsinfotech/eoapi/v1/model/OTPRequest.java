package com.pcsinfotech.eoapi.v1.model;

public class OTPRequest {
	
	private String country;
	private String isoCode;
	private String mobile;
	
	public String getCountry() {
		return this.country;
	}
	
	public String getIsoCode() {
		return this.isoCode;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	
	public void setCountry(String countryValue) {
		this.country = countryValue;
	}
	
	public void setIsoCode(String isoCodeValue) {
		this.isoCode = isoCodeValue;
	}
	
	public void setMobile(String value) {
		this.mobile = value;
	}
}
