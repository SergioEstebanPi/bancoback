package com.example.banco.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.banco.model.Client;

@Repository
public class FakeDaoService implements ClientDao {

	private static List<Client> DB = new ArrayList<Client>();

	@Override
	public int insertClient(UUID id, Client client) {
		DB.add(new Client(id, client.getIdentification(), client.getFirstname(), client.getLastname(),
				client.getBirthdate()));
		return 1;
	}

	@Override
	public List<Client> selectAllClients() {
		return DB;
	}

	@Override
	public Optional<Client> selectClientById(UUID id) {
		return DB.stream().filter(client -> client.getId().equals(id)).findFirst();
	}

	@Override
	public int deleteClientById(UUID id) {
		Optional<Client> maybeClient = selectClientById(id);
		if (!maybeClient.isPresent()) {
			return 0;
		}
		DB.remove(maybeClient.get());
		return 1;
	}

	@Override
	public int updateClientById(UUID id, Client client) {
		return selectClientById(id).map(p -> {
			int index = DB.indexOf(p);
			if (index > 0) {
				DB.set(index, new Client(id, client.getIdentification(), client.getFirstname(), client.getLastname(),
						client.getBirthdate()));
				return 1;
			}
			return 0;
		}).orElse(0);
	}

}
