package br.com.projeto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "cliente")
public class Cliente extends GenericDomain {
	@Column(length = 100, nullable = false)
	private String nome;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Departamento departamento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Transient
	public String getNomeDepartamento() {
		return getDepartamento() == null ? null : getDepartamento().getNome();
	}

	@Transient
	public void setNomeDepartamento(String nomeDepartamento) {
		departamento.setNome(nomeDepartamento);
	}
}
