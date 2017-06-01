package br.com.projeto.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.projeto.dao.CategoriaDAO;
import br.com.projeto.dao.ClienteDAO;
import br.com.projeto.dao.DepartamentoDAO;
import br.com.projeto.dao.EquipamentoDAO;
import br.com.projeto.dao.InteracaoDAO;
import br.com.projeto.dao.OcorrenciaDAO;
import br.com.projeto.dao.TicketDAO;
import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.domain.Categoria;
import br.com.projeto.domain.Cliente;
import br.com.projeto.domain.Departamento;
import br.com.projeto.domain.Equipamento;
import br.com.projeto.domain.Interacao;
import br.com.projeto.domain.Ocorrencia;
import br.com.projeto.domain.Ticket;
import br.com.projeto.domain.Usuario;
import br.com.projeto.util.EmailUtils;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MeusTicketsBean implements Serializable {
	private Ticket ticket;
	private Ocorrencia ocorrencia;
	private AutenticacaoBean autenticacaoBean;
	private Usuario usuario;
	private Categoria categoria;
	private Departamento departamento;
	private Departamento departamentoCliente;

	private Cliente cliente;
	private Equipamento equipamento;

	private List<Ocorrencia> ocorrencias;
	private List<Ticket> tickets;
	private List<Cliente> clientes;
	private List<Departamento> departamentos;
	private List<Departamento> departamentosCliente;
	private List<Categoria> categorias;
	private List<Equipamento> equipamentos;
	private List<Usuario> usuarios;

	private String solicitanteBusca;
	private Long departamentoBusca;
	private List<Departamento> departamentosBusca;
	private List<Cliente> solicitantesBusca;
	private Long usuarioAberturaBusca;
	private List<Usuario> usuariosAberturaBusca;
	private Long atendenteBusca;
	private Long ticketBusca;
	private List<Usuario> atendentesBusca;
	private String prioridadeBusca;
	private String statusBusca;
	private String assuntoBusca;
	private String solicitacaoBusca;

	private StreamedContent arquivo;

	private FacesMessage message;

	private String departamentoPesq;
	private String usuarioBusca;
	private String status;
	private String buscaDepartamento;
	private String buscaCliente;
	private String buscaCategoria;
	private String buscaUsuario;
	private String buscaEquipamento;
	private String usuarioEmail;
	private String extensao;
	private String tipoArquivo;

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

	public String getDepartamentoPesq() {
		return departamentoPesq;
	}

	public void setDepartamentoPesq(String departamentoPesq) {
		this.departamentoPesq = departamentoPesq;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Departamento getDepartamentoCliente() {
		return departamentoCliente;
	}

	public void setDepartamentoCliente(Departamento departamentoCliente) {
		this.departamentoCliente = departamentoCliente;
	}

	public List<Departamento> getDepartamentosCliente() {
		return departamentosCliente;
	}

	public void setDepartamentosCliente(List<Departamento> departamentosCliente) {
		this.departamentosCliente = departamentosCliente;
	}

	public String getUsuarioEmail() {
		return usuarioEmail;
	}

	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}

	public StreamedContent getArquivo() {
		return arquivo;
	}

	public void setArquivo(StreamedContent arquivo) {
		this.arquivo = arquivo;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public String getSolicitanteBusca() {
		return solicitanteBusca;
	}

	public void setSolicitanteBusca(String solicitanteBusca) {
		this.solicitanteBusca = solicitanteBusca;
	}

	public Long getDepartamentoBusca() {
		return departamentoBusca;
	}

	public void setDepartamentoBusca(Long departamentoBusca) {
		this.departamentoBusca = departamentoBusca;
	}

	public List<Cliente> getSolicitantesBusca() {
		return solicitantesBusca;
	}

	public void setSolicitantesBusca(List<Cliente> solicitantesBusca) {
		this.solicitantesBusca = solicitantesBusca;
	}

	public Long getUsuarioAberturaBusca() {
		return usuarioAberturaBusca;
	}

	public void setUsuarioAberturaBusca(Long usuarioAberturaBusca) {
		this.usuarioAberturaBusca = usuarioAberturaBusca;
	}

	public List<Usuario> getUsuariosAberturaBusca() {
		return usuariosAberturaBusca;
	}

	public void setUsuariosAberturaBusca(List<Usuario> usuariosAberturaBusca) {
		this.usuariosAberturaBusca = usuariosAberturaBusca;
	}

	public Long getAtendenteBusca() {
		return atendenteBusca;
	}

	public void setAtendenteBusca(Long atendenteBusca) {
		this.atendenteBusca = atendenteBusca;
	}

	public Long getTicketBusca() {
		return ticketBusca;
	}

	public void setTicketBusca(Long ticketBusca) {
		this.ticketBusca = ticketBusca;
	}

	public List<Usuario> getAtendentesBusca() {
		return atendentesBusca;
	}

	public void setAtendentesBusca(List<Usuario> atendentesBusca) {
		this.atendentesBusca = atendentesBusca;
	}

	public String getPrioridadeBusca() {
		return prioridadeBusca;
	}

	public void setPrioridadeBusca(String prioridadeBusca) {
		this.prioridadeBusca = prioridadeBusca;
	}

	public String getStatusBusca() {
		return statusBusca;
	}

	public void setStatusBusca(String statusBusca) {
		this.statusBusca = statusBusca;
	}

	public String getAssuntoBusca() {
		return assuntoBusca;
	}

	public void setAssuntoBusca(String assuntoBusca) {
		this.assuntoBusca = assuntoBusca;
	}

	public String getSolicitacaoBusca() {
		return solicitacaoBusca;
	}

	public void setSolicitacaoBusca(String solicitacaoBusca) {
		this.solicitacaoBusca = solicitacaoBusca;
	}

	public List<Departamento> getDepartamentosBusca() {
		return departamentosBusca;
	}

	public void setDepartamentosBusca(List<Departamento> departamentosBusca) {
		this.departamentosBusca = departamentosBusca;
	}

	@PostConstruct
	public void abrirTabelas() {
		try {
			listarPendentes();

			categoria = new Categoria();
			departamento = new Departamento();
			departamentoCliente = new Departamento();
			cliente = new Cliente();
			equipamento = new Equipamento();

			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.listarAtendimento();
			departamentosCliente = departamentoDAO.listar("nome");
			departamentosBusca = departamentos;

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar("nome");

			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categorias = categoriaDAO.listar("nome");

			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.pesquisarUsuarioDepartamento(usuario.getDepartamento().getCodigo());

			atendentesBusca = usuarioDAO.listarTodosAtendente();

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

			departamentoPesq = usuario.getDepartamento().getNome();

			TicketDAO ticketDAO = new TicketDAO();
			tickets = ticketDAO.pesquisarDepartamento(departamentoPesq, status);

			if (status.equals("Aberto")) {
				tickets = ticketDAO.pesquisarDepartamento(departamentoPesq);
			} else {
				tickets = ticketDAO.pesquisarDepartamento(departamentoPesq, status);
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

			TicketDAO ticketDAO = new TicketDAO();
			tickets = ticketDAO.meusTickets(usuario.getNome());

			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(":formListagem:tool");

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

			interacao("Listou as Ocorrências");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Abrir Ocorrencias.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void listarOcorrenciaAtalho(ActionEvent evento) {
		try {
			ticket = (Ticket) evento.getComponent().getAttributes().get("ticketSelecionado");

			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrencias = ocorrenciaDAO.pesquisarOcorrenciaTicket(ticket.getCodigo());

			interacao("Listou as Ocorrências");

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
		ocorrencia.setEmailEnviado(false);
		ocorrencia.setData(new java.util.Date());
	}

	public void salvar() throws EmailException {
		try {
			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			interacao("Alterou Dados do Ticket");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null,
					new FacesMessage("Aviso!", "Ticket Nº " + ticket.getCodigo() + " Ticket Salvo com Sucesso"));

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			ticket = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarOcorrencia() throws EmailException, IOException {
		try {
			if (ocorrencia.getCaminho() != null) {
				ocorrencia.setCodigoAnexo(ticket.getCodigo() + "" + ocorrencias.size());
				ocorrencia.setTipoAnexo(tipoArquivo);

				interacao("Salvou uma Ocorrência com Anexo");

				Path origem = Paths.get(ocorrencia.getCaminho());
				Path destino = Paths
						.get("C:/Ocorrencias/" + ticket.getCodigo() + ocorrencias.size() + "." + tipoArquivo);

				Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			}

			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.merge(ocorrencia);

			interacao("Salvou uma Ocorrência");

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

	public void salvarOcorrenciaEmail() throws EmailException {
		try {
			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.merge(ocorrencia);

			enviarEmail();

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoOcorrencia').hide();");

			ocorrencias = ocorrenciaDAO.pesquisarOcorrenciaTicket(ocorrencia.getTicket().getCodigo());

			ocorrencia = null;

			interacao("Ocorrência Salva com o Envio de E-mail");

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
			ticket.setUltimaInteracao(new java.util.Date());

			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Ticket Atendido com Sucesso!",
					"Ticket Nº " + ticket.getCodigo() + " em Atendimento!"));

			ticket = null;

			interacao("Atendeu o Ticket");

			listarPendentes();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Atender este Ticket.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void atenderTicketAtalho(ActionEvent evento) throws EmailException {
		try {

			ticket = (Ticket) evento.getComponent().getAttributes().get("ticketSelecionado");

			novaOcorrencia();
			ocorrencia.setOcorrencia("Ticket em Atendimento!");

			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.merge(ocorrencia);

			interacao("Atendeu o Ticket");

			ticket.setStatus("Em Atendimento");
			ticket.setUsuarioAtendimento(usuario);
			ticket.setUltimaInteracao(new java.util.Date());

			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Ticket Atendido com Sucesso!",
					"Ticket Nº " + ticket.getCodigo() + " em Atendimento!"));

			ticket = null;

			listarPendentes();

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

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoEncaminhar').hide();");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Ticket Encaminhado com Sucesso!",
					"Ticket Nº " + ticket.getCodigo() + " Encaminhado!"));

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Encaminhar este Ticket.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void encaminharTicketAtalho(ActionEvent evento) throws EmailException {
		try {
			ticket = (Ticket) evento.getComponent().getAttributes().get("ticketSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Encaminhar este Ticket.",
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
			ticket.setUltimaInteracao(new java.util.Date());

			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			org.primefaces.context.RequestContext.getCurrentInstance()
					.execute("PF('dialogoListagemOcorrencia').hide();");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null,
					new FacesMessage("Ticket Suspenso com Sucesso!", "Ticket Nº " + ticket.getCodigo() + " Suspenso!"));

			ticket = null;

			listarPendentes();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Suspender este Ticket.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void suspenderTicketAtalho(ActionEvent evento) throws EmailException {
		try {
			ticket = (Ticket) evento.getComponent().getAttributes().get("ticketSelecionado");

			novaOcorrencia();
			ocorrencia.setOcorrencia("Ticket Suspenso!");

			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.merge(ocorrencia);

			ticket.setStatus("Suspenso");
			ticket.setUsuarioAtendimento(usuario);
			ticket.setUltimaInteracao(new java.util.Date());

			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			org.primefaces.context.RequestContext.getCurrentInstance()
					.execute("PF('dialogoListagemOcorrencia').hide();");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null,
					new FacesMessage("Ticket Suspenso com Sucesso!", "Ticket Nº " + ticket.getCodigo() + " Suspenso!"));

			ticket = null;

			listarPendentes();

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
			ticket.setUltimaInteracao(new java.util.Date());

			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			org.primefaces.context.RequestContext.getCurrentInstance()
					.execute("PF('dialogoListagemOcorrencia').hide();");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Ticket Concluído com Sucesso!",
					"Ticket Nº " + ticket.getCodigo() + " Concluído!"));

			ticket = null;

			listarPendentes();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Concluir este Ticket.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void concluirTicketAtalho(ActionEvent evento) throws EmailException {
		try {
			ticket = (Ticket) evento.getComponent().getAttributes().get("ticketSelecionado");

			novaOcorrencia();
			ocorrencia.setOcorrencia("Ticket Concluído!");

			OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
			ocorrenciaDAO.merge(ocorrencia);

			ticket.setStatus("Concluído");
			ticket.setUsuarioAtendimento(usuario);
			ticket.setUltimaInteracao(new java.util.Date());

			TicketDAO ticketDAO = new TicketDAO();
			ticketDAO.merge(ticket);

			org.primefaces.context.RequestContext.getCurrentInstance()
					.execute("PF('dialogoListagemOcorrencia').hide();");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Ticket Concluído com Sucesso!",
					"Ticket Nº " + ticket.getCodigo() + " Concluído!"));

			ticket = null;

			listarPendentes();

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

	public void selecionarOcorrenciaEmail(ActionEvent evento) {
		try {
			ocorrencia = (Ocorrencia) evento.getComponent().getAttributes().get("ocorrenciaSelecionada");

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar("nome");

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

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Registro Excluído com Sucesso!", "Nº do Ticket: " + ticket.getCodigo()));

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

	public void duploCliqueDepartamento(SelectEvent evento) {
		try {
			ticket.setDepartamento(departamento);

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

	public void duploCliqueDepartamentoCliente(SelectEvent evento) {
		try {
			ticket.setDepartamento(departamentoCliente);

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

	public void duploCliqueCliente(SelectEvent evento) {
		try {

			ticket.setCliente(cliente);

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

	public void duploCliqueCategoria(SelectEvent evento) {
		try {
			ticket.setCategoria(categoria);

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
			usuarios = usuarioDAO.pesquisarUsuarioDepartamento(ticket.getDepartamento().getCodigo(), buscaUsuario);

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

			ticket.setUsuarioAtendimento(usuarioPesq);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqUsuario').hide();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void duploCliqueUsuario(SelectEvent evento) {
		try {
			ticket.setUsuarioAtendimento(usuario);

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

	public void duploTicket(SelectEvent evento) {
		try {
			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').show();");

			interacao("Visualizou o Ticket");

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

			categorias = categoriaDAO.listar("nome");

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

			clientes = clienteDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void novoTicket() {
		try {
			Faces.redirect("./pages/ticketExterno.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível Desconectar. Erro: " + erro.getMessage());
		}
	}

	public void enviarEmail() throws EmailException {
		try {
			String mensagem = "Registro de Ocorrência " + "\n" + "\n" + "Data Ocorrência: " + ocorrencia.getData()
					+ "\n" + "Atendente: " + ocorrencia.getUsuario().getNome() + "\n" + "Ocorrência: "
					+ ocorrencia.getOcorrencia() + "\n" + "" + "\n" +

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

	public void enviarEmailOcorrencia() throws EmailException {
		try {
			String mensagem = "Registro de Ocorrência " + "\n" + "\n" + "Data Ocorrência: " + ocorrencia.getData()
					+ "\n" + "Atendente: " + ocorrencia.getUsuario().getNome() + "\n" + "Ocorrência: "
					+ ocorrencia.getOcorrencia() + "\n" + "" + "\n" +

					"Dados do Ticket " + "\n" + "\n" + "Nº Ticket: " + ticket.getCodigo() + "\n" + "Prioridade: "
					+ ticket.getPrioridadeFormatada() + "\n" + "Assunto: " + ticket.getAssunto() + "\n" + "" + "\n"
					+ "Dados da Solicitação: " + "\n" + ticket.getSolicitacao() + "\n";

			EmailUtils.enviaEmail("Registro de Ocorrência", mensagem, usuarioEmail);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoEmailOcorrencia').hide();");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Envio de Ocorrência!", "E-mail Enviado com Sucesso!"));

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Enviar E-mail",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}

	}

	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);

			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			ocorrencia.setCaminho(arquivoTemp.toString());

			String extensao = evento.getFile().getContentType();

			boolean encontrado = false;
			tipoArquivo = "";

			for (int i = 0; i < extensao.length(); i++) {
				String busca = extensao.substring(0 + i, 1 + i);

				if (busca.equals("/")) {
					encontrado = true;
				}

				if (encontrado == true != (busca.equals("/"))) {
					tipoArquivo = tipoArquivo + busca;
				}

			}

			if (tipoArquivo.equals("plain")) {
				tipoArquivo = "txt";
			}

			if (tipoArquivo.equals("msword")) {
				tipoArquivo = "doc";
			}

			if (tipoArquivo.equals("vnd.openxmlformats-officedocument.wordprocessingml.document")) {
				tipoArquivo = "docx";
			}

			if (tipoArquivo.equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
				tipoArquivo = "xlsx";
			}

			if (tipoArquivo.equals("vnd.ms-excel")) {
				tipoArquivo = "xls";
			}

		} catch (IOException erro) {
			Messages.addGlobalInfo("Ocorreu um erro ao tentar realizar o upload de arquivo");
			erro.printStackTrace();
		}
	}

	public void download(ActionEvent evento) {
		try {
			ocorrencia = (Ocorrencia) evento.getComponent().getAttributes().get("ocorrenciaSelecionada");

			if (ocorrencia.getAnexo().equals("Sim")) {
				InputStream stream = new FileInputStream(
						"C:/Ocorrencias/" + ocorrencia.getCodigoAnexo() + "." + ocorrencia.getTipoAnexo());

				arquivo = new DefaultStreamedContent(stream, tipoArquivo + "/" + tipoArquivo,
						ocorrencia.getCodigo() + "." + ocorrencia.getTipoAnexo());
			} else {

				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Aviso!", "Não existe nenhum arquivo Anexado."));

				return;

			}

		} catch (FileNotFoundException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar efetuar o download da Arquivo");
			erro.printStackTrace();
		}
	}

	public void pesquisarAvancada() {
		try {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			departamentoPesq = usuario.getDepartamento().getNome();

			if (statusBusca.equals("Todos os Registros Selecionado")) {
				statusBusca = "";
			}

			if (prioridadeBusca.equals("Todos os Registros Selecionado")) {
				prioridadeBusca = "";
			}

			if (assuntoBusca.equals("Todos os Registros Selecionado")) {
				assuntoBusca = "";
			}

			if (solicitacaoBusca.equals("Todos os Registros Selecionado")) {
				solicitacaoBusca = "";
			}

			if (ticketBusca == null) {
				ticketBusca = 0L;
			}

			TicketDAO ticketDAO = new TicketDAO();
			tickets = ticketDAO.pesquisaAvancadoAdministrador(departamentoBusca, usuario.getCodigo(), statusBusca,
					prioridadeBusca, assuntoBusca, solicitacaoBusca, atendenteBusca, ticketBusca);

			ticket = null;

			if (tickets.isEmpty() == true) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Registro não Encontrado!", "Por favor tente novamente."));
			} else {
				org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoBusca').hide();");
			}

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Pesquisar Ticket.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void interacao(String descricao) {
		try {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			Interacao interacao = new Interacao();

			interacao.setInteracao(descricao);
			interacao.setData(new java.util.Date());
			interacao.setTicket(ticket);
			interacao.setUsuario(usuario);

			InteracaoDAO interacaoDAO = new InteracaoDAO();
			interacaoDAO.merge(interacao);

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar uma Interação.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}

	}

}
