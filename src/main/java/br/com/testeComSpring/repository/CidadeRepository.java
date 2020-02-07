package br.com.testeComSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.testeComSpring.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	List<Cidade> findByNome(String nome);
}
