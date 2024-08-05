package com.pcsinfotech.eoservices.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.util.StringUtils;

public class Customer  {
	
	private Long customerId;
	private String firstName;
	private String lastName;
	private String mobile;
	private String isoCode;
	private String eventType;
	private Long countryId;
	
	
   private long timeZone;
	
	public long getTimeZone() {
	return timeZone;
}

public void setTimeZone(long timeZone) {
	this.timeZone = timeZone;
}

	public Long getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	private ErrorCode error;
	
	public ErrorCode getError() {
		return this.error;
	}

	public void setError(ErrorCode error) {
		this.error = error;
	}

	public Long getCustomerId() {
		return this.customerId;
	}
	
	public void setCustomerId(Long id) {
		if (null != id && id > 0) {
			this.customerId = id;
		}
		else {
			this.customerId = null;
		}
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		if (StringUtils.hasText(firstName)) {
			this.firstName = firstName;
		}	
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		if (StringUtils.hasText(lastName)) {
			this.lastName = lastName;
		}
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile= mobile;
	}

	public String getIsoCode() {
		return this.isoCode;
	}

	public void setIsoCode(String isoCode) {
		if (StringUtils.hasText(isoCode)) {
			this.isoCode = isoCode;
		}
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		if (StringUtils.hasText(eventType)) {
			this.eventType = eventType;
		}
	}

	
}
