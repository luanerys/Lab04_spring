package br.com.testeComSpring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.testeComSpring.model.Cliente;
import br.com.testeComSpring.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<?> inserir(@Valid @RequestBody Cliente cliente){
		cliente = clienteService.salva(cliente);
		return new ResponseEntity<>(cliente, HttpStatus.CREATED);
	}
}
