package br.com.projeto.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.projeto.dao.TicketDAO;
import br.com.projeto.domain.Ticket;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class BarraFerramentaBean implements Serializable {
	private Ticket ticket;
	private List<Ticket> tickets;

	private FacesMessage message;

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void resumoAtendente() {
		try {
			TicketDAO ticketDAO = new TicketDAO();
			tickets = ticketDAO.resumoAtendente();

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Listar Resumo.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}

	}

	public void ticketInterno() {
		try {
			Faces.redirect("./pages/ticketInterno.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível Desconectar. Erro: " + erro.getMessage());
		}
	}

	public void ticketExterno() {
		try {
			Faces.redirect("./pages/ticketExterno.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível Desconectar. Erro: " + erro.getMessage());
		}
	}

	public void ticketDepartamento() {
		try {
			Faces.redirect("./pages/ticketDepartamento.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível Desconectar. Erro: " + erro.getMessage());
		}
	}

	public void ticketUsuario() {
		try {
			Faces.redirect("./pages/ticketUsuario.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível Desconectar. Erro: " + erro.getMessage());
		}
	}

	public void meusTickets() {
		try {
			Faces.redirect("./pages/meusTickets.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível Desconectar. Erro: " + erro.getMessage());
		}
	}

	public void artigo() {
		try {
			Faces.redirect("./pages/artigo.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível Desconectar. Erro: " + erro.getMessage());
		}
	}

}
