package br.com.testeComSpring.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
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
import br.com.testeComSpring.model.UF;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CidadeRepositoryTest {

	@Autowired
	CidadeRepository cidadeRepository;

	private Cidade cidade1, cidade2;

	@Before
	public void setUp() {
		cidade1 = new Cidade("Sao Luis", UF.MA, new BigDecimal(10.0));
		cidade2 = new Cidade("Sao Luis", UF.BA, new BigDecimal(10.0));
	}

	@Test
	public void deveSalvarCidade() {

		cidadeRepository.save(cidade1);

		List<Cidade> cidadesEncontrada = cidadeRepository.findAll();

		assertAll(() -> assertEquals("Sao Luis", cidadesEncontrada.get(0).getNome()),
				() -> assertEquals(UF.MA, cidadesEncontrada.get(0).getUf()));

		cidadeRepository.deleteAll();
	}

	@Test
	public void deveBuscarCidadePorNome() {

		cidadeRepository.save(cidade1);
		cidadeRepository.save(cidade2);

		List<Cidade> cidadesEncontrada = cidadeRepository.findByNome("Sao Luis");

		assertAll(() -> assertEquals(2, cidadesEncontrada.size()),
				() -> assertEquals("Sao Luis", cidadesEncontrada.get(0).getNome()),
				() -> assertEquals(UF.MA, cidadesEncontrada.get(0).getUf()),
				() -> assertEquals("Sao Luis", cidadesEncontrada.get(1).getNome()),
				() -> assertEquals(UF.BA, cidadesEncontrada.get(1).getUf()));

		cidadeRepository.deleteAll();
	}

}
