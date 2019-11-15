package co.edu.usbcali.bank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.bank.dto.ClienteDTO;

class ClienteControllerTest {

	private final static Long clieId = 4590L;
	private final static String url = "http://localhost:8080/bank-web/api/cliente/";
	private final static Logger log = LoggerFactory.getLogger(ClienteControllerTest.class);

	@Test
	@DisplayName("save")
	void aTest() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setActivo("S");
			clienteDTO.setClieId(clieId);
			clienteDTO.setDireccion("Carrera 1");
			clienteDTO.setEmail("correo@server.com");
			clienteDTO.setNombre("Diego Pinto");
			clienteDTO.setTdocId(1L);
			clienteDTO.setTelefono("3123121122");

			Object object = restTemplate.postForObject(url + "save", clienteDTO, Object.class);
			assertNotNull(object);
		} catch (HttpStatusCodeException e) {
			log.info(e.getResponseBodyAsString());
			assertNull(e, e.getResponseBodyAsString());
		}

	}

	@Test
	@DisplayName("findById")
	void bTest() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ClienteDTO clienteDTO = restTemplate.getForObject(url + "findById/" + clieId, ClienteDTO.class);
			assertNotNull(clienteDTO);
			log.info(clienteDTO.getNombre());
		} catch (HttpStatusCodeException e) {
			log.info(e.getResponseBodyAsString());
			assertNull(e, e.getResponseBodyAsString());
		}

	}

	@Test
	@DisplayName("update")
	void cTest() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setActivo("N");
			clienteDTO.setClieId(clieId);
			clienteDTO.setDireccion("Carrera 1");
			clienteDTO.setEmail("correo@server.com");
			clienteDTO.setNombre("Diego Pinto");
			clienteDTO.setTdocId(1L);
			clienteDTO.setTelefono("3123121122");

			restTemplate.put(url + "update", clienteDTO);

		} catch (HttpStatusCodeException e) {
			log.info(e.getResponseBodyAsString());
			assertNull(e, e.getResponseBodyAsString());
		}

	}

	@Test
	@DisplayName("delete")
	void dTest() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setActivo("S");
			clienteDTO.setClieId(clieId);
			clienteDTO.setDireccion("Carrera 1");
			clienteDTO.setEmail("correo@server.com");
			clienteDTO.setNombre("Diego Pinto");
			clienteDTO.setTdocId(1L);
			clienteDTO.setTelefono("3123121122");

			ResponseEntity<String> response = restTemplate.exchange(url + "delete", HttpMethod.DELETE,
					new HttpEntity<ClienteDTO>(clienteDTO), String.class, clienteDTO.getClieId());
			log.info(response.getBody());
			assertEquals("Cliente eliminado", response.getBody());

		} catch (HttpStatusCodeException e) {
			log.info(e.getResponseBodyAsString());
			assertNull(e, e.getResponseBodyAsString());
		}
	}

}
