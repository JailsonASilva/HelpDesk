package br.com.projeto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn(nullable = true)
	private Usuario gestor;

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

	public Usuario getGestor() {
		return gestor;
	}

	public void setGestor(Usuario gestor) {
		this.gestor = gestor;
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
