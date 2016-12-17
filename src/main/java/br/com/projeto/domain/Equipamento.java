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
@Table(name = "equipamento")
public class Equipamento extends GenericDomain {
	@ManyToOne
	@JoinColumn(nullable = false)
	private TipoEquipamento tipoEquipamento;

	@Column(length = 40, nullable = true)
	private String serie;

	@Column(length = 40, nullable = true)
	private String patrimonio;

	@Column(length = 40, nullable = true)
	private String modelo;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Departamento departamento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Marca marca;

	@Column(length = 40, nullable = false)
	private String situacao;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date garantia_inicial;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date garantia_final;

	@Column(length = 500, nullable = true)
	private String dadosAdicionais;

	public String getDadosAdicionais() {
		return dadosAdicionais;
	}

	public void setDadosAdicionais(String dadosAdicionais) {
		this.dadosAdicionais = dadosAdicionais;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getGarantia_inicial() {
		return garantia_inicial;
	}

	public void setGarantia_inicial(Date garantia_inicial) {
		this.garantia_inicial = garantia_inicial;
	}

	public Date getGarantia_final() {
		return garantia_final;
	}

	public void setGarantia_final(Date garantia_final) {
		this.garantia_final = garantia_final;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public TipoEquipamento getTipoEquipamento() {
		return tipoEquipamento;
	}

}
