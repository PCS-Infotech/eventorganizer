package com.pcsinfotech.eoservices.model;

import java.util.List;

public class OtpCodesList {

	private List<OtpCode> list;

	private ErrorCode error;

	public List<OtpCode> getList() {
		return this.list;
	}

	public ErrorCode getError() {
		return this.error;
	}

	public void setList(List<OtpCode> value) {
		this.list = value;
	}

	public void setError(ErrorCode value) {
		this.error = value;
	}

}
