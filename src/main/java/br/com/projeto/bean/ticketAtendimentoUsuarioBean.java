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

import br.com.projeto.dao.CategoriaDAO;
import br.com.projeto.dao.ClienteDAO;
import br.com.projeto.dao.DepartamentoDAO;
import br.com.projeto.dao.EquipamentoDAO;
import br.com.projeto.dao.OcorrenciaDAO;
import br.com.projeto.dao.TicketDAO;
import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.domain.Categoria;
import br.com.projeto.domain.Cliente;
import br.com.projeto.domain.Departamento;
import br.com.projeto.domain.Equipamento;
import br.com.projeto.domain.Ocorrencia;
import br.com.projeto.domain.Ticket;
import br.com.projeto.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ticketAtendimentoUsuarioBean implements Serializable {
	private Ticket ticket;
	private List<Ticket> tickets;

	private Ocorrencia ocorrencia;
	private List<Ocorrencia> ocorrencias;

	private AutenticacaoBean autenticacaoBean;
	private Usuario usuario;

	private List<Cliente> clientes;
	private List<Departamento> departamentos;
	private List<Categoria> categorias;
	private List<Equipamento> equipamentos;
	private List<Usuario> usuarios;

	private FacesMessage message;
	private String usuarioBusca;
	private String status;

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUsuarioBusca() {
		return usuarioBusca;
	}

	public void setUsuarioBusca(String usuarioBusca) {
		this.usuarioBusca = usuarioBusca;
	}

	@PostConstruct
	public void abrirTabelas() {
		try {
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.listarAtendimento();

			EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
			equipamentos = equipamentoDAO.listar();

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar("nome");

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar("nome");

			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categorias = categoriaDAO.listar("nome");

			listarPendentes();

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Abrir as Tabelas.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}
	
	public void pesquisar() {
		try {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			usuarioBusca = usuario.getNome();

			TicketDAO ticketDAO = new TicketDAO();
			tickets = ticketDAO.pesquisarUsuario(usuarioBusca, status);

			ticket = null;

			if (tickets.isEmpty() == true) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Nenhum Registro foi Encontrado! Por favor Tente Novamente.", "Registro não Encontrado!");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Pesquisar Ticket.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}	

	public void listarPendentes() {
		try {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			usuarioBusca = usuario.getNome();

			TicketDAO ticketDAO = new TicketDAO();
			tickets = ticketDAO.pesquisarUsuario(usuarioBusca, "Pendente");

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Listar Ticket's Pendentes.", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void listarOcorrencia(ActionEvent evento) {
		try {
			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrencias = ocorrenciaDAO.pesquisarOcorrenciaTicket(ticket.getCodigo());

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Abrir Ocorrencias.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void novaOcorrencia() {
		autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
		usuario = autenticacaoBean.getUsuarioLogado();

		ocorrencia = new Ocorrencia();
		ocorrencia.setTicket(ticket);
		ocorrencia.setUsuario(usuario);
		ocorrencia.setData(new java.util.Date());
	}

	public void salvar() {
		try {
			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket Salvo com Sucesso!",
					"Para acompanhar o andamento de seu Ticket acesse o Menu Sistema - Minha Conta - Meus Chamados");

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			ticket = null;

			listarPendentes();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarOcorrencia() {
		try {
			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.merge(ocorrencia);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoOcorrencia').hide();");

			ocorrencias = ocorrenciaDAO.pesquisarOcorrenciaTicket(ocorrencia.getTicket().getCodigo());

			ocorrencia = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void atenderTicket() {
		try {
			novaOcorrencia();
			ocorrencia.setOcorrencia("Ticket em Atendimento!");

			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.merge(ocorrencia);

			ticket.setStatus("Pendente");
			ticket.setUsuarioAtendimento(usuario);

			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			listarPendentes();

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket Atendido com Sucesso!",
					"Ticket Nº " + ticket.getCodigo() + " em Atendimento!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Atender este Ticket.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void suspenderTicket() {
		try {
			novaOcorrencia();
			ocorrencia.setOcorrencia("Ticket Suspenso!");

			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.merge(ocorrencia);

			ticket.setStatus("Suspenso");
			ticket.setUsuarioAtendimento(usuario);

			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			listarPendentes();

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket Suspenso com Sucesso!",
					"Ticket Nº " + ticket.getCodigo() + " Suspenso!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Suspender este Ticket.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void concluirTicket() {
		try {
			novaOcorrencia();
			ocorrencia.setOcorrencia("Ticket Concluído!");

			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.merge(ocorrencia);

			ticket.setStatus("Concluído");
			ticket.setUsuarioAtendimento(usuario);

			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			listarPendentes();

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket Concluído com Sucesso!",
					"Ticket Nº " + ticket.getCodigo() + " Concluído!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Concluir este Ticket.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editarOcorrencia(ActionEvent evento) {
		try {
			ocorrencia = (Ocorrencia) evento.getComponent().getAttributes().get("ocorrenciaSelecionada");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}


	public void excluirTicket(ActionEvent evento) {
		try {
			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.excluir(ticket);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Nº do Ticket: " + ticket.getCodigo());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			ticket = null;

			listarPendentes();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}
	
	public void excluirOcorrencia(ActionEvent evento) {
		try {
			ocorrencia = (Ocorrencia) evento.getComponent().getAttributes().get("ocorrenciaSelecionada");

			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.excluir(ocorrencia);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Ocorrência Excluída - Nº Ticket: " + ocorrencia.getTicket().getCodigo() + " / Assunto: "
							+ ocorrencia.getTicket().getAssunto());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			ocorrencias = ocorrenciaDAO.pesquisarOcorrenciaTicket(ticket.getCodigo());

			ocorrencia = null;

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
		ticket = null;
	}
}