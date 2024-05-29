package com.pcsinfotech.eodata.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "countries_catalog")
public class Country {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "country", length = 30, nullable = false)
    private String country;
	
    @Column(name = "iso_code", length = 5, nullable = false)
	private String isoCode;
    
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
	private  CountryStatus status;
    
    @Column(name = "version_number", nullable = false)
    private  Integer version;
    
    public Integer getId() {
    	return this.id;
    }
    
    public void setId(Integer id) {
    	this.id = id;
    }
    
    public String getCountry() {
    	return this.country;
    }
    
    public void setCountry(String c) {
    	this.country = c;
    }
    
    public String getIsoCode() {
    	return this.isoCode;
    }
    
    public void setIsoCode(String c) {
    	this.isoCode = c;
    }
    
    public CountryStatus getStatus() {
    	return this.status;
    }
    
    public void setStatus(CountryStatus s) {
    	this.status = s;
    }
    
    public Integer getVersion() {
    	return this.version;
    }
    
    public void setVersion(Integer ver) {
    	this.version = ver;
    }
}
