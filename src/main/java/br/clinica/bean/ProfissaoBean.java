package br.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.clinica.dao.ProfissaoDAO;
import br.clinica.domain.Profissao;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class ProfissaoBean implements Serializable {
	private Profissao profissao;
	private List<Profissao> profissoes;
	private FacesMessage message;
	private String busca;

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public List<Profissao> getProfissoes() {
		return profissoes;
	}

	public void setProfissoes(List<Profissao> profissoes) {
		this.profissoes = profissoes;
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
			ProfissaoDAO profissaoDAO = new ProfissaoDAO();
			profissoes = profissaoDAO.pesquisar(busca);

			if (profissoes.isEmpty() == true) {
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
		profissao = new Profissao();
	}

	public void salvar() {
		try {
			ProfissaoDAO profissaoDAO = new ProfissaoDAO();
			profissaoDAO.merge(profissao);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Salvo com Sucesso!",
					"Registro: " + profissao.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			profissao = new Profissao();

			profissoes = profissaoDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			profissao = (Profissao) evento.getComponent().getAttributes().get("profissaoSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			profissao = (Profissao) evento.getComponent().getAttributes().get("profissaoSelecionado");

			ProfissaoDAO profissaoDAO = new ProfissaoDAO();
			profissaoDAO.excluir(profissao);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + profissao.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			profissoes = profissaoDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}
}
