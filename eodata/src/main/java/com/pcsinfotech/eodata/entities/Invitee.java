package com.pcsinfotech.eodata.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "invitees")
public class Invitee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "eventid", nullable = false)
	private Long eventId;

	@Column(name = "customerid", nullable = false)
	private Long customerId;

	@Column(name = "softdeleted", nullable = false)
	private int softDeleted;

	public Long getId() {
		return this.id;
	}

	public Long getEventId() {
		return this.eventId;
	}

	public Long getCustomerId() {
		return this.customerId;
	}

	public int getSoftDeleted() {
		return this.softDeleted;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEventId(Long c) {
		this.eventId = c;
	}

	public void setCustomerId(Long c) {
		this.customerId = c;
	}

	public void setSoftDeleted(int c) {
		this.softDeleted = c;
	}

}
