package br.com.projeto.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

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

	public void ticketInterno() {
		try {
			Faces.redirect("./pages/ticketInterno.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível acessar Ticket Interno. Erro: " + erro.getMessage());
		}
	}

	public void ticketExterno() {
		try {
			Faces.redirect("./pages/ticketExterno.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível acessar Ticket Externo. Erro: " + erro.getMessage());
		}
	}

	public void ticketDepartamento() {
		try {
			Faces.redirect("./pages/ticketDepartamento.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível acessar Ticket por Departamento. Erro: " + erro.getMessage());
		}
	}

	public void ticketUsuario() {
		try {
			Faces.redirect("./pages/ticketUsuario.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível acessar Meus Atendimentos. Erro: " + erro.getMessage());
		}
	}

	public void meusTickets() {
		try {
			Faces.redirect("./pages/meusTickets.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível acessar Meus Ticket's. Erro: " + erro.getMessage());
		}
	}

	public void artigo() {
		try {
			Faces.redirect("./pages/artigo.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível acessar Base de Conhecimento. Erro: " + erro.getMessage());
		}
	}

	public void trocarSenha() {
		try {
			Faces.redirect("./pages/trocarSenha.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível acessar Troca de Senha. Erro: " + erro.getMessage());
		}
	}

}