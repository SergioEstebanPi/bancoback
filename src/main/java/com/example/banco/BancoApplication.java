package com.example.banco;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.banco.model.Client;
import com.example.banco.model.ClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

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
				System.out.println("INI");
				Client clientes = result.getBody();
				System.out.println(clientes.getProperties());
			} else {
				System.out.println("Error al consumir el servicio");
			}
		};
	}

}
