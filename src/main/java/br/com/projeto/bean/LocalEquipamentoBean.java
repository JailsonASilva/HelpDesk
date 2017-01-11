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

import br.com.projeto.dao.LocalEquipamentoDAO;
import br.com.projeto.domain.LocalEquipamento;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LocalEquipamentoBean implements Serializable {
	private LocalEquipamento localEquipamento;
	private List<LocalEquipamento> localEquipamentos;

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

	public List<LocalEquipamento> getLocalEquipamentos() {
		return localEquipamentos;
	}

	public void setLocalEquipamentos(List<LocalEquipamento> localEquipamentos) {
		this.localEquipamentos = localEquipamentos;
	}

	public LocalEquipamento getLocalEquipamento() {
		return localEquipamento;
	}

	public void setLocalEquipamento(LocalEquipamento localEquipamento) {
		this.localEquipamento = localEquipamento;
	}

	public void pesquisar() {
		try {
			LocalEquipamentoDAO localEquipamentoDAO = new LocalEquipamentoDAO();
			localEquipamentos = localEquipamentoDAO.pesquisar(busca);

			localEquipamento = null;

			if (localEquipamentos.isEmpty() == true) {
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
		localEquipamento = new LocalEquipamento();
	}

	public void salvar() {
		try {
			LocalEquipamentoDAO localEquipamentoDAO = new LocalEquipamentoDAO();
			localEquipamentoDAO.merge(localEquipamento);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			localEquipamentos = localEquipamentoDAO.listar("nome");

			localEquipamento = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			LocalEquipamentoDAO localEquipamentoDAO = new LocalEquipamentoDAO();
			localEquipamentoDAO.excluir(localEquipamento);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + localEquipamento.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			localEquipamentos = localEquipamentoDAO.listar("nome");

			localEquipamento = null;

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
		localEquipamento = null;
	}
}
