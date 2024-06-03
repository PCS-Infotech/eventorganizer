package com.pcsinfotech.eoservices.model;

import java.util.List;

public class IsoCodesList {
	
	private List<IsoCode> list;
	
	private ErrorCode error;
	
	public List<IsoCode> getList() {
		return this.list;
	}
	
	public ErrorCode getError() {
		return this.error;
	}
	
	public void setList(List<IsoCode> value) {
		this.list = value;
	}
	
	public void setError(ErrorCode value) {
		this.error = value;
	}
}
