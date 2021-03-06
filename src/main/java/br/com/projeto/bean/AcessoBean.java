package br.com.projeto.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.AcessoDAO;
import br.com.projeto.dao.AuditoriaDAO;
import br.com.projeto.domain.Acesso;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AcessoBean implements Serializable {
	private Acesso acesso;
	private List<Acesso> acessos;

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

	public List<Acesso> getAcessos() {
		return acessos;
	}

	public void setAcessos(List<Acesso> acessos) {
		this.acessos = acessos;
	}

	public Acesso getAcesso() {
		return acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}

	public void pesquisar() {
		try {
			AcessoDAO acessoDAO = new AcessoDAO();
			acessos = acessoDAO.pesquisar(busca);

			acesso = null;

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Pesquisou Acesso: '" + busca + "'");

			if (acessos.isEmpty() == true) {
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
		acesso = new Acesso();
	}

	public void salvar() {
		try {
			AcessoDAO acessoDAO = new AcessoDAO();
			acessoDAO.merge(acesso);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			acessos = acessoDAO.listar("nome");

			if (acesso.getCodigo() == null) {

				AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
				auditoriaDAO.auditar("Cadastro um Novo Acesso: " + acesso.getNome());

			} else {

				AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
				auditoriaDAO.auditar("Alterou um Acesso: " + acesso.getNome());
			}

			acesso = null;

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			AcessoDAO acessoDAO = new AcessoDAO();
			acessoDAO.excluir(acesso);

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Excluiu um Acesso: " + acesso.getNome());

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + acesso.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			acessos = acessoDAO.listar("nome");

			acesso = null;

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Excluído com Sucesso"));

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
		acesso = null;
	}

	public void duploClique(SelectEvent evento) {
		try {
			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').show();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

}
