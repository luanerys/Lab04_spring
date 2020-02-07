package br.com.testeComSpring.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.testeComSpring.model.Frete;

public interface FreteRepository extends JpaRepository<Frete, Long> {

	@Query(value = "FROM Frete f where f.cliente.nome = ?1 ORDER BY f.valor ASC")
	List<Frete> todosFretesPor(String nomeCliente);
	
	@Query(value = "SELECT f.valor FROM Frete f where f.id = ?1")
	BigDecimal buscaValor(Long idFrete);
	
	@Query(value = "SELECT MAX(f.valor) FROM Frete f")
	BigDecimal buscaMaiorValorDoFrete();
	
	
	
}
