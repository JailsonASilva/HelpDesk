package br.com.projeto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table
public class Acesso extends GenericDomain {
	@Column(length = 100, nullable = false)
	private String nome;

	@Column(nullable = true)
	private Boolean acesso;

	@Column(nullable = true)
	private Boolean categoria;

	@Column(nullable = true)
	private Boolean cliente;

	@Column(nullable = true)
	private Boolean departamento;

	@Column(nullable = true)
	private Boolean equipamento;

	@Column(nullable = true)
	private Boolean manutencao;

	@Column(nullable = true)
	private Boolean marca;

	@Column(nullable = true)
	private Boolean nivel;

	@Column(nullable = true)
	private Boolean proximaManutencao;

	@Column(nullable = true)
	private Boolean ticketExterno;

	@Column(nullable = true)
	private Boolean ticketInterno;

	@Column(nullable = true)
	private Boolean tipoEquipamento;

	@Column(nullable = true)
	private Boolean usuario;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAcesso() {
		return acesso;
	}

	public void setAcesso(Boolean acesso) {
		this.acesso = acesso;
	}

	public Boolean getCategoria() {
		return categoria;
	}

	public void setCategoria(Boolean categoria) {
		this.categoria = categoria;
	}

	public Boolean getCliente() {
		return cliente;
	}

	public void setCliente(Boolean cliente) {
		this.cliente = cliente;
	}

	public Boolean getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Boolean departamento) {
		this.departamento = departamento;
	}

	public Boolean getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Boolean equipamento) {
		this.equipamento = equipamento;
	}

	public Boolean getManutencao() {
		return manutencao;
	}

	public void setManutencao(Boolean manutencao) {
		this.manutencao = manutencao;
	}

	public Boolean getMarca() {
		return marca;
	}

	public void setMarca(Boolean marca) {
		this.marca = marca;
	}

	public Boolean getNivel() {
		return nivel;
	}

	public void setNivel(Boolean nivel) {
		this.nivel = nivel;
	}

	public Boolean getProximaManutencao() {
		return proximaManutencao;
	}

	public void setProximaManutencao(Boolean proximaManutencao) {
		this.proximaManutencao = proximaManutencao;
	}

	public Boolean getTicketExterno() {
		return ticketExterno;
	}

	public void setTicketExterno(Boolean ticketExterno) {
		this.ticketExterno = ticketExterno;
	}

	public Boolean getTicketInterno() {
		return ticketInterno;
	}

	public void setTicketInterno(Boolean ticketInterno) {
		this.ticketInterno = ticketInterno;
	}

	public Boolean getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(Boolean tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public Boolean getUsuario() {
		return usuario;
	}

	public void setUsuario(Boolean usuario) {
		this.usuario = usuario;
	}

}
