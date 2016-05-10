package br.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.clinica.dao.FinalizadoraDAO;
import br.clinica.domain.Finalizadora;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FinalizadoraBean implements Serializable {
	private Finalizadora finalizadora;
	private List<Finalizadora> finalizadoras;
	private FacesMessage message;
	private String busca;

	public Finalizadora getFinalizadora() {
		return finalizadora;
	}

	public void setFinalizadora(Finalizadora finalizadora) {
		this.finalizadora = finalizadora;
	}

	public List<Finalizadora> getFinalizadoras() {
		return finalizadoras;
	}

	public void setFinalizadoras(List<Finalizadora> finalizadoras) {
		this.finalizadoras = finalizadoras;
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
			FinalizadoraDAO finalizadoraDAO = new FinalizadoraDAO();
			finalizadoras = finalizadoraDAO.pesquisar(busca);

			if (finalizadoras.isEmpty() == true) {
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
		finalizadora = new Finalizadora();
	}

	public void salvar() {
		try {
			FinalizadoraDAO finalizadoraDAO = new FinalizadoraDAO();
			finalizadoraDAO.merge(finalizadora);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Salvo com Sucesso!",
					"Registro: " + finalizadora.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			finalizadora = new Finalizadora();
			finalizadoras = finalizadoraDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			finalizadora = (Finalizadora) evento.getComponent().getAttributes().get("finalizadoraSelecionada");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			finalizadora = (Finalizadora) evento.getComponent().getAttributes().get("finalizadoraSelecionada");

			FinalizadoraDAO finalizadoraDAO = new FinalizadoraDAO();
			finalizadoraDAO.excluir(finalizadora);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + finalizadora.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			finalizadoras = finalizadoraDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

}
