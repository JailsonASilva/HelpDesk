package br.com.projeto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "internoNotificacao")
public class InternoNotificacao extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Interno interno;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Ticket ticket;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@Column(nullable = true)
	private Boolean lido;

	public Interno getInterno() {
		return interno;
	}

	public void setInterno(Interno interno) {
		this.interno = interno;
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

	public Boolean getLido() {
		return lido;
	}

	public void setLido(Boolean lido) {
		this.lido = lido;
	}

	@Transient
	public String getLidoFormatado() {
		String lidoFormatado = "Não";

		if (lido) {
			lidoFormatado = "Sim";
		}

		return lidoFormatado;
	}

}
