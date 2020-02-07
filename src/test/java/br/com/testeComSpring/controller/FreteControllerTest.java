package br.com.testeComSpring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.testeComSpring.model.Cidade;
import br.com.testeComSpring.model.Cliente;
import br.com.testeComSpring.model.Frete;
import br.com.testeComSpring.model.UF;
import br.com.testeComSpring.service.FreteService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FreteController.class)
public class FreteControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private FreteService freteService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void deveBuscarTodosFretes() throws Exception {

		Frete frete = new Frete("algo aqui", new BigDecimal(10), new BigDecimal(10),
				new Cliente("cliente", "cliente", "cliente"), new Cidade("cidade", UF.AC, new BigDecimal(10)));

		Frete frete2 = new Frete("algo aqui", new BigDecimal(10), new BigDecimal(10),
				new Cliente("cliente", "cliente", "cliente"), new Cidade("cidade", UF.AC, new BigDecimal(10)));

		List<Frete> fretes = Arrays.asList(frete, frete2);

		Mockito.when(freteService.buscarFretes()).thenReturn(fretes);

		mvc.perform(get("/fretes").contentType("application/json")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void deveBuscarFretesPorId() throws Exception {

		Frete f = new Frete("algo aqui", new BigDecimal(10), new BigDecimal(10),
				new Cliente("cliente", "cliente", "cliente"), new Cidade("cidade", UF.AC, new BigDecimal(10)));


		Mockito.when(freteService.buscarFrete(1L)).thenReturn(f);

		mvc.perform(get("/fretes/{id}", 1L).contentType("application/json")).andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void deveCriarUmFrete() throws Exception {

		Cliente cliente = new Cliente("Cliente", "Rua 1", "32343234");
		cliente.setId(1L);
		Cidade cidade = new Cidade("Cidade", UF.AC, new BigDecimal(10.0));
		cidade.setId(1l);

		Frete freteRequest = new Frete("algo aqui", new BigDecimal(10.0), new BigDecimal(10.0), cliente, cidade);
		Frete freteResponse = new Frete("algo aqui", new BigDecimal(10.0), new BigDecimal(10.0), cliente, cidade);

		Mockito.when(freteService.inserir(freteRequest)).thenReturn(freteResponse);

		MvcResult mvcResult = mvc
				.perform(post("/fretes").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(freteRequest)))
				.andExpect(status().isCreated()).andReturn();

		String jsonResponse = mvcResult.getResponse().getContentAsString();

		assertEquals(objectMapper.writeValueAsString(freteRequest), jsonResponse);

	}
	
	
	@Test
	public void deveLancarErro400AoSalvarFreteComClienteNulo() throws Exception {

		Cliente cliente = new Cliente("Cliente", "Rua 1", "32343234");
		cliente.setId(1L);
		Cidade cidade = new Cidade("Cidade", UF.AC, new BigDecimal(10.0));
		cidade.setId(1l);

		Frete frete = new Frete("algo aqui", new BigDecimal(10.0), new BigDecimal(10.0), null, cidade);
		

		Mockito.when(freteService.inserir(frete)).thenReturn(frete);

		 mvc.perform(post("/fretes").contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(frete)))
			.andExpect(status().isBadRequest());

	

	}

}
