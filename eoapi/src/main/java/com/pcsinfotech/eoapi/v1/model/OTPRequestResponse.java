package com.pcsinfotech.eoapi.v1.model;

public class OTPRequestResponse extends Success {

	private int otpValidTimeoutInSecs;
	
	public int getOtpValidTimeoutInSecs() {
		return this.otpValidTimeoutInSecs;
	}
	
	public void setOtpValidTimeoutInSecs(int seconds) {	
		if (seconds < 0) {
			seconds = 0;
		}
		this.otpValidTimeoutInSecs = seconds;
	}
}
