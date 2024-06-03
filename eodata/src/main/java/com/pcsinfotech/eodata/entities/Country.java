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
@Table(name = "countries")
public class Country {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "country", length = 30, nullable = false)
    private String country;
	
    @Column(name = "isocode", length = 5, nullable = false)
	private String isoCode;
    
    @Column(name = "softdeleted", nullable = false)
    private  Integer softDeleted;
    
    @Column(name = "versionnumber", nullable = false)
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
    
    public int getSoftDeleted() {
    	return this.softDeleted;
    }
    
    public void setSoftDeleted(int s) {
    	if (s == 0)
    	{
    		this.softDeleted = 0;
    	}
    	else
    	{
    		this.softDeleted = 1;
    	}
    }
    
    public Integer getVersion() {
    	return this.version;
    }
    
    public void setVersion(Integer ver) {
    	this.version = ver;
    }
}
