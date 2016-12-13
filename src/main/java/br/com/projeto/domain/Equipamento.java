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
	@Column(length = 100, nullable = false)
	private String nome;

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
	@JoinColumn(nullable = true)
	private Marca marca;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataManutencao;

	public Date getDataManutencao() {
		return dataManutencao;
	}

	public void setDataManutencao(Date dataManutencao) {
		this.dataManutencao = dataManutencao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

}
