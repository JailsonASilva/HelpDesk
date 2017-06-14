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

import br.com.projeto.dao.UnidadeDAO;
import br.com.projeto.domain.Unidade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UnidadeBean implements Serializable {
	private Unidade unidade;
	private List<Unidade> unidades;

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

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public void pesquisar() {
		try {
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			unidades = unidadeDAO.pesquisar(busca);

			unidade = null;

			if (unidades.isEmpty() == true) {
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
		unidade = new Unidade();
	}

	public void salvar() {
		try {
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			unidadeDAO.merge(unidade);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			unidades = unidadeDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			unidade = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			unidadeDAO.excluir(unidade);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + unidade.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			unidades = unidadeDAO.listar("nome");

			unidade = null;

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
		unidade = null;
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