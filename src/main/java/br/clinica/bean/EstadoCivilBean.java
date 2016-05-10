package br.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.clinica.dao.EstadoCivilDAO;
import br.clinica.domain.EstadoCivil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoCivilBean implements Serializable {
	private EstadoCivil estadoCivil;
	private List<EstadoCivil> estadoCivis;
	private FacesMessage message;
	private String busca;

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public List<EstadoCivil> getEstadoCivis() {
		return estadoCivis;
	}

	public void setEstadoCivis(List<EstadoCivil> estadoCivis) {
		this.estadoCivis = estadoCivis;
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
			EstadoCivilDAO estadoCivilDAO = new EstadoCivilDAO();
			estadoCivis = estadoCivilDAO.pesquisar(busca);

			if (estadoCivis.isEmpty() == true) {
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
		estadoCivil = new EstadoCivil();
	}

	public void salvar() {
		try {
			EstadoCivilDAO estadoCivilDAO = new EstadoCivilDAO();
			estadoCivilDAO.merge(estadoCivil);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Salvo com Sucesso!",
					"Registro: " + estadoCivil.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			estadoCivil = new EstadoCivil();

			estadoCivis = estadoCivilDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			estadoCivil = (EstadoCivil) evento.getComponent().getAttributes().get("estadoCivilSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			estadoCivil = (EstadoCivil) evento.getComponent().getAttributes().get("estadoCivilSelecionado");

			EstadoCivilDAO estadoCivilDAO = new EstadoCivilDAO();
			estadoCivilDAO.excluir(estadoCivil);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + estadoCivil.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			estadoCivis = estadoCivilDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

}
