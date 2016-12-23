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
@Table
public class Ticket extends GenericDomain {
	@ManyToOne
	@JoinColumn(nullable = true)
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Departamento departamento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Equipamento equipamento;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataAbertura;

	@Column(length = 40, nullable = false)
	private String status;

	@Column(length = 40, nullable = false)
	private String prioridade;

	@Column(length = 40, nullable = false)
	private String assunto;

	@Column(length = 600, nullable = false)
	private String solicitacao;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(String solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
