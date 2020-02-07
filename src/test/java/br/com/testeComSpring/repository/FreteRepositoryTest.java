package br.com.testeComSpring.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.testeComSpring.model.Cidade;
import br.com.testeComSpring.model.Cliente;
import br.com.testeComSpring.model.Frete;
import br.com.testeComSpring.model.UF;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FreteRepositoryTest {

	@Autowired
	private FreteRepository freteRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	private Frete frete;
	private Cliente cliente = new Cliente("Jamil", "rua 1", "23456787");
	private Cidade cidade   = new Cidade("Sao Luis", UF.MA, new BigDecimal(10.0));
	
	
	@Before
	public void setUp() {
		
		clienteRepository.save(cliente);
		cidadeRepository.save(cidade);
		
		frete = new Frete("descricao aqui", new BigDecimal(20), new BigDecimal(30), cliente, cidade);
	}
	
	@Test
	public void deveSalvarFrete() {
		
		freteRepository.save(frete);
		
		List<Frete> fretesEncontrado = freteRepository.findAll();

		assertAll(
				() -> assertTrue(fretesEncontrado.get(0).getDescricao().equals("descricao aqui")),
				() -> assertEquals(new BigDecimal(30),  fretesEncontrado.get(0).getValor()));

		freteRepository.deleteAll();
	}
	
	
	public void deveBuscarFretesPorCliente() {
		
		Cliente cliente1 = new Cliente("Jamil", "RUA 1", "34432334");
		
		Frete frete1 = new Frete("descricao aqui 2", new BigDecimal(20), new BigDecimal(20), cliente1, cidade);
		
		List<Frete> fretes = new ArrayList<>();
		fretes.addAll(Arrays.asList(frete, frete1));
		
		freteRepository.saveAll(fretes);
		
		List<Frete> fretesEncontrados = freteRepository.todosFretesPor("Jamil");
		
		
		assertAll(
				()->assertEquals(2, fretesEncontrados.size()),
				() -> assertTrue(fretesEncontrados.get(0).getDescricao().equals("descricao aqui 2")),
				() -> assertEquals(new BigDecimal(20)  ,  fretesEncontrados.get(0).getValor()    ),
				() -> assertTrue(fretesEncontrados.get(1).getDescricao().equals("descricao aqui")),
				() -> assertEquals(new BigDecimal(30)  ,  fretesEncontrados.get(1).getValor()    ));
		
	}
	
}
