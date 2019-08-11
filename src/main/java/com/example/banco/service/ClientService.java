package com.example.banco.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banco.dao.ClientDao;
import com.example.banco.model.Client;

@Service
public class ClientService {

	@Autowired
	private final ClientDao clientDao;

	public ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public int addClient(Client client) {
		return clientDao.insertClient(client);
	}

	public List<Client> getAllClients() {
		return clientDao.selectAllClients();
	}

	public Optional<Client> getClientById(UUID id) {
		return clientDao.selectClientById(id);
	}

	public int deleteClient(UUID id) {
		return clientDao.deleteClientById(id);
	}

	public int updatePerson(UUID id, Client client) {
		return clientDao.updateClientById(id, client);
	}
}
