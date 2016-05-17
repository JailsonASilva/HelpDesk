package br.clinica.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "administrativo_pessoa_fisica")
public class PessoaFisica extends GenericDomain {
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	@Column(length = 15, nullable = true)
	private String cpf;

	@Column(length = 20, nullable = true)
	private String sexo;

	@Column(length = 40, nullable = true)
	private String identidade;

	@Column(length = 15, nullable = true)
	private String orgaoEmissor;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataExpedicao;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Profissao profissao;

	@ManyToOne
	@JoinColumn(nullable = true)
	private EstadoCivil estadoCivil;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Cidade nacionalidade;

	@OneToOne
	@JoinColumn(nullable = true)
	private Pessoa pai;

	@OneToOne
	@JoinColumn(nullable = true)
	private Pessoa mae;

	@OneToOne
	@JoinColumn(nullable = true)
	private Pessoa conjuge;

	@Column(nullable = true, precision = 6, scale = 2)
	private BigDecimal renda;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Cidade getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(Cidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public Pessoa getPai() {
		return pai;
	}

	public void setPai(Pessoa pai) {
		this.pai = pai;
	}

	public Pessoa getMae() {
		return mae;
	}

	public void setMae(Pessoa mae) {
		this.mae = mae;
	}

	public Pessoa getConjuge() {
		return conjuge;
	}

	public void setConjuge(Pessoa conjuge) {
		this.conjuge = conjuge;
	}

	public BigDecimal getRenda() {
		return renda;
	}

	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}
}
