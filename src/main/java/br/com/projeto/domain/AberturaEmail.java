package br.com.projeto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "abertura_email")
public class AberturaEmail extends GenericDomain {
	@ManyToOne
	@JoinColumn(nullable = false)
	private Departamento departamento;

	@Column(nullable = false, length = 100)
	private String host;

	@Column(nullable = false, length = 100)
	private String user;

	@Column(nullable = false, length = 100)
	private String senha;

	@Column(nullable = false)
	private Long porta;

	@Column(nullable = true)
	private Long controle;

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getPorta() {
		return porta;
	}

	public void setPorta(Long porta) {
		this.porta = porta;
	}

	public Long getControle() {
		return controle;
	}

	public void setControle(Long controle) {
		this.controle = controle;
	}

}
