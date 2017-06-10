package br.com.projeto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.EventoDAO;
import br.com.projeto.dao.LocalDAO;
import br.com.projeto.dao.TipoEventoDAO;
import br.com.projeto.domain.Evento;
import br.com.projeto.domain.Local;
import br.com.projeto.domain.TipoEvento;
import br.com.projeto.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EventoBean implements Serializable {
	private Evento evento;
	private List<Evento> eventos;

	private Local local;
	private List<Local> locais;

	private TipoEvento tipoEvento;
	private List<TipoEvento> tipoEventos;

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

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}

	public List<TipoEvento> getTipoEventos() {
		return tipoEventos;
	}

	public void setTipoEventos(List<TipoEvento> tipoEventos) {
		this.tipoEventos = tipoEventos;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	@PostConstruct
	public void abrirTabelas() {
		try {
			LocalDAO localDAO = new LocalDAO();
			locais = localDAO.listar("nome");

			TipoEventoDAO tipoEventoDAO = new TipoEventoDAO();
			tipoEventos = tipoEventoDAO.listar("nome");

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Abrir Tabelas.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void pesquisar() {
		try {
			EventoDAO eventoDAO = new EventoDAO();
			eventos = eventoDAO.pesquisarEvento(busca);

			evento = null;

			if (eventos.isEmpty() == true) {
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
		AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
		Usuario usuario = autenticacaoBean.getUsuarioLogado();
		
		evento = new Evento();
		evento.setRealizado(false);
		evento.setUsuario(usuario);

		local = new Local();
		tipoEvento = new TipoEvento();
	}

	public void salvar() {
		try {
			evento.setDataEvento(evento.getDataHoraInicial());
			EventoDAO eventoDAO = new EventoDAO();
			eventoDAO.merge(evento);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			eventos = eventoDAO.listar("titulo");

			evento = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarLocal() {
		try {
			LocalDAO localDAO = new LocalDAO();
			localDAO.merge(local);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoLocal').hide();");

			locais = localDAO.listar("nome");
			local = new Local();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarTipoEvento() {
		try {
			TipoEventoDAO tipoEventoDAO = new TipoEventoDAO();
			tipoEventoDAO.merge(tipoEvento);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoTipo').hide();");

			tipoEventos = tipoEventoDAO.listar("nome");
			tipoEvento = new TipoEvento();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent eventoExcluir) {
		try {
			EventoDAO eventoDAO = new EventoDAO();
			eventoDAO.excluir(evento);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + evento.getTitulo());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			eventos = eventoDAO.listar("titulo");

			evento = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void exibirMateriais(ActionEvent eventoMaterial) {
		try {
			evento = (Evento) eventoMaterial.getComponent().getAttributes().get("eventoSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Exibir Materiais.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) {

	}

	public void onRowUnselect(UnselectEvent event) {
		evento = null;
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
