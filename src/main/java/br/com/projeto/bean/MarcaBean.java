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

import br.com.projeto.dao.MarcaDAO;
import br.com.projeto.domain.Marca;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MarcaBean implements Serializable {
	private Marca marca;
	private List<Marca> marcas;

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

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public void pesquisar() {
		try {
			MarcaDAO marcaDAO = new MarcaDAO();
			marcas = marcaDAO.pesquisar(busca);

			marca = null;

			if (marcas.isEmpty() == true) {
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
		marca = new Marca();
	}

	public void salvar() {
		try {
			MarcaDAO marcaDAO = new MarcaDAO();
			marcaDAO.merge(marca);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			marcas = marcaDAO.listar("nome");

			marca = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			MarcaDAO marcaDAO = new MarcaDAO();
			marcaDAO.excluir(marca);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + marca.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			marcas = marcaDAO.listar("nome");

			marca = null;

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
		marca = null;
	}
}