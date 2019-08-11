package com.example.banco;

import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.banco.model.Client;

@SpringBootApplication
public class BancoApplication {

	private static final Logger log = LoggerFactory.getLogger(BancoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			String urlClient = "https://testbankapi.firebaseio.com/clients.json";

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			// HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			// Object response = restTemplate.exchange(urlClient, HttpMethod.GET, entity,
			// new ParameterizedTypeReference<Map<String, Object>>() {});

			ResponseEntity<Client> result = restTemplate.getForEntity(urlClient, Client.class);
			if (result.getStatusCode() == HttpStatus.OK) {
				Client clientes = result.getBody();
				System.out.println(clientes.getProperties());
				
				System.out.println("Test de insercion");
				
				JSONObject clienteNuevo = new JSONObject();
				clienteNuevo.put("identification", "01234");
				clienteNuevo.put("firstname", "newcl");
				clienteNuevo.put("lastname", "lasnewcl");
				clienteNuevo.put("birthdate", "2019-01-01");
				
				System.out.println("PETICION: " + clienteNuevo.toString());
				 HttpEntity<String> request = new HttpEntity<String>(clienteNuevo.toString(), headers);
				ResponseEntity<Client> result2 = restTemplate.postForEntity(urlClient, request, Client.class);
				if(result2.getStatusCode() == HttpStatus.OK) {
					System.out.println("cliente registrado correctamente");
					System.out.println(result2.getBody());
					System.out.println(result2.getBody().getProperties());
				} else {
					System.out.println("error al registrar el cliente");
				}
				
			} else {
				System.out.println("Error al consumir el servicio");
			}
		};
	}

}
