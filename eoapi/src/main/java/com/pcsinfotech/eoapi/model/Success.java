package com.pcsinfotech.eoapi.model;

public class Success {
	
	private boolean success = false;
	private String errorCode = null;
	private String errorMessage = null;
	
	public boolean getSuccess() {
		return this.success;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
	
	public void setSuccess(boolean successValue) {
		this.success = successValue;
	}
	
	public void setErrorCode(String errorCodeValue) {
		this.errorCode = errorCodeValue;
	}
	
	public void setErrorMessage(String errorMessageValue) {
		this.errorMessage = errorMessageValue;
	}
	
	
	
}
