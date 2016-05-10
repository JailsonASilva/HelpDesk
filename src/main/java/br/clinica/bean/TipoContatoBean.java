package br.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.clinica.dao.TipoContatoDAO;
import br.clinica.domain.TipoContato;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class TipoContatoBean implements Serializable {
	private TipoContato tipoContato;
	private List<TipoContato> tipoContatos;
	private FacesMessage message;
	private String busca;

	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public List<TipoContato> getTipoContatos() {
		return tipoContatos;
	}

	public void setTipoContatos(List<TipoContato> tipoContatos) {
		this.tipoContatos = tipoContatos;
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
			TipoContatoDAO tipoContatoDAO = new TipoContatoDAO();
			tipoContatos = tipoContatoDAO.pesquisar(busca);

			if (tipoContatos.isEmpty() == true) {
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
		tipoContato = new TipoContato();
	}

	public void salvar() {
		try {
			TipoContatoDAO tipoContatoDAO = new TipoContatoDAO();
			tipoContatoDAO.merge(tipoContato);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Salvo com Sucesso!",
					"Registro: " + tipoContato.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			tipoContato = new TipoContato();

			tipoContatos = tipoContatoDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			tipoContato = (TipoContato) evento.getComponent().getAttributes().get("tipoSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			tipoContato = (TipoContato) evento.getComponent().getAttributes().get("tipoSelecionado");

			TipoContatoDAO tipoContatoDAO = new TipoContatoDAO();
			tipoContatoDAO.excluir(tipoContato);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + tipoContato.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			tipoContatos = tipoContatoDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

}
