package com.pcsinfotech.eoservices.model;

public class AuthToken {

	private ErrorCode error;

	private CustomerType customerType;

	private String authToken;

	public ErrorCode getError() {
		return this.error;
	}

	public CustomerType getCustomerType() {
		return this.customerType;
	}

	public String getAuthToken() {
		return this.authToken;
	}

	public void setError(ErrorCode error) {
		this.error = error;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
}
