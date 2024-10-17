package com.pcsinfotech.eoservices.model;

public enum CustomerType {

	NEW("NEW", "New Customer"), 
	INVITEE("INVITEE", "Invitee only"), 
	ORGANIZER("ORGANIZER", "Organizer only"),
	BOTH("BOTH", "Invitee and Organizer");

	private String type;

	private String description;

	private CustomerType(String type, String description) {
		this.type = type;
		this.description = description;
	}

	public String getType() {
		return this.type;
	}

	public String getDescription() {
		return this.description;
	}
}
