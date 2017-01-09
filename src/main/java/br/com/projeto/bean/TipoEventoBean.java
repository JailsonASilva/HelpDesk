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

import br.com.projeto.dao.TipoEventoDAO;
import br.com.projeto.domain.TipoEvento;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TipoEventoBean implements Serializable {
	private TipoEvento tipoEvento;
	private List<TipoEvento> tipoEventos;

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

	public List<TipoEvento> getTipoEventos() {
		return tipoEventos;
	}

	public void setTipoEventos(List<TipoEvento> tipoEventos) {
		this.tipoEventos = tipoEventos;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public void pesquisar() {
		try {
			TipoEventoDAO tipoEventoDAO = new TipoEventoDAO();
			tipoEventos = tipoEventoDAO.pesquisar(busca);

			tipoEvento = null;

			if (tipoEventos.isEmpty() == true) {
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
		tipoEvento = new TipoEvento();
	}

	public void salvar() {
		try {
			TipoEventoDAO tipoEventoDAO = new TipoEventoDAO();
			tipoEventoDAO.merge(tipoEvento);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			tipoEventos = tipoEventoDAO.listar("nome");

			tipoEvento = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			TipoEventoDAO tipoEventoDAO = new TipoEventoDAO();
			tipoEventoDAO.excluir(tipoEvento);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + tipoEvento.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			tipoEventos = tipoEventoDAO.listar("nome");

			tipoEvento = null;

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
		tipoEvento = null;
	}
}
