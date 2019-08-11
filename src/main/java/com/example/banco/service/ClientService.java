package com.example.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banco.dao.ClientDao;
import com.example.banco.model.ClientRequest;
import com.example.banco.model.ClientResponse;

@Service
public class ClientService {

	@Autowired
	private final ClientDao clientDao;

	public ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public ClientResponse addClient(ClientRequest clientRequest) {
		return clientDao.insertClient(clientRequest);
	}

	public ClientResponse getClientByIdentification(String id) {
		return clientDao.selectClientByIdentification(id);
	}
}
