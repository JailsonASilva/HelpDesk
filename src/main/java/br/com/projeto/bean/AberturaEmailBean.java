package br.com.projeto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.AberturaEmailDAO;
import br.com.projeto.dao.DepartamentoDAO;
import br.com.projeto.domain.AberturaEmail;
import br.com.projeto.domain.Departamento;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AberturaEmailBean implements Serializable {
	private AberturaEmail aberturaEmail;
	private List<AberturaEmail> aberturaEmails;

	private Departamento departamento;
	private List<Departamento> departamentos;

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

	public List<AberturaEmail> getAberturaEmails() {
		return aberturaEmails;
	}

	public void setAberturaEmails(List<AberturaEmail> aberturaEmails) {
		this.aberturaEmails = aberturaEmails;
	}

	public AberturaEmail getAberturaEmail() {
		return aberturaEmail;
	}

	public void setAberturaEmail(AberturaEmail aberturaEmail) {
		this.aberturaEmail = aberturaEmail;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@PostConstruct
	public void inicializar() {
		try {
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.listar("nome");

			departamento = new Departamento();

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Inicializar.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void pesquisar() {
		try {
			AberturaEmailDAO aberturaEmailDAO = new AberturaEmailDAO();
			aberturaEmails = aberturaEmailDAO.pesquisarDepartamento(busca);

			aberturaEmail = null;

			if (aberturaEmails.isEmpty() == true) {
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
		aberturaEmail = new AberturaEmail();
		aberturaEmail.setControle(0L);
	}

	public void salvar() {
		try {
			AberturaEmailDAO aberturaEmailDAO = new AberturaEmailDAO();
			aberturaEmailDAO.merge(aberturaEmail);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			aberturaEmails = aberturaEmailDAO.listar();

			aberturaEmail = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			AberturaEmailDAO aberturaEmailDAO = new AberturaEmailDAO();
			aberturaEmailDAO.excluir(aberturaEmail);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + aberturaEmail.getDepartamento().getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			aberturaEmails = aberturaEmailDAO.listar();

			aberturaEmail = null;

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
		aberturaEmail = null;
	}

	public void salvarDepartamento() {
		try {
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentoDAO.merge(departamento);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoDepartamento').hide();");

			departamento = new Departamento();

			departamentos = departamentoDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}
}