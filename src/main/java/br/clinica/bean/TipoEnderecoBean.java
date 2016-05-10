package br.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.clinica.dao.TipoEnderecoDAO;
import br.clinica.domain.TipoEndereco;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class TipoEnderecoBean implements Serializable {
	private TipoEndereco tipoEndereco;
	private List<TipoEndereco> tipoEnderecos;
	private FacesMessage message;
	private String busca;

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public List<TipoEndereco> getTipoEnderecos() {
		return tipoEnderecos;
	}

	public void setTipoEnderecos(List<TipoEndereco> tipoEnderecos) {
		this.tipoEnderecos = tipoEnderecos;
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
			TipoEnderecoDAO tipoEnderecoDAO = new TipoEnderecoDAO();
			tipoEnderecos = tipoEnderecoDAO.pesquisar(busca);

			if (tipoEnderecos.isEmpty() == true) {
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
		tipoEndereco = new TipoEndereco();
	}

	public void salvar() {
		try {
			TipoEnderecoDAO tipoEnderecoDAO = new TipoEnderecoDAO();
			tipoEnderecoDAO.merge(tipoEndereco);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Salvo com Sucesso!",
					"Registro: " + tipoEndereco.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			tipoEndereco = new TipoEndereco();

			tipoEnderecos = tipoEnderecoDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			tipoEndereco = (TipoEndereco) evento.getComponent().getAttributes().get("tipoSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			tipoEndereco = (TipoEndereco) evento.getComponent().getAttributes().get("tipoSelecionado");

			TipoEnderecoDAO tipoEnderecoDAO = new TipoEnderecoDAO();
			tipoEnderecoDAO.excluir(tipoEndereco);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + tipoEndereco.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			tipoEnderecos = tipoEnderecoDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}	

}
