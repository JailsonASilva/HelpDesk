package br.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.clinica.dao.BairroDAO;
import br.clinica.dao.CidadeDAO;
import br.clinica.domain.Bairro;
import br.clinica.domain.Cidade;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class BairroBean implements Serializable {
	private Bairro bairro;
	private List<Bairro> bairros;
	
	private List<Cidade> cidades;

	private FacesMessage message;
	private String busca;

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
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
	
	@PostConstruct
	public void carregarTabelas() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu ao Tentar Carregar Tabelas.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void pesquisar() {
		try {
			BairroDAO bairroDAO = new BairroDAO();
			bairros = bairroDAO.pesquisar(busca);

			if (bairros.isEmpty() == true) {
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
		bairro = new Bairro();
	}

	public void salvar() {
		try {
			BairroDAO bairroDAO = new BairroDAO();
			bairroDAO.merge(bairro);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Salvo com Sucesso!",
					"Registro: " + bairro.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			bairro = new Bairro();

			bairros = bairroDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			bairro = (Bairro) evento.getComponent().getAttributes().get("bairroSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			bairro = (Bairro) evento.getComponent().getAttributes().get("bairroSelecionado");

			BairroDAO bairroDAO = new BairroDAO();
			bairroDAO.excluir(bairro);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + bairro.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			bairros = bairroDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}
}
