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
@Table(name = "evento")
public class Evento extends GenericDomain {
	@Column(length = 100, nullable = false)
	private String titulo;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataEvento;

	@Column(nullable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraInicial;

	@Column(nullable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraFinal;

	@ManyToOne
	@JoinColumn(nullable = true)
	private TipoEvento tipoEvento;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Ticket ticket;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Local local;

	@Column(length = 10000, nullable = true)
	private String observacao;

	@Column(nullable = true, columnDefinition = "boolean default False")
	private Boolean realizado;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataHoraInicial() {
		return dataHoraInicial;
	}

	public void setDataHoraInicial(Date dataHoraInicial) {
		this.dataHoraInicial = dataHoraInicial;
	}

	public Date getDataHoraFinal() {
		return dataHoraFinal;
	}

	public void setDataHoraFinal(Date dataHoraFinal) {
		this.dataHoraFinal = dataHoraFinal;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
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

	public Boolean getRealizado() {
		return realizado;
	}

	public void setRealizado(Boolean realizado) {
		this.realizado = realizado;
	}

	@Transient
	public String getRealizadoFormatado() {
		String realizadoFormatado = "Não";

		if (realizado) {
			realizadoFormatado = "Sim";
		}

		return realizadoFormatado;
	}
}
