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

import br.com.projeto.dao.OcorrenciaProntuarioDAO;
import br.com.projeto.domain.OcorrenciaProntuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class OcorrenciaProntuarioBean implements Serializable {
	private OcorrenciaProntuario ocorrenciaProntuario;
	private List<OcorrenciaProntuario> ocorrenciaProntuarios;

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

	public List<OcorrenciaProntuario> getOcorrenciaProntuarios() {
		return ocorrenciaProntuarios;
	}

	public void setOcorrenciaProntuarios(List<OcorrenciaProntuario> ocorrenciaProntuarios) {
		this.ocorrenciaProntuarios = ocorrenciaProntuarios;
	}

	public OcorrenciaProntuario getOcorrenciaProntuario() {
		return ocorrenciaProntuario;
	}

	public void setOcorrenciaProntuario(OcorrenciaProntuario ocorrenciaProntuario) {
		this.ocorrenciaProntuario = ocorrenciaProntuario;
	}

	public void pesquisar() {
		try {
			OcorrenciaProntuarioDAO ocorrenciaProntuarioDAO = new OcorrenciaProntuarioDAO();
			ocorrenciaProntuarios = ocorrenciaProntuarioDAO.pesquisar(busca);

			ocorrenciaProntuario = null;

			if (ocorrenciaProntuarios.isEmpty() == true) {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Registro não Encontrado!",
						"Nenhum Registro foi Encontrado! Por favor Tente Novamente."));
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
		ocorrenciaProntuario = new OcorrenciaProntuario();
	}

	public void salvar() {
		try {
			OcorrenciaProntuarioDAO ocorrenciaProntuarioDAO = new OcorrenciaProntuarioDAO();
			ocorrenciaProntuarioDAO.merge(ocorrenciaProntuario);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			ocorrenciaProntuarios = ocorrenciaProntuarioDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			ocorrenciaProntuario = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			OcorrenciaProntuarioDAO ocorrenciaProntuarioDAO = new OcorrenciaProntuarioDAO();
			ocorrenciaProntuarioDAO.excluir(ocorrenciaProntuario);

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Excluído com Sucesso"));

			ocorrenciaProntuarios = ocorrenciaProntuarioDAO.listar("nome");

			ocorrenciaProntuario = null;

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
		ocorrenciaProntuario = null;
	}

	public void duploClique(SelectEvent evento) {
		try {
			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').show();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}
}