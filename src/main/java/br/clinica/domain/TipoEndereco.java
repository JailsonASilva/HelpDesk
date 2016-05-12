package br.clinica.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "administrativo_tipo_endereco")
public class TipoEndereco extends GenericDomain {
	@Column(length = 100, nullable = false)
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}