package com.example.banco.service.impl;

import java.util.Arrays;
import java.util.Map.Entry;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.banco.dao.ClientDao;
import com.example.banco.model.BankClient;
import com.example.banco.model.ClientRequest;
import com.example.banco.model.ClientResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ClientServiceImpl implements ClientDao {

	private String urlBD = "https://testbankapi.firebaseio.com/clients.json";
	private RestTemplate restTemplate;

	public ClientServiceImpl() {
		restTemplate = new RestTemplate();
	}

	@Override
	public ClientResponse insertClient(ClientRequest clientRequest) {
		ClientResponse clientResponse = new ClientResponse();
		/* consumo del servicio de la BD del banco */
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<ClientRequest> request = new HttpEntity<ClientRequest>(clientRequest, headers);
		ResponseEntity<ClientResponse> response = restTemplate.postForEntity(urlBD, request, ClientResponse.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			/* no fue posible registrar el cliente */
			clientResponse.setCodigo("201");
			clientResponse.setDescripcion("Cliente registrado correctamente");
		} else {
			/* no fue posible registrar el cliente */
			clientResponse.setCodigo("403");
			clientResponse.setDescripcion("NO se pudo realizar el registro");
		}
		return clientResponse;
	}

	@Override
	public ClientResponse selectClientByIdentification(String id) {
		ClientResponse clientResponse = new ClientResponse();
		/* consumo del servicio de la BD del banco */
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		ResponseEntity<BankClient> response = restTemplate.getForEntity(urlBD, BankClient.class, headers);

		if (response.getStatusCode() == HttpStatus.OK) {
			BankClient clientes = response.getBody();
			for (Entry<String, Object> entry : clientes.getProperties().entrySet()) {
				/* se intenta convertir el objeto */
				try {
					ObjectMapper obj = new ObjectMapper();
					String jsonObj = obj.writeValueAsString(entry.getValue());
					ClientRequest clientRequest = obj.readValue(jsonObj, ClientRequest.class);
					/* se valida que exista en la bd */
					if (clientRequest.getIdentification() != null && !clientRequest.getIdentification().isEmpty()) {
						if (clientRequest.getIdentification().equals(id)) {
							/* no se permite registro */
							clientResponse.setCodigo("200");
							clientResponse.setDescripcion("NO se permite registro");
							break;
						} else {
							/* se registra el cliente */
							clientResponse.setCodigo("204");
							clientResponse.setDescripcion("Se permite registro");
						}
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		} else {
			/* servicio no disponible */
			clientResponse.setCodigo("404");
			clientResponse.setDescripcion("No se pudo consumir el servicio");
		}
		return clientResponse;
	}

}
