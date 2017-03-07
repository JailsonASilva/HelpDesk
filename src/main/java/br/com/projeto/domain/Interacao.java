package br.com.projeto.domain;

import java.text.SimpleDateFormat;
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
@Table(name = "interacao")
public class Interacao extends GenericDomain {

	@Column(nullable = false, length = 500)
	private String interacao;

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

	public String getInteracao() {
		return interacao;
	}

	public void setInteracao(String interacao) {
		this.interacao = interacao;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	@Transient
	public String getDataFormatada() {
		String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(data);

		return dataFormatada;
	}
}
