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
public class FreteValidationTest {

	private Validator validador;
	private Frete frete;
	private Cliente cliente = new Cliente("jamil", "rua 1", "23456787");
	private Cidade cidade = new Cidade("Sao Luis", UF.MA, new BigDecimal(10.0));

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validador = factory.getValidator();

		frete = new Frete("descricao aqui", new BigDecimal(20), new BigDecimal(10), cliente, cidade);
	}

	@Test
	public void deveLancarExcecaoComFreteComDescricaoNula() {

		frete.setDescricao(null);

		Set<ConstraintViolation<Frete>> restricoes = validador.validate(frete);

		ConstraintViolation<Frete> restricao = restricoes.iterator().next();

		assertAll(() -> assertTrue(restricoes.size() == 1),
				() -> assertEquals("descricao", restricao.getPropertyPath().toString()),
				() -> assertEquals("Descricao não pode ser vazio", restricao.getMessage()),
				() -> assertEquals(null, restricao.getInvalidValue()));
	}

	@Test
	public void deveLancarExcecaoDeFreteComPesoNulo() {

		frete.setPeso(null);

		Set<ConstraintViolation<Frete>> restricoes = validador.validate(frete);

		ConstraintViolation<Frete> restricao = restricoes.iterator().next();

		assertAll(() -> assertTrue(restricoes.size() == 1),
				() -> assertEquals("peso", restricao.getPropertyPath().toString()),
				() -> assertEquals("não pode ser nulo", restricao.getMessage()),
				() -> assertEquals(null, restricao.getInvalidValue()));
	}

	@Test
	public void deveLancarExcecaoDeFreteComValorNulo() {

		frete.setValor(null);

		Set<ConstraintViolation<Frete>> restricoes = validador.validate(frete);

		ConstraintViolation<Frete> restricao = restricoes.iterator().next();

		assertAll(() -> assertTrue(restricoes.size() == 1),
				() -> assertEquals("valor", restricao.getPropertyPath().toString()),
				() -> assertEquals("não pode ser nulo", restricao.getMessage()),
				() -> assertEquals(null, restricao.getInvalidValue()));
	}

	@Test
	public void deveLancarExcecaoDeFreteComClienteNulo() {

		frete.setCliente(null);

		Set<ConstraintViolation<Frete>> restricoes = validador.validate(frete);

		ConstraintViolation<Frete> restricao = restricoes.iterator().next();

		assertAll(() -> assertTrue(restricoes.size() == 1),
				() -> assertEquals("cliente", restricao.getPropertyPath().toString()),
				() -> assertEquals("não pode ser nulo", restricao.getMessage()),
				() -> assertEquals(null, restricao.getInvalidValue()));
	}

	@Test
	public void deveLancarExcecaoDeFreteComCidadeNula() {

		frete.setCidade(null);

		Set<ConstraintViolation<Frete>> restricoes = validador.validate(frete);

		ConstraintViolation<Frete> restricao = restricoes.iterator().next();

		assertAll(() -> assertTrue(restricoes.size() == 1),
				() -> assertEquals("cidade", restricao.getPropertyPath().toString()),
				() -> assertEquals("não pode ser nulo", restricao.getMessage()),
				() -> assertEquals(null, restricao.getInvalidValue()));
	}

}
