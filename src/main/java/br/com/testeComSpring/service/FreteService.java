package br.com.testeComSpring.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.testeComSpring.model.Frete;
import br.com.testeComSpring.repository.CidadeRepository;
import br.com.testeComSpring.repository.ClienteRepository;
import br.com.testeComSpring.repository.FreteRepository;

@Service
public class FreteService {
	
	@Autowired
	private FreteRepository freteRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Transactional
	public Frete inserir(Frete frete) throws Exception {
		if(Objects.isNull(clienteRepository.findById(frete.getCidade().getId())) 
		|| Objects.isNull(cidadeRepository.findById(frete.getCliente().getId()))) {	
			throw new InvalidDataAccessApiUsageException("Erro");
		}
			
		return freteRepository.save(frete);
	}
	
	
	public BigDecimal buscaValorDo(Frete frete) {
		return freteRepository.buscaValor(frete.getId());
	}
	
	public BigDecimal buscaMaiorValorDeFrete() {
		return freteRepository.buscaMaiorValorDoFrete();
	}
	
	public List<Frete> buscarFretes() {
		return freteRepository.findAll();
	}

	public Frete buscarFrete(Long id) {
		return freteRepository.findById(id).orElse(null);
	}

	public void remover(Long id) {
		freteRepository.deleteById(id);
	}
	
}
