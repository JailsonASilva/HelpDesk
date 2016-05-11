package br.clinica.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "financeiro_finalizadora")
public class Finalizadora extends GenericDomain{
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(length = 100, nullable = false)
	private String tipo;
	
	@Column(length = 100, nullable = false)
	private Double valorSangria;
	

	@Column(length = 100, nullable = false)
	private Boolean avisarSangria;
	

	@Column(length = 100, nullable = false)
	private Boolean inativo;


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Double getValorSangria() {
		return valorSangria;
	}


	public void setValorSangria(Double valorSangria) {
		this.valorSangria = valorSangria;
	}


	public Boolean getAvisarSangria() {
		return avisarSangria;
	}


	public void setAvisarSangria(Boolean avisarSangria) {
		this.avisarSangria = avisarSangria;
	}


	public Boolean getInativo() {
		return inativo;
	}


	public void setInativo(Boolean inativo) {
		this.inativo = inativo;
	}
	
	
}
