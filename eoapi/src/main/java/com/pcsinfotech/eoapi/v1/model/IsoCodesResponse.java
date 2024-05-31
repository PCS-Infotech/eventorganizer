package com.pcsinfotech.eoapi.v1.model;

import java.util.List;

public class IsoCodesResponse extends Success {
	
	private List<IsoCode> isoCodes;
	
	
	public List<IsoCode> getIsoCodes() {
		return this.isoCodes;
	}
	
	public void setIsoCodes(List<IsoCode> isoCodesValues) {
		this.isoCodes = isoCodesValues;
	}

}
