package br.com.projeto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "nivel")
public class Nivel extends GenericDomain {
	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 40, nullable = false)
	private String visualizacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVisualizacao() {
		return visualizacao;
	}

	public void setVisualizacao(String visualizacao) {
		this.visualizacao = visualizacao;
	}

}
