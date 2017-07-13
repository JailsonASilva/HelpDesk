package br.com.projeto.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "dependente")
public class Dependente extends GenericDomain {

	@Column(nullable = false, length = 100)
	private String nome;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Colaborador colaborador;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Parentesco parentesco;	

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataNascimento;

	@Column(nullable = true, precision = 6, scale = 2, columnDefinition = "decimal default 0")
	private BigDecimal salarioFamilia;

	@Column(nullable = true, precision = 6, scale = 2, columnDefinition = "decimal default 0")
	private BigDecimal impostoRenda;

	@Column(nullable = true, precision = 6, scale = 2, columnDefinition = "decimal default 0")
	private BigDecimal salarioEducacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public BigDecimal getSalarioFamilia() {
		return salarioFamilia;
	}

	public void setSalarioFamilia(BigDecimal salarioFamilia) {
		this.salarioFamilia = salarioFamilia;
	}

	public BigDecimal getImpostoRenda() {
		return impostoRenda;
	}

	public void setImpostoRenda(BigDecimal impostoRenda) {
		this.impostoRenda = impostoRenda;
	}

	public BigDecimal getSalarioEducacao() {
		return salarioEducacao;
	}

	public void setSalarioEducacao(BigDecimal salarioEducacao) {
		this.salarioEducacao = salarioEducacao;
	}

}
