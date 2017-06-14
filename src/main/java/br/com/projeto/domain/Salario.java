package br.com.projeto.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "salario")
public class Salario extends GenericDomain {

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 10, nullable = true)
	private String classe;

	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal salario;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

}
