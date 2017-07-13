package br.com.projeto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.ColaboradorDAO;
import br.com.projeto.dao.OcorrenciaProntuarioDAO;
import br.com.projeto.dao.ProntuarioDAO;
import br.com.projeto.domain.Colaborador;
import br.com.projeto.domain.OcorrenciaProntuario;
import br.com.projeto.domain.Prontuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProntuarioBean implements Serializable {

	private Prontuario prontuario;
	private List<Prontuario> prontuarios;

	private Colaborador colaborador;
	private List<Colaborador> colaboradores;

	private OcorrenciaProntuario ocorrenciaProntuario;
	private List<OcorrenciaProntuario> ocorrenciaProntuarios;

	private FacesMessage message;
	private String busca;

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public List<Prontuario> getProntuarios() {
		return prontuarios;
	}

	public void setProntuarios(List<Prontuario> prontuarios) {
		this.prontuarios = prontuarios;
	}

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

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public OcorrenciaProntuario getOcorrenciaProntuario() {
		return ocorrenciaProntuario;
	}

	public void setOcorrenciaProntuario(OcorrenciaProntuario ocorrenciaProntuario) {
		this.ocorrenciaProntuario = ocorrenciaProntuario;
	}

	public List<OcorrenciaProntuario> getOcorrenciaProntuarios() {
		return ocorrenciaProntuarios;
	}

	public void setOcorrenciaProntuarios(List<OcorrenciaProntuario> ocorrenciaProntuarios) {
		this.ocorrenciaProntuarios = ocorrenciaProntuarios;
	}

	@PostConstruct
	public void inicializar() {
		try {
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradores = colaboradorDAO.listar("nome");
			
			OcorrenciaProntuarioDAO ocorrenciaProntuarioDAO = new OcorrenciaProntuarioDAO();
			ocorrenciaProntuarios = ocorrenciaProntuarioDAO.listar("nome");

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Inicializar Tabelas.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void pesquisar() {
		try {
			ProntuarioDAO prontuarioDAO = new ProntuarioDAO();
			prontuarios = prontuarioDAO.pesquisarProntuario(busca);

			prontuario = null;
			
			ocorrenciaProntuario = new OcorrenciaProntuario();

			if (prontuarios.isEmpty() == true) {
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
		prontuario = new Prontuario();
		ocorrenciaProntuario = new OcorrenciaProntuario();
	}

	public void salvar() {
		try {
			ProntuarioDAO prontuarioDAO = new ProntuarioDAO();
			prontuarioDAO.merge(prontuario);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			prontuarios = prontuarioDAO.pesquisarProntuario("");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			prontuario = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			ProntuarioDAO prontuarioDAO = new ProntuarioDAO();
			prontuarioDAO.excluir(prontuario);

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Excluído com Sucesso"));

			prontuarios = prontuarioDAO.pesquisarProntuario("");

			prontuario = null;

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
		prontuario = null;
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

	public void editar(ActionEvent evento) {
		try {
			prontuario = (Prontuario) evento.getComponent().getAttributes().get("registroSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluirAtalho(ActionEvent evento) {
		try {
			prontuario = (Prontuario) evento.getComponent().getAttributes().get("registroSelecionado");

			ProntuarioDAO prontuarioDAO = new ProntuarioDAO();
			prontuarioDAO.excluir(prontuario);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + prontuario.getColaborador().getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			prontuarios = prontuarioDAO.pesquisarProntuario("");

			prontuario = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}
	
	public void salvarOcorrencia() {
		try {
			OcorrenciaProntuarioDAO ocorrenciaProntuarioDAO = new OcorrenciaProntuarioDAO();
			ocorrenciaProntuarioDAO.merge(ocorrenciaProntuario);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoOcorrencia').hide();");

			ocorrenciaProntuarios = ocorrenciaProntuarioDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			ocorrenciaProntuario = new OcorrenciaProntuario();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

}
