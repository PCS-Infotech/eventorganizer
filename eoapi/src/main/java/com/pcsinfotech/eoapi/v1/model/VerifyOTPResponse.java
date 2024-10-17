package com.pcsinfotech.eoapi.v1.model;

public class VerifyOTPResponse extends Success {

	private String customerType;
	private String authToken;

	public String getCustomerType() {
		return this.customerType;
	}

	public String getAuthToken() {
		return this.authToken;
	}

	public void setCustomerType(String value) {
		this.customerType = value;
	}

	public void setAuthToken(String value) {
		this.authToken = value;
	}

}
