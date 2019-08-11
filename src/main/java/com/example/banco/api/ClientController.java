package com.example.banco.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banco.model.ClientRequest;
import com.example.banco.model.ClientResponse;
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
	public ClientResponse addClient(@Valid @NotNull @RequestBody ClientRequest clientRequest) {
		return clientService.addClient(clientRequest);
	}

	@GetMapping(path = "{id}")
	public ClientResponse getClientByIdentification(@PathVariable("id") String id) {
		return clientService.getClientByIdentification(id);
	}

}
