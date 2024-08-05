package com.pcsinfotech.eodata.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
		
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", nullable = false)
	 private Long id;
	 
	 @Column(name = "lastname", length = 30, nullable = false)
	 private String lastName;
	 
	 @Column(name = "firstname", length = 30, nullable = false)
	 private String firstName;
	 
	 @Column(name = "mobile", length = 10, nullable = false)
	 private String mobile;
	 
	 @Column(name= "countryid", nullable = false) 
	 private Long countryId;
	 
	 @Column(name= "timezoneId", nullable = false)
	 private Long timeZoneId;
	 
	 
	 @Column(name = "softdeleted")
	 private Integer softDeleted = 0;
	 
	 @Column(name = "updateddatetime") 
	 private LocalDateTime updatedDatetime;
	 
	 @Column(name = "createddatetime", nullable = false, updatable = false)
	 private LocalDateTime createdDatetime;
	 
	 @Column(nullable = false)
	 private Integer versionNumber=1;
	 
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getTimeZoneId() {
		return timeZoneId;
	}

	public void setTimeZoneId(Long timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	public int getSoftDeleted() {
		return this.softDeleted;
	}

	public void setSoftDeleted(Integer s) {
		if ((s == null) || (s == 0))
    	{
    		this.softDeleted = 0;
    	}
    	else
    	{
    		this.softDeleted = 1;
    	}
	}

	public LocalDateTime getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(LocalDateTime updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(LocalDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Integer getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Integer ver) {
		//this.versionNumber = versionNumber;
		
		
		if ((ver == null) || (ver < 1))
    	{
    		this.versionNumber = 1;
    	}
    	else
    	{
    		this.versionNumber = ver;
    	}
	}


	

}
