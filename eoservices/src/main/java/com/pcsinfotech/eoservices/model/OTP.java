package com.pcsinfotech.eoservices.model;

public class OTP {
	
	private ErrorCode error;
	
	private String otp;
	
	private int otpExpirationInSecs;
	
	public ErrorCode getError() {
		return error;
	}
	
	public String getOtp() {
		return this.otp;
	}
	
	public int getOtpExpirationInSecs() {
		return this.otpExpirationInSecs;
	}
	
	public void setError(ErrorCode value) {
		this.error = value;
	}
	
	public void setOtp(String value) {
		this.otp = value;
	}
	
	public void setOtpExpirationInSecs(int value) {
		if (value >= 0) {
			this.otpExpirationInSecs = value;
		}
		else {
			this.otpExpirationInSecs = 0;
		}
	}
}
