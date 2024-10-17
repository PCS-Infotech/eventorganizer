package com.pcsinfotech.eodata.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "otpcodes")
public class OtpCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "countryId", nullable = false)
	private Long countryId;

	@Column(name = "mobile", nullable = false)
	private String mobile;

	@Column(name = "otpcode")
	private String otpCode;

	@Column(name = "expirytimeinsecs", nullable = false)
	private Integer expiryTimeInSecs;

	@Column(name = "softdeleted", nullable = false)
	private Integer softDeleted = 0;

	@Column(name = "versionnumber", nullable = false)
	private Integer version = 1;

	@Column(name = "createddatetime", nullable = false)
	private Timestamp createddatetime;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Long c) {
		this.countryId = c;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String c) {
		this.mobile = c;
	}

	public String getOtpCode() {
		return this.otpCode;
	}

	public void setOtpCode(String c) {
		this.otpCode = c;
	}

	public Integer getExpiryTimeInSecs() {
		return this.expiryTimeInSecs;
	}

	public void setExpiryTimeInSecs(Integer c) {
		if (c == null) {
			this.expiryTimeInSecs = 0;
		} else {
			this.expiryTimeInSecs = c;
		}
	}

	public int getSoftDeleted() {
		return this.softDeleted;
	}

	public void setSoftDeleted(Integer s) {
		if ((s == null) || (s == 0)) {
			this.softDeleted = 0;
		} else {
			this.softDeleted = 1;
		}
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer ver) {
		if ((ver == null) || (ver < 1)) {
			this.version = 1;
		} else {
			this.version = ver;
		}
	}

	public Timestamp getCreatedDateTime() {
		return this.createddatetime;
	}

	public void setCreatedDateTime(Timestamp c) {
		this.createddatetime = c;
	}
}
