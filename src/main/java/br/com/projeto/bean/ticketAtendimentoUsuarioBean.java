package br.com.projeto.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.mail.EmailException;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
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
import br.com.projeto.util.EmailUtils;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ticketAtendimentoUsuarioBean implements Serializable {
	private Ticket ticket;
	private Ocorrencia ocorrencia;
	private AutenticacaoBean autenticacaoBean;
	private Usuario usuario;
	private Categoria categoria;
	private Departamento departamento;
	private Cliente cliente;

	private List<Ocorrencia> ocorrencias;
	private List<Ticket> tickets;
	private List<Cliente> clientes;
	private List<Departamento> departamentos;
	private List<Departamento> departamentosCliente;
	private List<Categoria> categorias;
	private List<Equipamento> equipamentos;
	private List<Usuario> usuarios;

	private FacesMessage message;
	private String usuarioBusca;
	private String status;
	private String buscaDepartamento;
	private String buscaCliente;
	private String buscaCategoria;
	private String buscaUsuario;
	private String buscaEquipamento;

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getBuscaDepartamento() {
		return buscaDepartamento;
	}

	public void setBuscaDepartamento(String buscaDepartamento) {
		this.buscaDepartamento = buscaDepartamento;
	}

	public String getBuscaCliente() {
		return buscaCliente;
	}

	public void setBuscaCliente(String buscaCliente) {
		this.buscaCliente = buscaCliente;
	}

	public String getBuscaCategoria() {
		return buscaCategoria;
	}

	public void setBuscaCategoria(String buscaCategoria) {
		this.buscaCategoria = buscaCategoria;
	}

	public String getBuscaUsuario() {
		return buscaUsuario;
	}

	public void setBuscaUsuario(String buscaUsuario) {
		this.buscaUsuario = buscaUsuario;
	}

	public String getBuscaEquipamento() {
		return buscaEquipamento;
	}

	public void setBuscaEquipamento(String buscaEquipamento) {
		this.buscaEquipamento = buscaEquipamento;
	}

	public List<Departamento> getDepartamentosCliente() {
		return departamentosCliente;
	}

	public void setDepartamentosCliente(List<Departamento> departamentosCliente) {
		this.departamentosCliente = departamentosCliente;
	}

	@PostConstruct
	public void abrirTabelas() {
		try {
			listarPendentes();

			categoria = new Categoria();
			departamento = new Departamento();
			cliente = new Cliente();

			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.listarAtendimento();
			departamentosCliente = departamentoDAO.listar("nome");

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar("nome");

			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categorias = categoriaDAO.listar("nome");

			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.pesquisarUsuarioDepartamento(usuario.getDepartamento().getCodigo());

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

			if (status.equals("Aberto")) {
				tickets = ticketDAO.pesquisarUsuario(usuarioBusca);
			} else {
				tickets = ticketDAO.pesquisarUsuario(usuarioBusca, status);
			}

			ticket = null;

			if (tickets.isEmpty() == true) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Registro não Encontrado!", "Por favor tente novamente."));
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
			tickets = ticketDAO.pesquisarUsuario(usuarioBusca);

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

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Ticket Salvo com Sucesso");

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

	public void salvarOcorrencia() throws EmailException {
		try {
			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.merge(ocorrencia);

			// enviarEmail();

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

	public void atenderTicket() throws EmailException {
		try {
			novaOcorrencia();
			ocorrencia.setOcorrencia("Ticket em Atendimento!");

			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.merge(ocorrencia);

			ticket.setStatus("Em Atendimento");
			ticket.setUsuarioAtendimento(usuario);

			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			// enviarEmail();

			listarPendentes();

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Ticket Atendido com Sucesso!",
					"Ticket Nº " + ticket.getCodigo() + " em Atendimento!"));

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Atender este Ticket.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void encaminharTicket() throws EmailException {
		try {
			novaOcorrencia();
			ocorrencia.setOcorrencia("Ticket em Encaminhado para " + ticket.getUsuarioAtendimento().getNome());

			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.merge(ocorrencia);

			ticket.setStatus("Pendente");

			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			// enviarEmail();

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoEncaminhar').hide();");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Ticket Encaminhado com Sucesso!",
					"Ticket Nº " + ticket.getCodigo() + " Encaminhado!"));

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Atender este Ticket.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void suspenderTicket() throws EmailException {
		try {
			novaOcorrencia();
			ocorrencia.setOcorrencia("Ticket Suspenso!");

			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.merge(ocorrencia);

			ticket.setStatus("Suspenso");
			ticket.setUsuarioAtendimento(usuario);

			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			// enviarEmail();

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null,
					new FacesMessage("Ticket Suspenso com Sucesso!", "Ticket Nº " + ticket.getCodigo() + " Suspenso!"));

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Suspender este Ticket.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void concluirTicket() throws EmailException {
		try {
			novaOcorrencia();
			ocorrencia.setOcorrencia("Ticket Concluído!");

			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.merge(ocorrencia);

			ticket.setStatus("Concluído");
			ticket.setUsuarioAtendimento(usuario);

			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			// enviarEmail();

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Ticket Concluído com Sucesso!",
					"Ticket Nº " + ticket.getCodigo() + " Concluído!"));

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
	
	public void duploTicket(SelectEvent evento) {
		try {
			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').show();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

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

	public void pesquisarDepartamento() {
		try {
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.pesquisarAtendimento(buscaDepartamento);

			if (departamentos.isEmpty() == true) {
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

	public void pesquisarDepartamentoCliente() {
		try {
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.pesquisarDepartamento(buscaDepartamento);

			if (departamentos.isEmpty() == true) {
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

	public void selecionarDepartamento(ActionEvent evento) {
		try {
			Departamento departamentoPesq = (Departamento) evento.getComponent().getAttributes()
					.get("departamentoSelecionado");

			ticket.setDepartamento(departamentoPesq);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqDepartamento').hide();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void selecionarDepartamentoCliente(ActionEvent evento) {
		try {
			Departamento departamentoPesq = (Departamento) evento.getComponent().getAttributes()
					.get("departamentoSelecionado");

			cliente.setDepartamento(departamentoPesq);

			org.primefaces.context.RequestContext.getCurrentInstance()
					.execute("PF('dialogoPesqDepartamentoCliente').hide();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void pesquisarCliente() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.pesquisar(buscaCliente);

			if (clientes.isEmpty() == true) {
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

	public void selecionarCliente(ActionEvent evento) {
		try {

			Cliente clientePesq = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			ticket.setCliente(clientePesq);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqCliente').hide();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void pesquisarCategoria() {
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categorias = categoriaDAO.pesquisar(buscaCategoria);

			if (categorias.isEmpty() == true) {
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

	public void selecionarCategoria(ActionEvent evento) {
		try {
			Categoria categoriaPesq = (Categoria) evento.getComponent().getAttributes().get("categoriaSelecionado");

			ticket.setCategoria(categoriaPesq);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqCategoria').hide();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void pesquisarUsuario() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.pesquisar(buscaUsuario);

			if (usuarios.isEmpty() == true) {
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

	public void selecionarUsuario(ActionEvent evento) {
		try {
			Usuario usuarioPesq = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			ticket.setUsuario(usuarioPesq);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqUsuario').hide();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void pesquisarEquipamento() {
		try {
			EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
			equipamentos = equipamentoDAO.pesquisarEquipamento(buscaEquipamento);

			if (equipamentos.isEmpty() == true) {
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

	public void selecionarEquipamento(ActionEvent evento) {
		try {
			Equipamento equipamentoPesq = (Equipamento) evento.getComponent().getAttributes()
					.get("equipamentoSelecionado");

			ticket.setEquipamento(equipamentoPesq);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqEquipamento').hide();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarCategoria() {
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categoriaDAO.merge(categoria);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoCategoria').hide();");

			categoria = new Categoria();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarDepartamento() {
		try {
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentoDAO.merge(departamento);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoDepartamento').hide();");

			departamento = new Departamento();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarCliente() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoCliente').hide();");

			cliente = new Cliente();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void novoTicket() {
		try {
			Faces.redirect("./pages/ticketInterno.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível Desconectar. Erro: " + erro.getMessage());
		}
	}

	public void enviarEmail() throws EmailException {
		try {
			String mensagem = "Registro de Ocorrência " + "\n" + "\n" + "Data Ocorrência: " + ocorrencia.getData()
					+ "\n" + "Atendente: " + ocorrencia.getUsuario().getNome() + "\n" + "Ocorrência: "
					+ ocorrencia.getOcorrencia() + "\n" + "" +

					"Dados do Ticket " + "\n" + "\n" + "Nº Ticket: " + ticket.getCodigo() + "\n" + "Prioridade: "
					+ ticket.getPrioridadeFormatada() + "\n" + "Assunto: " + ticket.getAssunto() + "\n" + "" + "\n"
					+ "Dados da Solicitação: " + "\n" + ticket.getSolicitacao() + "\n";

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listarUsuarios(ticket.getDepartamento().getCodigo());

			for (int i = 0; i < usuarios.size(); i++) {
				Usuario usuarioEmail = (Usuario) usuarios.get(i);

				EmailUtils.enviaEmail("Registro de Ocorrência", mensagem, usuarioEmail.getEmail());
			}

			EmailUtils.enviaEmail("Registro de Ocorrência", mensagem, ticket.getUsuario().getEmail());

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Enviar E-mail para o Departamento", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}

	}
}
