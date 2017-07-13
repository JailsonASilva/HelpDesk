package br.com.projeto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.ColaboradorDAO;
import br.com.projeto.dao.ProgramacaoFeriasDAO;
import br.com.projeto.domain.Colaborador;
import br.com.projeto.domain.ProgramacaoFerias;
import br.com.projeto.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProgramacaoFeriasBean implements Serializable {

	private ProgramacaoFerias programacaoFerias;
	private List<ProgramacaoFerias> programacaoFeriasLista;

	private FacesMessage message;
	private String busca;

	private List<Colaborador> colaboradores;

	public FacesMessage getMessage() {
		return message;
	}

	public ProgramacaoFerias getProgramacaoFerias() {
		return programacaoFerias;
	}

	public void setProgramacaoFerias(ProgramacaoFerias programacaoFerias) {
		this.programacaoFerias = programacaoFerias;
	}

	public List<ProgramacaoFerias> getProgramacaoFeriasLista() {
		return programacaoFeriasLista;
	}

	public void setProgramacaoFeriasLista(List<ProgramacaoFerias> programacaoFeriasLista) {
		this.programacaoFeriasLista = programacaoFeriasLista;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	@PostConstruct
	public void incializar() {
		try {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradores = colaboradorDAO.listarColaboradores(usuario.getDepartamento().getCodigo());

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Inicializar Tabelas.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void pesquisar() {
		try {
			ProgramacaoFeriasDAO programacaoFeriasDAO = new ProgramacaoFeriasDAO();
			programacaoFeriasLista = programacaoFeriasDAO.pesquisarColaborador(busca);

			programacaoFerias = null;

			if (programacaoFeriasLista.isEmpty() == true) {
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

		AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
		Usuario usuario = autenticacaoBean.getUsuarioLogado();

		programacaoFerias = new ProgramacaoFerias();

		programacaoFerias.setUsuario(usuario);

	}

	public void salvar() {
		try {
			ProgramacaoFeriasDAO programacaoFeriasDAO = new ProgramacaoFeriasDAO();

			if (programacaoFeriasDAO.duplicidade(programacaoFerias.getColaborador().getCodigo()) == false
					& programacaoFerias.getCodigo() == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Ocorreu um Erro ao Tentar Salvar este Registro.", "Colaborador já Cadastrado!");

				RequestContext.getCurrentInstance().showMessageInDialog(message);

			} else {

				programacaoFeriasDAO.merge(programacaoFerias);

				org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

				programacaoFeriasLista = programacaoFeriasDAO.pesquisarColaborador("");

				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

				programacaoFerias = null;

			}

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			ProgramacaoFeriasDAO programacaoFeriasDAO = new ProgramacaoFeriasDAO();
			programacaoFeriasDAO.excluir(programacaoFerias);

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Excluído com Sucesso"));

			programacaoFeriasLista = programacaoFeriasDAO.pesquisarColaborador("");

			programacaoFerias = null;

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
		programacaoFerias = null;
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

	public void excluirAtalho(ActionEvent evento) {
		try {
			programacaoFerias = (ProgramacaoFerias) evento.getComponent().getAttributes().get("registroSelecionado");

			ProgramacaoFeriasDAO programacaoFeriasDAO = new ProgramacaoFeriasDAO();
			programacaoFeriasDAO.excluir(programacaoFerias);

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Registro Excluído com Sucesso!",
					"Registro: " + programacaoFerias.getColaborador().getNome()));

			programacaoFeriasLista = programacaoFeriasDAO.pesquisarColaborador("");

			programacaoFerias = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			programacaoFerias = (ProgramacaoFerias) evento.getComponent().getAttributes().get("registroSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}
}