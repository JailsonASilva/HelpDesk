package br.clinica.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "administrativo_pessoa")
public class Pessoa {
	@Id
	private Long codigo;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 20, nullable = false)
	private String tipo;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascFund;

	@Column(nullable = false)
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean getInativo() {
		return inativo;
	}

	public void setInativo(Boolean inativo) {
		this.inativo = inativo;
	}

	public Date getDataNascFund() {
		return dataNascFund;
	}

	public void setDataNascFund(Date dataNascFund) {
		this.dataNascFund = dataNascFund;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Long getCodigo() {
		return codigo;
	}

}
