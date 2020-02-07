package br.com.testeComSpring.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Cidade {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome eh obrigatorio")
	private String nome;
	
	@Enumerated(EnumType.STRING)@NotNull
	private UF uf;
	
	@NotNull @DecimalMin(value = "0.0", inclusive = true)
	private BigDecimal taxa;
	
	
	public Cidade() {}


	public Cidade(String nome, UF uf, BigDecimal taxa) {
		this.nome = nome;
		this.uf = uf;
		this.taxa = taxa;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public UF getUf() {
		return uf;
	}


	public void setUf(UF uf) {
		this.uf = uf;
	}


	public BigDecimal getTaxa() {
		return taxa;
	}


	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
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
		Cidade other = (Cidade) obj;
		return Objects.equals(id, other.id);
	}
}
