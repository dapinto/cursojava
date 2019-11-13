package co.edu.usbcali.bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.dto.ClienteDTO;
import co.edu.usbcali.bank.dto.ClienteMapper;
import co.edu.usbcali.bank.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@Autowired
	ClienteMapper clienteMapper;

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {

		Optional<Cliente> clienteOptional = clienteService.findById(id);
		if (!clienteOptional.isPresent()) {
			return ResponseEntity.badRequest().body("El cliente no existe");
		}
		Cliente cliente = clienteOptional.get();
		ClienteDTO clienteDTO = clienteMapper.entityToDTO(cliente);
		return ResponseEntity.ok().body(clienteDTO);
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {

		List<Cliente> clientes = clienteService.findAll();
		if (clientes.isEmpty()) {
			return ResponseEntity.badRequest().body("No existen clientes");
		}

		List<ClienteDTO> clientesDTO = clienteMapper.toClientesDTOs(clientes);
		return ResponseEntity.ok().body(clientesDTO);
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ClienteDTO clienteDTO) {

		try {
			Cliente cliente = clienteMapper.dtoToEntity(clienteDTO);
			cliente = clienteService.save(cliente);
			clienteDTO = clienteMapper.entityToDTO(cliente);
			return ResponseEntity.ok().body(clienteDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody ClienteDTO clienteDTO) {

		try {
			Cliente cliente = clienteMapper.dtoToEntity(clienteDTO);
			cliente = clienteService.update(cliente);
			clienteDTO = clienteMapper.entityToDTO(cliente);
			return ResponseEntity.ok().body(clienteDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody ClienteDTO clienteDTO) {

		try {
			Cliente cliente = clienteMapper.dtoToEntity(clienteDTO);
			clienteService.delete(cliente);
			return ResponseEntity.ok().body("Cliente eliminado");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		try {
			clienteService.deleteById(id);
			return ResponseEntity.ok().body("Cliente eliminado");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
