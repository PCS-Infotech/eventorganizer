package com.pcsinfotech.eoservices.model;

public class OtpCode {

	private Long otpCodeId;

	private String mobile;

	private String otpCode;

	private Long countryId;

	private int expiryTime;

	public Long getOtpCodeId() {
		return this.otpCodeId;
	}

	public String getMobile() {
		return this.mobile;
	}

	public String getOtpCode() {
		return this.otpCode;
	}

	public Long getCountryId() {
		return this.countryId;
	}

	public int getExpiryTime() {
		return this.expiryTime;
	}

	public void setOtpCodeId(Long value) {
		this.otpCodeId = value;
	}

	public void setMobile(String value) {
		this.mobile = value;
	}

	public void setOtpCode(String value) {
		this.otpCode = value;
	}

	public void setCountryId(Long value) {
		this.countryId = value;
	}

	public void setExpiryTime(int value) {
		this.expiryTime = value;
	}

}
