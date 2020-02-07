package br.com.testeComSpring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.testeComSpring.model.Frete;
import br.com.testeComSpring.service.FreteService;

@RestController
@RequestMapping("/fretes")
public class FreteController {

	@Autowired
	private FreteService freteService;
	

	@GetMapping
	public ResponseEntity<List<Frete>> fretes(){
		List<Frete> fretes = freteService.buscarFretes();
		return ResponseEntity.ok(fretes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Frete> contato(@PathVariable Long id){
		return ResponseEntity.ok().body(freteService.buscarFrete(id)); 
	}
	
	@PostMapping
	public ResponseEntity<?> inserir(@Valid @RequestBody Frete frete) throws Exception {
		frete = freteService.inserir(frete);
		return new ResponseEntity<>(frete, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable Long id, @Valid @RequestBody  Frete frete) throws Exception {
		frete = freteService.inserir(frete);
		return new ResponseEntity<>(frete, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		freteService.remover(id);
		return ResponseEntity.noContent().build();
	}	
}
