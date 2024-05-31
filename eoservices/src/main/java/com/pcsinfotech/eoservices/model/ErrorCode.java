package com.pcsinfotech.eoservices.model;

public enum ErrorCode{
	
	PCS_0("PCS-0", "Error"),
	PCS_1("PCS-1", "Mobile number is invalid"),
	PCS_2("PCS-2", "Mobile number is invalid in country"),
	PCS_3("PCS-3", "Unable to send the OTP at this time"),
	PCS_4("PCS-4", "Entered OTP is not valid for the submitted mobile"),
	PCS_5("PCS-5", "Entered OTP is not valid as it expired for the submitted mobile"),
	PCS_6("PCS-6", "Unable to send OTP now as previous OTP for this mobile number was sent with in last 60 seconds"),
	PCS_7("PCS-7", "Last Name is required"),
	PCS_8("PCS-8", "First Name is required"),
	PCS_9("PCS-9", "Invalid Auth Token"),
	PCS_10("PCS-10", "Auth Token is Mandatory"),
	PCS_11("PCS_11", "Country is required"),
	PCS_12("PCS_12", "ISO Code is required"),
	PCS_13("PCS_13", "ISO Code does not exist in the Country");

	private String errorCode;
	
	private String errorMessage;
	
	private ErrorCode(String code, String message) {
		this.errorCode = code;
		this.errorMessage = message;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
}
