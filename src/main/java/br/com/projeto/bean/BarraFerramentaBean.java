package br.com.projeto.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.projeto.domain.Ticket;
import br.com.projeto.domain.Usuario;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class BarraFerramentaBean implements Serializable {
	private Ticket ticket;
	private List<Ticket> tickets;
	private Usuario usuario;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@PostConstruct
	public void inicializar() {

		AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
		usuario = autenticacaoBean.getUsuarioLogado();

	}

	public void ticketInterno() {
		try {
			if (usuario.getAcesso().getTicketInterno() == true) {

				Faces.redirect("./pages/ticketInterno.xhtml");

			} else {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Aviso!", "Acesso não Permitido"));
			}

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível acessar Ticket Interno. Erro: " + erro.getMessage());
		}
	}

	public void ticketExterno() {
		try {

			if (usuario.getAcesso().getTicketExterno() == true) {

				Faces.redirect("./pages/ticketExterno.xhtml");

			} else {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Aviso!", "Acesso não Permitido"));
			}

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível acessar Ticket Externo. Erro: " + erro.getMessage());
		}
	}

	public void ticketDepartamento() {
		try {

			if (usuario.getAcesso().getTicketDepartamento() == true) {

				Faces.redirect("./pages/ticketDepartamento.xhtml");

			} else {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Aviso!", "Acesso não Permitido"));
			}

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível acessar Ticket por Departamento. Erro: " + erro.getMessage());
		}
	}

	public void ticketUsuario() {
		try {

			if (usuario.getAcesso().getTicketUsuario() == true) {

				Faces.redirect("./pages/ticketUsuario.xhtml");

			} else {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Aviso!", "Acesso não Permitido"));
			}

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

			if (usuario.getAcesso().getArtigo() == true) {

				Faces.redirect("./pages/artigo.xhtml");

			} else {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Aviso!", "Acesso não Permitido"));
			}

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível acessar Base de Conhecimento. Erro: " + erro.getMessage());
		}
	}

	public void mensagem() {
		try {

			if (usuario.getAcesso().getMensagem() == true) {

				Faces.redirect("./pages/notificacao.xhtml");

			} else {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Aviso!", "Acesso não Permitido"));
			}

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível acessar Mensagens. Erro: " + erro.getMessage());
		}
	}

	public void evento() {
		try {

			if (usuario.getAcesso().getEvento() == true) {

				Faces.redirect("./pages/evento.xhtml");

			} else {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Aviso!", "Acesso não Permitido"));
			}

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível acessar Evento. Erro: " + erro.getMessage());
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