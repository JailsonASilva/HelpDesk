package br.com.projeto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "departamento")
public class Departamento extends GenericDomain {
	@Column(length = 100, nullable = false)
	private String nome;

	@Column(nullable = false)
	private Boolean atendimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Boolean atendimento) {
		this.atendimento = atendimento;
	}
	
	@Transient
	public String getAtendimentoFormatado() {
		String atendimentoFormatado = "Não";

		if (atendimento) {
			atendimentoFormatado = "Sim";
		}

		return atendimentoFormatado;
	}	

}
