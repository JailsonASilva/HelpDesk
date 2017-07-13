package br.com.projeto.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.ColaboradorDAO;
import br.com.projeto.dao.DependenteDAO;
import br.com.projeto.dao.ParentescoDAO;
import br.com.projeto.domain.Colaborador;
import br.com.projeto.domain.Dependente;
import br.com.projeto.domain.Parentesco;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DependenteBean implements Serializable {

	private Dependente dependente;
	private List<Dependente> dependentes;

	private Colaborador colaborador;
	private List<Colaborador> colaboradores;

	private Parentesco parentesco;
	private List<Parentesco> parentescos;

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

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	public Dependente getDependente() {
		return dependente;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public void setDependente(Dependente dependente) {
		this.dependente = dependente;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	public List<Parentesco> getParentescos() {
		return parentescos;
	}

	public void setParentescos(List<Parentesco> parentescos) {
		this.parentescos = parentescos;
	}

	@PostConstruct
	public void inicializarTabela() {
		try {
			DependenteDAO dependenteDAO = new DependenteDAO();
			dependentes = dependenteDAO.pesquisar(busca);

			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradores = colaboradorDAO.listar("nome");

			ParentescoDAO parentescoDAO = new ParentescoDAO();
			parentescos = parentescoDAO.listar("nome");

			dependente = null;

			parentesco = new Parentesco();

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Inicializar Tabelas.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void pesquisar() {
		try {
			DependenteDAO dependenteDAO = new DependenteDAO();
			dependentes = dependenteDAO.pesquisar(busca);

			dependente = null;

			parentesco = new Parentesco();

			if (dependentes.isEmpty() == true) {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Registro não Encontrado!",
						"Nenhum Registro foi Encontrado! Por favor Tente Novamente."));
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
		dependente = new Dependente();
		
		dependente.setSalarioEducacao(BigDecimal.ZERO);
		dependente.setSalarioFamilia(BigDecimal.ZERO);
		dependente.setImpostoRenda(BigDecimal.ZERO);

		parentesco = new Parentesco();
	}

	public void salvar() {
		try {
			DependenteDAO dependenteDAO = new DependenteDAO();
			dependenteDAO.merge(dependente);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			dependentes = dependenteDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			dependente = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			DependenteDAO dependenteDAO = new DependenteDAO();
			dependenteDAO.excluir(dependente);

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Excluído com Sucesso"));

			dependentes = dependenteDAO.listar("nome");

			dependente = null;

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
		dependente = null;
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

	public void salvarParentesco() {
		try {
			ParentescoDAO parentescoDAO = new ParentescoDAO();
			parentescoDAO.merge(parentesco);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoParentesco').hide();");

			parentescos = parentescoDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			parentesco = new Parentesco();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluirAtalho(ActionEvent evento) {
		try {
			dependente = (Dependente) evento.getComponent().getAttributes().get("registroSelecionado");

			DependenteDAO dependenteDAO = new DependenteDAO();
			dependenteDAO.excluir(dependente);

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null,
					new FacesMessage("Registro Excluído com Sucesso!", "Registro: " + dependente.getNome()));

			dependentes = dependenteDAO.listar("nome");

			dependente = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			dependente = (Dependente) evento.getComponent().getAttributes().get("registroSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}
}