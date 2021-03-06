package co.edu.usbcali.bank.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.dto.ConsignarDTO;
import co.edu.usbcali.bank.dto.RetirarDTO;
import co.edu.usbcali.bank.dto.TransferirDTO;
import co.edu.usbcali.bank.service.TransaccionesService;

@RestController
@RequestMapping("/transaccion")
public class TransaccionesController {

	@Autowired
	TransaccionesService transaccionesService;

	@PostMapping("/retirar")
	public ResponseEntity<?> retirar(@RequestBody RetirarDTO retirarDTO) {
		try {
			Long idTransaccion = transaccionesService.retirar(retirarDTO.getCuenId(), retirarDTO.getValor(),
					retirarDTO.getUsuUsuario());
			return ResponseEntity.ok().body(idTransaccion);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError("400", e.getMessage()));
		}
	}

	@PostMapping("/consignar")
	public ResponseEntity<?> consignar(@RequestBody ConsignarDTO consignarDTO) {
		try {
			Long idTransaccion = transaccionesService.consignar(consignarDTO.getCuenId(), consignarDTO.getValor(),
					consignarDTO.getUsuUsuario());
			return ResponseEntity.ok().body(idTransaccion);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError("400", e.getMessage()));
		}
	}

	@PostMapping("/transferir")
	public ResponseEntity<?> transferir(@RequestBody TransferirDTO transferirDTO) {
		try {
			Long idTransaccion = transaccionesService.transferir(transferirDTO.getCuenIdOrigen(),
					transferirDTO.getCuenIdDestino(), transferirDTO.getValor(), transferirDTO.getUsuUsuario());
			return ResponseEntity.ok().body(idTransaccion);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError("400", e.getMessage()));
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
