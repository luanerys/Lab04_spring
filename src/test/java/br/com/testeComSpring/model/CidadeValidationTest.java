package br.com.testeComSpring.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CidadeValidationTest {
	
	private Validator validador;
	private Cidade cidade;
	
	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validador = factory.getValidator();

		cidade = new Cidade("Sao Luis", UF.MA, new BigDecimal(10.0));
	}
	
	@Test
	public void deveLancarExcecaoComCidadeComNomeNulo() {

		cidade.setNome(null);

		Set<ConstraintViolation<Cidade>> restricoes = validador.validate(cidade);

		ConstraintViolation<Cidade> restricao = restricoes.iterator().next();

		assertAll(() -> assertTrue(restricoes.size() == 1),
				() -> assertEquals("nome", restricao.getPropertyPath().toString()),
				() -> assertEquals("Nome não pode ser vazio", restricao.getMessage()),
				() -> assertEquals(null, restricao.getInvalidValue()));
	}
	
	
	@Test
	public void deveLancarExcecaoComCidadeComUfNulo() {

		cidade.setUf(null);

		Set<ConstraintViolation<Cidade>> restricoes = validador.validate(cidade);

		ConstraintViolation<Cidade> restricao = restricoes.iterator().next();

		assertAll(() -> assertTrue(restricoes.size() == 1),
				() -> assertEquals("uf", restricao.getPropertyPath().toString()),
				() -> assertEquals("não pode ser nulo", restricao.getMessage()),
				() -> assertEquals(null, restricao.getInvalidValue()));
	}
	
	
	@Test
	public void deveLancarExcecaoComCidadeComTaxaNula() {

		cidade.setTaxa(null);

		Set<ConstraintViolation<Cidade>> restricoes = validador.validate(cidade);

		ConstraintViolation<Cidade> restricao = restricoes.iterator().next();

		assertAll(() -> assertTrue(restricoes.size() == 1),
				() -> assertEquals("taxa", restricao.getPropertyPath().toString()),
				() -> assertEquals("não pode ser nulo", restricao.getMessage()),
				() -> assertEquals(null, restricao.getInvalidValue()));
	}
	
	

}
