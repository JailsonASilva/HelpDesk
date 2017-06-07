package br.com.projeto.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.AuditoriaDAO;
import br.com.projeto.dao.InternoNotificacaoDAO;
import br.com.projeto.domain.InternoNotificacao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class InternoNotificacaoBean implements Serializable {

	private InternoNotificacao internoNotificacao;
	private List<InternoNotificacao> internoNotificacoes;

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

	public InternoNotificacao getInternoNotificacao() {
		return internoNotificacao;
	}

	public void setInternoNotificacao(InternoNotificacao internoNotificacao) {
		this.internoNotificacao = internoNotificacao;
	}

	public List<InternoNotificacao> getInternoNotificacoes() {
		return internoNotificacoes;
	}

	public void setInternoNotificacoes(List<InternoNotificacao> internoNotificacoes) {
		this.internoNotificacoes = internoNotificacoes;
	}

	public void pesquisar() {
		try {
			InternoNotificacaoDAO internoNotificacaoDAO = new InternoNotificacaoDAO();
			internoNotificacoes = internoNotificacaoDAO.pesquisarMensagem(busca);

			internoNotificacao = null;

			if (internoNotificacoes.isEmpty() == true) {
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
		internoNotificacao = new InternoNotificacao();
	}

	public void salvar() {
		try {
			InternoNotificacaoDAO internoNotificacaoDAO = new InternoNotificacaoDAO();
			internoNotificacaoDAO.merge(internoNotificacao);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			internoNotificacoes = internoNotificacaoDAO.listar("nome");

			internoNotificacao = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) {

	}

	public void onRowUnselect(UnselectEvent event) {
		internoNotificacao = null;
	}

	public void confirmarLeitura(ActionEvent evento) {
		try {
			internoNotificacao = (InternoNotificacao) evento.getComponent().getAttributes()
					.get("internoNotificacaoSelecionado");
			internoNotificacao.setLido(true);

			InternoNotificacaoDAO internoNotificacaoDAO = new InternoNotificacaoDAO();
			internoNotificacaoDAO.merge(internoNotificacao);

			internoNotificacoes = internoNotificacaoDAO.pesquisarMensagem("");

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Confirmação de Leitura!", "Confirmação Efetuada com Sucesso!"));

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Confirmou Leitura Mensagem - Ticket: "
					+ internoNotificacao.getInterno().getTicket().getCodigo() + " (Notificação)");

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Listar Confirmar Leitura.", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}
}