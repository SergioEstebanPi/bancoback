package com.example.banco.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Client {
	@JsonProperty
	private final UUID id;
	@JsonProperty
	@NotBlank
	private final String identification;
	@JsonProperty
	@NotBlank
	private final String firstname;
	@JsonProperty
	@NotBlank
	private final String lastname;
	@JsonProperty
	@NotBlank
	private final String birthdate;

	public Client(UUID id, String identification, String firstname, String lastname, String birthdate) {
		this.id = id;
		this.identification = identification;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
	}

	public UUID getId() {
		return id;
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

}
