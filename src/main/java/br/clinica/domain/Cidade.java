package br.clinica.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "administrativo_cidade")
public class Cidade extends GenericDomain {
	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 15, nullable = true)
	private String cep;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Estado estado;

	@Column(nullable = true)
	private Long codigoIBGE;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Long getCodigoIBGE() {
		return codigoIBGE;
	}

	public void setCodigoIBGE(Long codigoIBGE) {
		this.codigoIBGE = codigoIBGE;
	}

}
