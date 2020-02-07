package br.com.testeComSpring.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.testeComSpring.model.Cidade;
import br.com.testeComSpring.model.Cliente;
import br.com.testeComSpring.model.Frete;
import br.com.testeComSpring.model.UF;
import br.com.testeComSpring.repository.CidadeRepository;
import br.com.testeComSpring.repository.ClienteRepository;
import br.com.testeComSpring.repository.FreteRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FreteServiceTest {
	
	@Autowired
	private FreteService freteService;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private FreteRepository freteRepository;
	
	@Rule
    public ExpectedException expectedException = ExpectedException.none();
	
	private Frete frete;
	
	private final BigDecimal valorFixo = new BigDecimal(10.0);
		
	
	@Test
	public void deveLancarExcecaoAoSalvarFreteSemClienteCadastrado() throws Exception {

		expectedException.expect(InvalidDataAccessApiUsageException.class);
		

		Cliente cliente = new Cliente("Jamil", "endereco", "32344543");
		Cidade cidade = new Cidade("Sao Luis", UF.MA, new BigDecimal(10));
		cidadeRepository.save(cidade);

		BigDecimal peso = new BigDecimal(5.0);

		BigDecimal valorFrete = this.calculaValorFrete(peso, cidade.getTaxa());

		frete = new Frete("uma descricao aqui", peso, valorFrete, cliente, cidade);

		freteService.inserir(frete);

	}
	
	private BigDecimal calculaValorFrete(BigDecimal peso, BigDecimal valorTaxa) {
		return  valorTaxa.add(peso.multiply(valorFixo));
	}
	
	
	@Test
	public void deveLancarExcecaoAoSalvarFreteSemCidadeCadastrada() throws Exception {

		expectedException.expect(InvalidDataAccessApiUsageException.class);

		Cliente cliente = new Cliente("Jamil", "endereco", "32344543");
		clienteRepository.save(cliente);
		Cidade cidade = new Cidade("Sao Luis", UF.MA, new BigDecimal(10));

		BigDecimal peso = new BigDecimal(5.0);

		BigDecimal valorFrete = this.calculaValorFrete(peso, cidade.getTaxa());

		frete = new Frete("uma descricao aqui", peso, valorFrete, cliente, cidade);

		freteService.inserir(frete);

	}
	
	
	@Test
	public void deveSalvarFrete() throws Exception {

		Cliente cliente = new Cliente("Jamil", "endereco", "32344543");
		clienteRepository.save(cliente);

		Cidade cidade = new Cidade("Sao Luis", UF.MA, new BigDecimal(10));
		cidadeRepository.save(cidade);

		BigDecimal peso = new BigDecimal(5.0);

		BigDecimal valorFrete = this.calculaValorFrete(peso, cidade.getTaxa());

		frete = new Frete("uma descricao aqui", peso, valorFrete, cliente, cidade);

		freteService.inserir(frete);

	}
	
	@Test
	public void deveRecuperarValorDoFrete() throws Exception {
		
		Cliente cliente = new Cliente("Jamil", "endereco", "32344543");
		clienteRepository.save(cliente);

		Cidade cidade = new Cidade("Sao Luis", UF.MA, new BigDecimal(10));
		cidadeRepository.save(cidade);

		BigDecimal peso = new BigDecimal(5.0);

		BigDecimal valorFrete = this.calculaValorFrete(peso, cidade.getTaxa());

		frete = new Frete("uma descricao aqui", peso, valorFrete, cliente, cidade);

		freteService.inserir(frete);
		
		List<Frete> freteBuscado = freteRepository.findAll();
		
		
		assertEquals(1, freteBuscado.size());
		assertTrue(frete.getValor().equals(valorFrete));
			
	}
	
	
	@Test
	public void deveRecuperarMaiorValorDoFrete() throws Exception {
		
		Cliente cliente = new Cliente("Jamil", "endereco", "32344543");
		clienteRepository.save(cliente);

		Cidade cidade = new Cidade("Sao Luis", UF.MA, new BigDecimal(20));
		Cidade cidade1 = new Cidade("Sao Luis", UF.RJ, new BigDecimal(5));
		cidadeRepository.saveAll(Arrays.asList(cidade, cidade1));

		BigDecimal peso = new BigDecimal(5.0);

		BigDecimal valorMaiorFrete = this.calculaValorFrete(peso, cidade.getTaxa());
		
		BigDecimal valorMenorFrete = this.calculaValorFrete(peso, cidade1.getTaxa());


		frete = new Frete("maior frete aqui", peso, valorMaiorFrete, cliente, cidade);
		Frete frete1 = new Frete("menor frete aqui", peso, valorMenorFrete, cliente, cidade1);

		freteService.inserir(frete);
		freteService.inserir(frete1);
		
		BigDecimal valorBuscado = freteService.buscaMaiorValorDeFrete();
		
		
		assertTrue(valorMaiorFrete.compareTo(valorBuscado) == 0);
			
	}
	


}
