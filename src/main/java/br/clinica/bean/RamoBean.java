package br.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.clinica.dao.RamoDAO;
import br.clinica.domain.Ramo;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class RamoBean implements Serializable {
	private Ramo ramo;
	private List<Ramo> ramos;
	private FacesMessage message;
	private String busca;

	public Ramo getRamo() {
		return ramo;
	}

	public void setRamo(Ramo ramo) {
		this.ramo = ramo;
	}

	public List<Ramo> getRamos() {
		return ramos;
	}

	public void setRamos(List<Ramo> ramos) {
		this.ramos = ramos;
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
			RamoDAO ramoDAO = new RamoDAO();
			ramos = ramoDAO.pesquisar(busca);

			if (ramos.isEmpty() == true) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Nenhum Registro foi Encontrado! Por favor Tente Novamente.", "Registro não Encontrado!");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Pesquisar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void novo() {
		ramo = new Ramo();
	}

	public void salvar() {
		try {
			RamoDAO ramoDAO = new RamoDAO();
			ramoDAO.merge(ramo);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Salvo com Sucesso!",
					"Registro: " + ramo.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			ramo = new Ramo();

			ramos = ramoDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			ramo = (Ramo) evento.getComponent().getAttributes().get("ramoSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			ramo = (Ramo) evento.getComponent().getAttributes().get("ramoSelecionado");

			RamoDAO ramoDAO = new RamoDAO();
			ramoDAO.excluir(ramo);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + ramo.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			ramos = ramoDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}
}
