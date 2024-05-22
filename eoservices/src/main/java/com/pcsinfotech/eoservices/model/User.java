package com.pcsinfotech.eoservices.model;

public class User {
	
	private String firstName = "";
	private String lastName = "";
	
	public void setFirstName(String first) {
		this.firstName = first;
	}
	
	public void setLastName(String last) {
		this.lastName = last;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}

}
