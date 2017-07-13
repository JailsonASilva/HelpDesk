package br.com.projeto.domain;

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
@Table(name = "prontuario")
public class Prontuario extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Colaborador colaborador;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataInicial;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataFinal;

	@Column(nullable = true, length = 2000)
	private String observacao;

	@ManyToOne
	@JoinColumn(nullable = false)
	private OcorrenciaProntuario ocorrenciaProntuario;

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public OcorrenciaProntuario getOcorrenciaProntuario() {
		return ocorrenciaProntuario;
	}

	public void setOcorrenciaProntuario(OcorrenciaProntuario ocorrenciaProntuario) {
		this.ocorrenciaProntuario = ocorrenciaProntuario;
	}

}
