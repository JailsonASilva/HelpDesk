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
@Table(name = "manutencao")
public class Manutencao extends GenericDomain {
	@ManyToOne
	@JoinColumn(nullable = false)
	private Equipamento equipamento;

	@Column(length = 40, nullable = false)
	private String tipo;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@Column(length = 500, nullable = false)
	private String problema;

	@Column(length = 500, nullable = false)
	private String servico;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataManutencao;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataProxima;

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getProblema() {
		return problema;
	}

	public void setProblema(String problema) {
		this.problema = problema;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public Date getDataManutencao() {
		return dataManutencao;
	}

	public void setDataManutencao(Date dataManutencao) {
		this.dataManutencao = dataManutencao;
	}

	public Date getDataProxima() {
		return dataProxima;
	}

	public void setDataProxima(Date dataProxima) {
		this.dataProxima = dataProxima;
	}

}
