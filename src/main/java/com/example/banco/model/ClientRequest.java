package com.example.banco.model;

public class ClientRequest {
	private String identification;
	private String firstname;
	private String lastname;
	private String birthdate;

	public ClientRequest() {
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setBirthdate(String birthdate) {
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
