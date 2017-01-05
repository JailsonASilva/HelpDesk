package br.com.projeto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "nivel")
public class Nivel extends GenericDomain {
	@Column(length = 100, nullable = false)
	private String nome;

	@Column(nullable = true)
	private Boolean publico;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getPublico() {
		return publico;
	}

	public void setPublico(Boolean publico) {
		this.publico = publico;
	}

	@Transient
	public String getPublicoFormatado() {
		String publicoFormatado = "Não";

		if (publico) {
			publicoFormatado = "Sim";
		}

		return publicoFormatado;
	}
}
