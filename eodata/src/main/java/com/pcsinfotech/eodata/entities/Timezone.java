package com.pcsinfotech.eodata.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "timezones")
public class Timezone {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;
    
    @Column(name = "offset", nullable = false)
    private String offset;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "softdeleted", nullable = false)
    private  Integer softDeleted = 0;

	@Column(name = "versionnumber", nullable = false)
    private  Integer version = 1;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSoftDeleted() {
		return softDeleted;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer ver) {
		if ((ver == null) || (ver < 1))
    	{
    		this.version = 1;
    	}
    	else
    	{
    		this.version = ver;
    	}
	}
}
