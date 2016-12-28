package br.com.projeto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.DepartamentoDAO;
import br.com.projeto.dao.TicketDAO;
import br.com.projeto.domain.Departamento;
import br.com.projeto.domain.Ticket;
import br.com.projeto.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TicketBean implements Serializable {
	private Ticket ticket;
	private List<Ticket> tickets;

	private List<Departamento> departamentos;

	private AutenticacaoBean autenticacaoBean;
	private Usuario usuario;

	private FacesMessage message;
	private String busca;

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@PostConstruct
	public void abrirTabelas() {
		try {
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.listarAtendimento();

			novo();

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Abrir as Tabelas.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void pesquisar() {
		try {
			TicketDAO ticketDAO = new TicketDAO();
			tickets = ticketDAO.pesquisar(busca);

			ticket = null;

			if (tickets.isEmpty() == true) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Nenhum Registro foi Encontrado! Por favor Tente Novamente.", "Registro não Encontrado!");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Pesquisar Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void novo() {
		autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
		usuario = autenticacaoBean.getUsuarioLogado();

		ticket = new Ticket();
		ticket.setUsuario(usuario);
		ticket.setDataAbertura(new java.util.Date());
		ticket.setStatus("Pendente");
	}

	public void salvarExterno() {
		try {
			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket Aberto com Sucesso!",
					"Para acompanhar o andamento de seu Ticket acesse o Menu Sistema - Minha Conta - Meus Chamados");

			ticket = new Ticket();

			RequestContext.getCurrentInstance().showMessageInDialog(message);

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.excluir(ticket);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + ticket.getAssunto());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			tickets = ticketDAO.listar("nome");

			ticket = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) {

	}

	public void onRowUnselect(UnselectEvent event) {
		ticket = null;
	}
}
