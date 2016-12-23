package br.com.projeto.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.NivelDAO;
import br.com.projeto.domain.Nivel;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class NivelBean implements Serializable {
	private Nivel nivel;
	private List<Nivel> nivels;

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

	public List<Nivel> getNivels() {
		return nivels;
	}

	public void setNivels(List<Nivel> nivels) {
		this.nivels = nivels;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public void pesquisar() {
		try {
			NivelDAO nivelDAO = new NivelDAO();
			nivels = nivelDAO.pesquisar(busca);

			nivel = null;

			if (nivels.isEmpty() == true) {
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
		nivel = new Nivel();
	}

	public void salvar() {
		try {
			NivelDAO nivelDAO = new NivelDAO();
			nivelDAO.merge(nivel);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			nivels = nivelDAO.listar("nome");

			nivel = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			NivelDAO nivelDAO = new NivelDAO();
			nivelDAO.excluir(nivel);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + nivel.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			nivels = nivelDAO.listar("nome");

			nivel = null;

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
		nivel = null;
	}
}
