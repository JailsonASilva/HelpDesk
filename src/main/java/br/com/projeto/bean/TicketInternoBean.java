package br.com.projeto.bean;

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
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.projeto.dao.AcessoDAO;
import br.com.projeto.dao.AuditoriaDAO;
import br.com.projeto.dao.CategoriaDAO;
import br.com.projeto.dao.DepartamentoDAO;
import br.com.projeto.dao.EquipamentoDAO;
import br.com.projeto.dao.TicketDAO;
import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.domain.Acesso;
import br.com.projeto.domain.Categoria;
import br.com.projeto.domain.Cliente;
import br.com.projeto.domain.Departamento;
import br.com.projeto.domain.Equipamento;
import br.com.projeto.domain.Ticket;
import br.com.projeto.domain.Usuario;
import br.com.projeto.util.EmailUtils;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TicketInternoBean implements Serializable {
	private Ticket ticket;
	private Categoria categoria;
	private Departamento departamento;
	private Departamento departamentoCliente;
	private Cliente cliente;
	private Usuario usuario;
	private Equipamento equipamento;
	private AutenticacaoBean autenticacaoBean;

	private List<Ticket> tickets;
	private List<Cliente> clientes;
	private List<Departamento> departamentos;
	private List<Departamento> departamentosCliente;
	private List<Categoria> categorias;
	private List<Equipamento> equipamentos;
	private List<Usuario> usuarios;
	private List<Acesso> acessos;

	private FacesMessage message;

	private String busca;
	private String buscaDepartamento;
	private String buscaCliente;
	private String buscaCategoria;
	private String buscaUsuario;
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

	public Departamento getDepartamentoCliente() {
		return departamentoCliente;
	}

	public void setDepartamentoCliente(Departamento departamentoCliente) {
		this.departamentoCliente = departamentoCliente;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public List<Departamento> getDepartamentosCliente() {
		return departamentosCliente;
	}

	public void setDepartamentosCliente(List<Departamento> departamentosCliente) {
		this.departamentosCliente = departamentosCliente;
	}

	public List<Acesso> getAcessos() {
		return acessos;
	}

	public void setAcessos(List<Acesso> acessos) {
		this.acessos = acessos;
	}

	@PostConstruct
	public void inicializar() {
		try {
			novo();

			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.listarAtendimento();
			departamentosCliente = departamentoDAO.listar("nome");

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar("nome");

			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categorias = categoriaDAO.listar("nome");

			AcessoDAO acessoDAO = new AcessoDAO();
			acessos = acessoDAO.listar("nome");

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
		ticket.setUltimaInteracao(new java.util.Date());
		ticket.setPrioridade("Normal");
		ticket.setEmailDepartamento(false);
		ticket.setEmailSolicitante(false);
		ticket.setEvento(false);

		categoria = new Categoria();
		departamento = new Departamento();
		cliente = new Cliente();
		usuario = new Usuario();
	}

	public void salvar() throws EmailException {
		try {
			TicketDAO ticketDAO = new TicketDAO();
			ticket.setEmailDepartamento(false);
			ticket.setEmailSolicitante(false);
			ticketDAO.merge(ticket);

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Ticket Aberto com Sucesso!",
					"Para acompanhar o andamento de seu Ticket acesse o menu Meus Ticket's"));
			novo();

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Abriu Ticket Interno");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

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

	public void duploCliqueEquipamento(SelectEvent evento) {
		try {
			ticket.setEquipamento(equipamento);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqEquipamento').hide();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void enviarEmailDepartamento() throws EmailException {
		try {
			String mensagem = "Dados do Solicitante " + "\n" + "\n" + "Nome: " + ticket.getUsuario().getNome() + "\n"
					+ "E-mail: " + ticket.getUsuario().getEmail() + "\n" + "Ramal: " + ticket.getUsuario().getRamal()
					+ "\n" + "" + "\n" + "\n" +

					"Dados do Ticket " + "\n" + "\n" + "Data Abertura: " + ticket.getDataAbertura() + "\n"
					+ "Prioridade: " + ticket.getPrioridadeFormatada() + "\n" + "Assunto: " + ticket.getAssunto() + "\n"
					+ "" + "\n" + "Dados da Solicitação: " + "\n" + ticket.getSolicitacao() + "\n";

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listarUsuarios(ticket.getDepartamento().getCodigo());

			for (int i = 0; i < usuarios.size(); i++) {
				Usuario usuarioEmail = (Usuario) usuarios.get(i);

				EmailUtils.enviaEmail("Ticket Aberto", mensagem, usuarioEmail.getEmail());
			}

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Enviar E-mail para o Departamento", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}

	}

	public void enviarEmailSolicitante() throws EmailException {
		try {
			String mensagem = "Seu Ticket foi aberto com sucesso! Em breve o Departamento de "
					+ ticket.getDepartamento().getNome()
					+ " entrará em contato. Para acompanhar o andamento de seu Ticket acesse o Sistema no menu Meus Ticket's.";

			EmailUtils.enviaEmail("Ticket Aberto", mensagem, usuario.getEmail());

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Enviar E-mail para o Solicitante", "Erro: " + erro.getMessage());

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

			categorias = categoriaDAO.listar("nome");

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Cadastro uma Nova Categoria (Por Departamento)");

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

			departamentos = departamentoDAO.listar("nome");

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Cadastro um Novo Departamento (Por Departamento)");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarUsuario() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoUsuario').hide();");

			usuarios = usuarioDAO.listar("nome");

			if (usuario.getCodigo() == null) {

				AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
				auditoriaDAO.auditar("Cadastro um Novo Usuário: " + usuario.getNome());

			} else {

				AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
				auditoriaDAO.auditar("Alterou um Usuário: " + usuario.getNome());
			}

			usuario = new Usuario();

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

}
