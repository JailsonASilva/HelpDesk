package br.com.projeto.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.projeto.dao.TicketDAO;
import br.com.projeto.domain.Ticket;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class AberturaDepartamentoBean implements Serializable {
	private List<Ticket> tickets;
	private FacesMessage message;
	private Date dataInicial;
	private Date dataFinal;

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	@PostConstruct
	public void inicializar() {
		dataInicial = new Date();
		dataFinal = new Date();
	}

	@SuppressWarnings("unchecked")
	public void pesquisar() {
		try {
			TicketDAO ticketDAO = new TicketDAO();
			tickets = ticketDAO.aberturaDepartamento(dataInicial, dataFinal);

			if (tickets.size() == 0) {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Aviso!", "Nenhum Registro foi Encontrado!"));
			}

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Pesquisar.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}
}
