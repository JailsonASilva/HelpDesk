package br.com.projeto.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table
public class Ocorrencia extends GenericDomain {
	@Column(length = 600, nullable = false)
	private String ocorrencia;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Ticket ticket;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;

	@Temporal(TemporalType.TIME)
	@Column(nullable = true)
	private Date hora;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@Column(nullable = true)
	private Boolean emailEnviado;

	@Column(nullable = true)
	private String codigoAnexo;

	@Column(nullable = true)
	private String tipoAnexo;

	@Transient
	private String caminho;

	public String getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getEmailEnviado() {
		return emailEnviado;
	}

	public void setEmailEnviado(Boolean emailEnviado) {
		this.emailEnviado = emailEnviado;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getCodigoAnexo() {
		return codigoAnexo;
	}

	public void setCodigoAnexo(String codigoAnexo) {
		this.codigoAnexo = codigoAnexo;
	}

	@Transient
	public String getAnexo() {
		String anexo = "Não";

		if (codigoAnexo != null) {
			anexo = "Sim";
		}

		return anexo;
	}

	public String getTipoAnexo() {
		return tipoAnexo;
	}

	public void setTipoAnexo(String tipoAnexo) {
		this.tipoAnexo = tipoAnexo;
	}

}
