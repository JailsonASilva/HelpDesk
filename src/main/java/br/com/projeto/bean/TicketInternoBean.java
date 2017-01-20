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

import br.com.projeto.dao.CategoriaDAO;
import br.com.projeto.dao.ClienteDAO;
import br.com.projeto.dao.DepartamentoDAO;
import br.com.projeto.dao.EquipamentoDAO;
import br.com.projeto.dao.TicketDAO;
import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.domain.Categoria;
import br.com.projeto.domain.Cliente;
import br.com.projeto.domain.Departamento;
import br.com.projeto.domain.Equipamento;
import br.com.projeto.domain.Ticket;
import br.com.projeto.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TicketInternoBean implements Serializable {
	private Ticket ticket;
	private Categoria categoria;
	private Departamento departamento;
	private Cliente cliente;
	private Usuario usuario;

	private AutenticacaoBean autenticacaoBean;

	private List<Ticket> tickets;
	private List<Cliente> clientes;
	private List<Departamento> departamentos;
	private List<Categoria> categorias;
	private List<Equipamento> equipamentos;
	private List<Usuario> usuarios;

	private FacesMessage message;

	private String busca;
	private String buscaDepartamento;
	private String buscaCliente;
	private String buscaCategoria;	private String buscaUsuario;
	private String buscaEquipamento;

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

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	@PostConstruct
	public void inicializar() {
		try {

			novo();

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Inicializar Rotinas.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void pesquisar() {
		try {

			ticket = null;

			if (tickets.isEmpty() == true) {
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
		autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
		usuario = autenticacaoBean.getUsuarioLogado();

		ticket = new Ticket();
		ticket.setStatus("Pendente");
		ticket.setUsuario(usuario);
		ticket.setDataAbertura(new java.util.Date());
		ticket.setPrioridade("Normal");

		categoria = new Categoria();
		departamento = new Departamento();
		cliente = new Cliente();
	}

	public void salvar() {
		try {
			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket Aberto com Sucesso!",
					"Para acompanhar o andamento de seu Ticket acesse o Menu Sistema - Minha Conta - Meus Chamados");

			novo();

			RequestContext.getCurrentInstance().showMessageInDialog(message);

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
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
}
