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
@Table(name = "ferias")
public class Ferias extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = true)
	private Colaborador colaborador;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataAquisitivoInicio;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataAquisitivoFinal;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataGozoInicio;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataGozoFinal;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataLimite;

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Date getDataAquisitivoInicio() {
		return dataAquisitivoInicio;
	}

	public void setDataAquisitivoInicio(Date dataAquisitivoInicio) {
		this.dataAquisitivoInicio = dataAquisitivoInicio;
	}

	public Date getDataAquisitivoFinal() {
		return dataAquisitivoFinal;
	}

	public void setDataAquisitivoFinal(Date dataAquisitivoFinal) {
		this.dataAquisitivoFinal = dataAquisitivoFinal;
	}

	public Date getDataGozoInicio() {
		return dataGozoInicio;
	}

	public void setDataGozoInicio(Date dataGozoInicio) {
		this.dataGozoInicio = dataGozoInicio;
	}

	public Date getDataGozoFinal() {
		return dataGozoFinal;
	}

	public void setDataGozoFinal(Date dataGozoFinal) {
		this.dataGozoFinal = dataGozoFinal;
	}

	public Date getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}

}
