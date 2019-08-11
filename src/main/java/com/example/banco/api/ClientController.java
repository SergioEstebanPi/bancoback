package com.example.banco.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banco.model.Client;
import com.example.banco.service.ClientService;

@RequestMapping("api/v1/client")
@RestController
public class ClientController {

	@Autowired
	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@PostMapping
	public int addClient(@Valid @NotNull @RequestBody Client client) {
		return clientService.addClient(client);
	}

	@GetMapping
	public List<Client> getAllClients() {
		return clientService.getAllClients();
	}

	@GetMapping(path = "{id}")
	public Client getClientById(@PathVariable("id") UUID id, String nombre) {
		return clientService.getClientById(id).orElse(null);
	}

	@DeleteMapping(path = "{id}")
	public int deleteClient(@PathVariable("id") UUID id) {
		return clientService.deleteClient(id);
	}

	@PutMapping(path = "{id}")
	public int updateClient(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Client client) {
		return clientService.updatePerson(id, client);
	}
}
