package br.com.projeto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "horario")
public class Horario extends GenericDomain {

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 50, nullable = false)
	private String tipoHorario;

	@Column(length = 10, nullable = true)
	private String segEntrada;

	@Column(length = 10, nullable = true)
	private String segEntradaIntervalo;

	@Column(length = 10, nullable = true)
	private String segSaida;

	@Column(length = 10, nullable = true)
	private String segSaidaIntervalo;

	@Column(length = 10, nullable = true)
	private String terEntrada;

	@Column(length = 10, nullable = true)
	private String terEntradaIntervalo;

	@Column(length = 10, nullable = true)
	private String terSaida;

	@Column(length = 10, nullable = true)
	private String terSaidaIntervalo;

	@Column(length = 10, nullable = true)
	private String quaEntrada;

	@Column(length = 10, nullable = true)
	private String quaEntradaIntervalo;

	@Column(length = 10, nullable = true)
	private String quaSaida;

	@Column(length = 10, nullable = true)
	private String quaSaidaIntervalo;

	@Column(length = 10, nullable = true)
	private String quiEntrada;

	@Column(length = 10, nullable = true)
	private String quiEntradaIntervalo;

	@Column(length = 10, nullable = true)
	private String quiSaida;

	@Column(length = 10, nullable = true)
	private String quiSaidaIntervalo;

	@Column(length = 10, nullable = true)
	private String sexEntrada;

	@Column(length = 10, nullable = true)
	private String sexEntradaIntervalo;

	@Column(length = 10, nullable = true)
	private String sexSaidaIntervalo;

	@Column(length = 10, nullable = true)
	private String sexSaida;

	@Column(length = 10, nullable = true)
	private String sabEntrada;

	@Column(length = 10, nullable = true)
	private String sabEntradaIntervalo;

	@Column(length = 10, nullable = true)
	private String sabSaida;

	@Column(length = 10, nullable = true)
	private String sabSaidaIntervalo;

	@Column(length = 10, nullable = true)
	private String domEntrada;

	@Column(length = 10, nullable = true)
	private String domEntradaIntervalo;

	@Column(length = 10, nullable = true)
	private String domSaida;

	@Column(length = 10, nullable = true)
	private String domSaidaIntervalo;

	@Column(length = 10, nullable = true)
	private String horarioEntradaIj;

	@Column(length = 10, nullable = true)
	private String horarioSaidaIj;

	@Column(length = 10, nullable = true)
	private String horaDiaria;

	@Column(length = 10, nullable = true)
	private String horaSabado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoHorario() {
		return tipoHorario;
	}

	public void setTipoHorario(String tipoHorario) {
		this.tipoHorario = tipoHorario;
	}

	public String getSegEntrada() {
		return segEntrada;
	}

	public void setSegEntrada(String segEntrada) {
		this.segEntrada = segEntrada;
	}

	public String getSegEntradaIntervalo() {
		return segEntradaIntervalo;
	}

	public void setSegEntradaIntervalo(String segEntradaIntervalo) {
		this.segEntradaIntervalo = segEntradaIntervalo;
	}

	public String getSegSaida() {
		return segSaida;
	}

	public void setSegSaida(String segSaida) {
		this.segSaida = segSaida;
	}

	public String getSegSaidaIntervalo() {
		return segSaidaIntervalo;
	}

	public void setSegSaidaIntervalo(String segSaidaIntervalo) {
		this.segSaidaIntervalo = segSaidaIntervalo;
	}

	public String getTerEntrada() {
		return terEntrada;
	}

	public void setTerEntrada(String terEntrada) {
		this.terEntrada = terEntrada;
	}

	public String getTerEntradaIntervalo() {
		return terEntradaIntervalo;
	}

	public void setTerEntradaIntervalo(String terEntradaIntervalo) {
		this.terEntradaIntervalo = terEntradaIntervalo;
	}

	public String getTerSaida() {
		return terSaida;
	}

	public void setTerSaida(String terSaida) {
		this.terSaida = terSaida;
	}

	public String getTerSaidaIntervalo() {
		return terSaidaIntervalo;
	}

	public void setTerSaidaIntervalo(String terSaidaIntervalo) {
		this.terSaidaIntervalo = terSaidaIntervalo;
	}

	public String getQuaEntrada() {
		return quaEntrada;
	}

	public void setQuaEntrada(String quaEntrada) {
		this.quaEntrada = quaEntrada;
	}

	public String getQuaEntradaIntervalo() {
		return quaEntradaIntervalo;
	}

	public void setQuaEntradaIntervalo(String quaEntradaIntervalo) {
		this.quaEntradaIntervalo = quaEntradaIntervalo;
	}

	public String getQuaSaida() {
		return quaSaida;
	}

	public void setQuaSaida(String quaSaida) {
		this.quaSaida = quaSaida;
	}

	public String getQuaSaidaIntervalo() {
		return quaSaidaIntervalo;
	}

	public void setQuaSaidaIntervalo(String quaSaidaIntervalo) {
		this.quaSaidaIntervalo = quaSaidaIntervalo;
	}

	public String getQuiEntrada() {
		return quiEntrada;
	}

	public void setQuiEntrada(String quiEntrada) {
		this.quiEntrada = quiEntrada;
	}

	public String getQuiEntradaIntervalo() {
		return quiEntradaIntervalo;
	}

	public void setQuiEntradaIntervalo(String quiEntradaIntervalo) {
		this.quiEntradaIntervalo = quiEntradaIntervalo;
	}

	public String getQuiSaida() {
		return quiSaida;
	}

	public void setQuiSaida(String quiSaida) {
		this.quiSaida = quiSaida;
	}

	public String getQuiSaidaIntervalo() {
		return quiSaidaIntervalo;
	}

	public void setQuiSaidaIntervalo(String quiSaidaIntervalo) {
		this.quiSaidaIntervalo = quiSaidaIntervalo;
	}

	public String getSexEntrada() {
		return sexEntrada;
	}

	public void setSexEntrada(String sexEntrada) {
		this.sexEntrada = sexEntrada;
	}

	public String getSexEntradaIntervalo() {
		return sexEntradaIntervalo;
	}

	public void setSexEntradaIntervalo(String sexEntradaIntervalo) {
		this.sexEntradaIntervalo = sexEntradaIntervalo;
	}

	public String getSexSaidaIntervalo() {
		return sexSaidaIntervalo;
	}

	public void setSexSaidaIntervalo(String sexSaidaIntervalo) {
		this.sexSaidaIntervalo = sexSaidaIntervalo;
	}

	public String getSexSaida() {
		return sexSaida;
	}

	public void setSexSaida(String sexSaida) {
		this.sexSaida = sexSaida;
	}

	public String getSabEntrada() {
		return sabEntrada;
	}

	public void setSabEntrada(String sabEntrada) {
		this.sabEntrada = sabEntrada;
	}

	public String getSabEntradaIntervalo() {
		return sabEntradaIntervalo;
	}

	public void setSabEntradaIntervalo(String sabEntradaIntervalo) {
		this.sabEntradaIntervalo = sabEntradaIntervalo;
	}

	public String getSabSaida() {
		return sabSaida;
	}

	public void setSabSaida(String sabSaida) {
		this.sabSaida = sabSaida;
	}

	public String getSabSaidaIntervalo() {
		return sabSaidaIntervalo;
	}

	public void setSabSaidaIntervalo(String sabSaidaIntervalo) {
		this.sabSaidaIntervalo = sabSaidaIntervalo;
	}

	public String getDomEntrada() {
		return domEntrada;
	}

	public void setDomEntrada(String domEntrada) {
		this.domEntrada = domEntrada;
	}

	public String getDomEntradaIntervalo() {
		return domEntradaIntervalo;
	}

	public void setDomEntradaIntervalo(String domEntradaIntervalo) {
		this.domEntradaIntervalo = domEntradaIntervalo;
	}

	public String getDomSaida() {
		return domSaida;
	}

	public void setDomSaida(String domSaida) {
		this.domSaida = domSaida;
	}

	public String getDomSaidaIntervalo() {
		return domSaidaIntervalo;
	}

	public void setDomSaidaIntervalo(String domSaidaIntervalo) {
		this.domSaidaIntervalo = domSaidaIntervalo;
	}

	public String getHorarioEntradaIj() {
		return horarioEntradaIj;
	}

	public void setHorarioEntradaIj(String horarioEntradaIj) {
		this.horarioEntradaIj = horarioEntradaIj;
	}

	public String getHorarioSaidaIj() {
		return horarioSaidaIj;
	}

	public void setHorarioSaidaIj(String horarioSaidaIj) {
		this.horarioSaidaIj = horarioSaidaIj;
	}

	public String getHoraDiaria() {
		return horaDiaria;
	}

	public void setHoraDiaria(String horaDiaria) {
		this.horaDiaria = horaDiaria;
	}

	public String getHoraSabado() {
		return horaSabado;
	}

	public void setHoraSabado(String horaSabado) {
		this.horaSabado = horaSabado;
	}

}
