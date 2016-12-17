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

import br.com.projeto.dao.TipoEquipamentoDAO;
import br.com.projeto.domain.TipoEquipamento;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TipoEquipamentoBean implements Serializable {
	private TipoEquipamento tipoEquipamento;
	private List<TipoEquipamento> tipoEquipamentos;

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

	public List<TipoEquipamento> getTipoEquipamentos() {
		return tipoEquipamentos;
	}

	public void setTipoEquipamentos(List<TipoEquipamento> tipoEquipamentos) {
		this.tipoEquipamentos = tipoEquipamentos;
	}

	public TipoEquipamento getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public void pesquisar() {
		try {
			TipoEquipamentoDAO tipoEquipamentoDAO = new TipoEquipamentoDAO();
			tipoEquipamentos = tipoEquipamentoDAO.pesquisar(busca);

			tipoEquipamento = null;

			if (tipoEquipamentos.isEmpty() == true) {
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
		tipoEquipamento = new TipoEquipamento();
	}

	public void salvar() {
		try {
			TipoEquipamentoDAO tipoEquipamentoDAO = new TipoEquipamentoDAO();
			tipoEquipamentoDAO.merge(tipoEquipamento);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			tipoEquipamentos = tipoEquipamentoDAO.listar("nome");

			tipoEquipamento = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			TipoEquipamentoDAO tipoEquipamentoDAO = new TipoEquipamentoDAO();
			tipoEquipamentoDAO.excluir(tipoEquipamento);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + tipoEquipamento.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			tipoEquipamentos = tipoEquipamentoDAO.listar("nome");

			tipoEquipamento = null;

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
		tipoEquipamento = null;
	}
}
