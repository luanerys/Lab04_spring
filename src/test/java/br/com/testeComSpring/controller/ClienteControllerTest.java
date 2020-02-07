package br.com.testeComSpring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.testeComSpring.model.Cliente;
import br.com.testeComSpring.service.ClienteService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClienteController.class)
public class ClienteControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ClienteService clienteService;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Test
	public void deveSalvarCliente() throws JsonProcessingException, Exception {
		
		Cliente cliente = new Cliente("Cliente", "Rua 1", "32343234");
		
		Mockito.when(clienteService.salva(cliente)).thenReturn(cliente);
		
		MvcResult mvcResult =mvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andReturn();
	 
	 
	 String jsonResponse = mvcResult.getResponse().getContentAsString();
	 
	 
	 assertEquals(objectMapper.writeValueAsString(cliente), jsonResponse);
		
		
	}
}
