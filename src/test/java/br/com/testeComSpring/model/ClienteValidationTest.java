package br.com.testeComSpring.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
public class ClienteValidationTest {

	private Cliente cliente;

	private Validator validador;


	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validador = factory.getValidator();

		cliente = new Cliente("Jamil", "Rua 1", "32344334");
	}

	@Test
	public void deveLancarExcecaoAoSalvarClienteComNomeNulo() {

		cliente.setNome(null);

		Set<ConstraintViolation<Cliente>> restricoes = validador.validate(cliente);

		ConstraintViolation<Cliente> restricao = restricoes.iterator().next();

		assertAll(() -> assertTrue(restricoes.size() == 1),
				() -> assertEquals("nome", restricao.getPropertyPath().toString()),
				() -> assertEquals("Nome não pode ser vazio", restricao.getMessage()),
				() -> assertEquals(null, restricao.getInvalidValue()));
	}

	@Test
	public void naoDeveAceitarEnderecoNulo() {
		cliente.setEndereco(null);

		Set<ConstraintViolation<Cliente>> restricoes = validador.validate(cliente);

		ConstraintViolation<Cliente> restricao = restricoes.iterator().next();

		assertAll(() -> assertTrue(restricoes.size() == 1),
				() -> assertEquals("endereco", restricao.getPropertyPath().toString()),
				() -> assertEquals("Endereco não pode ser vazio", restricao.getMessage()),
				() -> assertEquals(null, restricao.getInvalidValue()));

	}

	@Test
	public void naoDeveAceitarTelefoneNulo() {
		cliente.setTelefone(null);

		Set<ConstraintViolation<Cliente>> restricoes = validador.validate(cliente);

		ConstraintViolation<Cliente> restricao = restricoes.iterator().next();

		assertAll(() -> assertTrue(restricoes.size() == 1),
				() -> assertEquals("telefone", restricao.getPropertyPath().toString()),
				() -> assertEquals("Telefone não pode ser vazio", restricao.getMessage()),
				() -> assertEquals(null, restricao.getInvalidValue()));

	}

	@Test
	public void naoDeveAceitarTelefoneComTamanhoCurto() {
		cliente.setTelefone("8732");

		Set<ConstraintViolation<Cliente>> restricoes = validador.validate(cliente);

		ConstraintViolation<Cliente> restricao = restricoes.iterator().next();

		assertAll(() -> assertTrue(restricoes.size() == 1),
				() -> assertEquals("telefone", restricao.getPropertyPath().toString()),
				() -> assertEquals("Tamanho inválido: min => 8 max => 9", restricao.getMessage()),
				() -> assertEquals("8732", restricao.getInvalidValue()));

	}

	@Test
	public void naoDeveAceitarTelefoneComTamanhoLongo() {
		cliente.setTelefone("8732544554");

		Set<ConstraintViolation<Cliente>> restricoes = validador.validate(cliente);

		ConstraintViolation<Cliente> restricao = restricoes.iterator().next();

		assertAll(() -> assertTrue(restricoes.size() == 1),
				() -> assertEquals("telefone", restricao.getPropertyPath().toString()),
				() -> assertEquals("Tamanho inválido: min => 8 max => 9", restricao.getMessage()),
				() -> assertEquals("8732544554", restricao.getInvalidValue()));

	}

	@Test
	public void naoDeveAceitarTelefoneComCaracteresDiferentesDeDigitos() {
		cliente.setTelefone("3236-4487");

		Set<ConstraintViolation<Cliente>> restricoes = validador.validate(cliente);

		ConstraintViolation<Cliente> restricao = restricoes.iterator().next();

		assertAll(() -> assertTrue(restricoes.size() == 1),
				() -> assertEquals("telefone", restricao.getPropertyPath().toString()),
				() -> assertEquals("Telefone deve possuir só digitos", restricao.getMessage()),
				() -> assertEquals("3236-4487", restricao.getInvalidValue()));

	}

}
