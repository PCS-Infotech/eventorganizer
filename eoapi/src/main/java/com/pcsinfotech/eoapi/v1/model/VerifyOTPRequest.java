package com.pcsinfotech.eoapi.v1.model;

public class VerifyOTPRequest {

	private String otpCode;
	private String isoCode;
	private String mobile;

	public String getOtpCode() {
		return this.otpCode;
	}

	public String getIsoCode() {
		return this.isoCode;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setOtpCode(String value) {
		this.otpCode = value;
	}

	public void setIsoCode(String value) {
		this.isoCode = value;
	}

	public void setMobile(String value) {
		this.mobile = value;
	}

}
