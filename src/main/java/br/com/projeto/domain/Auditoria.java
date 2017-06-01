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
@Table(name = "auditoria")
public class Auditoria extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@Column(length = 800, nullable = false)
	private String acao;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date data;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date hora;

	@Column(length = 60, nullable = true)
	private String ip;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
