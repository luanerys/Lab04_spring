package br.com.testeComSpring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.testeComSpring.model.Cliente;
import br.com.testeComSpring.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;
	
	
	public Cliente salva(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente buscaPor(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}
	
	public List<Cliente> buscaTodos(){
		return clienteRepository.findAll();
	}
}
