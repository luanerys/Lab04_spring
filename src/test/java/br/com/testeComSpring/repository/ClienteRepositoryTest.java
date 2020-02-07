package br.com.testeComSpring.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.testeComSpring.model.Cliente;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private Cliente cliente;

	@Before
	public void setUp() {
		cliente = new Cliente("Jamil", "Rua 1", "32344332");
	}

	@Test
	public void deveLancarExcecaoAoSalvarClienteComNomeNulo() throws Exception {

		expectedException.expect(ConstraintViolationException.class);
		expectedException.expectMessage("Nome não pode ser vazio");

		cliente.setNome(null);
		clienteRepository.save(cliente);
	}

	@Test
	public void deveLancarExcecaoAoSalvarClienteComEnderecoNulo() throws Exception {

		expectedException.expect(ConstraintViolationException.class);
		expectedException.expectMessage("Endereco não pode ser vazio");

		cliente.setEndereco(null);
		clienteRepository.save(cliente);
	}

	@Test
	public void deveLancarExcecaoAoSalvarClienteComTelefoneNulo() throws Exception {

		expectedException.expect(ConstraintViolationException.class);
		expectedException.expectMessage("Telefone não pode ser vazio");

		cliente.setTelefone(null);
		clienteRepository.save(cliente);
	}

	@Test
	public void deveSalvarCliente() {

		clienteRepository.save(cliente);

		List<Cliente> clientes = clienteRepository.findAll();

		assertAll(() -> assertEquals(1, clientes.size()),
				  () -> assertEquals("Jamil", clientes.get(0).getNome())
                 );
		
		clienteRepository.deleteAll();
	}
	
	
	@Test
	public void deveEncontrarClientePorTelefone() {
		
		clienteRepository.save(cliente);
		
		Cliente clienteEncontrado = clienteRepository.findByTelefone("32344332");

		assertAll(() -> assertEquals("Jamil", clienteEncontrado.getNome()),
				  () -> assertEquals("Rua 1",clienteEncontrado.getEndereco())
                 );
		
		clienteRepository.deleteAll();
	}

}
