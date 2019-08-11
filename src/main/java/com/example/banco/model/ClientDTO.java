package com.example.banco.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientDTO {
	@JsonProperty
	private final String identification;
	@JsonProperty
	private final String firstname;
	@JsonProperty
	private final String lastname;
	@JsonProperty
	private final String birthdate;

	public ClientDTO(String identification, String firstname, String lastname, String birthdate) {
		this.identification = identification;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
	}

	public String getIdentification() {
		return identification;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getBirthdate() {
		return birthdate;
	}

	@Override
	public String toString() {
		return "Client: { identification : " + identification + ", firstname: " + firstname + ", lastname: " + lastname
				+ ", birthdate: " + birthdate + "}";
	}

}
