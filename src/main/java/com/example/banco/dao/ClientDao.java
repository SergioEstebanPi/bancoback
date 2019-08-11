package com.example.banco.dao;

import com.example.banco.model.ClientRequest;
import com.example.banco.model.ClientResponse;

public interface ClientDao {
	ClientResponse insertClient(ClientRequest client);

	ClientResponse selectClientByIdentification(String id);
}
