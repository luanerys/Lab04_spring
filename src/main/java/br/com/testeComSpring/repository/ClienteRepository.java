package br.com.testeComSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.testeComSpring.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByTelefone(String telefone);
}
