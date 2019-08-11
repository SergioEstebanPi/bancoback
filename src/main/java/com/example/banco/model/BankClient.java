package com.example.banco.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class BankClient {
	private Map<String, Object> properties = new HashMap<String, Object>();

	@JsonAnyGetter
	public Map<String, Object> getProperties() {
		return this.properties;
	}

	@JsonAnySetter
	public void setProperties(String name, Object value) {
		this.properties.put(name, value);
	}
}
