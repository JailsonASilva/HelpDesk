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

import br.com.projeto.dao.ColaboradorDAO;
import br.com.projeto.domain.Colaborador;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ColaboradorBean implements Serializable {

	private Colaborador colaborador;
	private List<Colaborador> colaboradores;

	private FacesMessage message;
	private String busca;

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

	public void pesquisar() {
		try {
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradores = colaboradorDAO.pesquisar(busca);

			colaborador = null;

			if (colaboradores.isEmpty() == true) {
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
		colaborador = new Colaborador();
	}

	public void salvar() {
		try {
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradorDAO.merge(colaborador);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			colaboradores = colaboradorDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			colaborador = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradorDAO.excluir(colaborador);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + colaborador.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			colaboradores = colaboradorDAO.listar("nome");

			colaborador = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}
	
	public void excluirAtalho(ActionEvent evento) {
		try {
			colaborador = (Colaborador) evento.getComponent().getAttributes().get("registroSelecionado");
			
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradorDAO.excluir(colaborador);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + colaborador.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			colaboradores = colaboradorDAO.listar("nome"); 

			colaborador = null;

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
		colaborador = null;
	}

	public void editar(ActionEvent evento) {
		try {
			colaborador = (Colaborador) evento.getComponent().getAttributes().get("registroSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
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