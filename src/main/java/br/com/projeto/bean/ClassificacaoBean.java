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

import br.com.projeto.dao.ClassificacaoDAO;
import br.com.projeto.domain.Classificacao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClassificacaoBean implements Serializable {
	private Classificacao classificacao;
	private List<Classificacao> classificacaos;

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

	public List<Classificacao> getClassificacaos() {
		return classificacaos;
	}

	public void setClassificacaos(List<Classificacao> classificacaos) {
		this.classificacaos = classificacaos;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public void pesquisar() {
		try {
			ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();
			classificacaos = classificacaoDAO.pesquisar(busca);

			classificacao = null;

			if (classificacaos.isEmpty() == true) {
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
		classificacao = new Classificacao();
	}

	public void salvar() {
		try {
			ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();
			classificacaoDAO.merge(classificacao);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			classificacaos = classificacaoDAO.listar("nome");

			classificacao = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();
			classificacaoDAO.excluir(classificacao);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + classificacao.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			classificacaos = classificacaoDAO.listar("nome");

			classificacao = null;

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
		classificacao = null;
	}
}
