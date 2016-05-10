package br.clinica.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "administrativo_tipo_contato")
public class TipoContato extends GenericDomain {
	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 40, nullable = false)
	private String tipo;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
}
