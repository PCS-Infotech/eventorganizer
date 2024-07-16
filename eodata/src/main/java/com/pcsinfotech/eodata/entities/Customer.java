package com.pcsinfotech.eodata.entities;

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

	@Column(name = "lastname", length = 50, nullable = false)
	private String lastName;

	@Column(name = "firstname", length = 50, nullable = false)
	private String firstName;

	@Column(name = "mobile", length = 10, nullable = false)
	private String mobile;

	@Column(name = "countryid", nullable = false)
	private Long countryId;

	@Column(name = "softdeleted", nullable = false)
	private Integer softDeleted = 0;

	public Long getId() {
		return this.id;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public Long getCountryId() {
		return this.countryId;
	}

	public int getSoftDeleted() {
		return this.softDeleted;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastName(String c) {
		this.lastName = c;
	}

	public void setFirstName(String c) {
		this.firstName = c;
	}

	public void setMobile(String c) {
		this.mobile = c;
	}

	public void setCountryId(Long c) {
		this.countryId = c;
	}

	public void setSoftDeleted(int c) {
		this.softDeleted = c;
	}

}
