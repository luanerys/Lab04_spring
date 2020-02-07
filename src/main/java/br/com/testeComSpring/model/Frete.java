package br.com.testeComSpring.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Frete {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Descricao eh obrigatorio")
	private String descricao;
	
	@NotNull @DecimalMin(value = "0.0", inclusive = true)
	private BigDecimal peso;
	
	@NotNull @DecimalMin(value = "0.0", inclusive = true)
	private BigDecimal valor;
	
	@ManyToOne @JoinColumn(name = "cliente_id")
	@Valid
	@NotNull
	private Cliente cliente;
	
	@ManyToOne @JoinColumn(name = "cidade_id")
	@Valid
	@NotNull
	private Cidade cidade;
	
	public Frete() {}

	public Frete(String descricao, BigDecimal peso, BigDecimal valor, Cliente cliente, Cidade cidade) {
		this.descricao = descricao;
		this.peso = peso;
		this.valor = valor;
		this.cliente = cliente;
		this.cidade = cidade;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;	
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Frete other = (Frete) obj;
		return Objects.equals(id, other.id);
	}
}
