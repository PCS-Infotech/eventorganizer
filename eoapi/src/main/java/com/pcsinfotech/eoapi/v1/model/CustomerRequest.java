package com.pcsinfotech.eoapi.v1.model;

import java.time.LocalDateTime;

public class CustomerRequest {
	private Long customerid;
	private String lastName;
	private String firstName;	
	private String eventType;
	private String isoCode;
	private String mobile;
	
/*	public CustomerRequest(Long customerid, String eventType, String isoCode,String mobile) {
		super();
		this.customerid = customerid;
		this.eventType = eventType;
		this.isoCode = isoCode;
		this.mobile = mobile;
	}*/
	
	
	public Long getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getIsoCode() {
		return isoCode;
	}
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	 
	 
}
