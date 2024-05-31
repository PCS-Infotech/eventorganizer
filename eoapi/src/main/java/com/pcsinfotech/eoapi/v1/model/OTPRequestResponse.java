package com.pcsinfotech.eoapi.v1.model;

public class OTPRequestResponse extends Success {

	private Integer otpValidTimeoutInSecs;
	
	public Integer getOtpValidTimeoutInSecs() {
		return this.otpValidTimeoutInSecs;
	}
	
	public void setOtpValidTimeoutInSecs(Integer seconds) {	
		if (seconds != null && seconds < 0) {
			seconds = 0;
		}
		this.otpValidTimeoutInSecs = seconds;
	}
}
