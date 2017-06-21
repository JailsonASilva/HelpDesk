
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

import br.com.projeto.dao.EstabilidadeDAO;
import br.com.projeto.domain.Estabilidade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstabilidadeBean implements Serializable {
	private Estabilidade estabilidade;
	private List<Estabilidade> estabilidades;

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

	public List<Estabilidade> getEstabilidades() {
		return estabilidades;
	}

	public void setEstabilidades(List<Estabilidade> estabilidades) {
		this.estabilidades = estabilidades;
	}

	public Estabilidade getEstabilidade() {
		return estabilidade;
	}

	public void setEstabilidade(Estabilidade estabilidade) {
		this.estabilidade = estabilidade;
	}

	public void pesquisar() {
		try {
			EstabilidadeDAO estabilidadeDAO = new EstabilidadeDAO();
			estabilidades = estabilidadeDAO.pesquisar(busca);

			estabilidade = null;

			if (estabilidades.isEmpty() == true) {
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
		estabilidade = new Estabilidade();
	}

	public void salvar() {
		try {
			EstabilidadeDAO estabilidadeDAO = new EstabilidadeDAO();
			estabilidadeDAO.merge(estabilidade);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			estabilidades = estabilidadeDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			estabilidade = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			EstabilidadeDAO estabilidadeDAO = new EstabilidadeDAO();
			estabilidadeDAO.excluir(estabilidade);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + estabilidade.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			estabilidades = estabilidadeDAO.listar("nome");

			estabilidade = null;

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
		estabilidade = null;
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