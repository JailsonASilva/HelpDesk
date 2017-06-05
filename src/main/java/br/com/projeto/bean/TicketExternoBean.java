package br.com.projeto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;

import br.com.projeto.dao.AuditoriaDAO;
import br.com.projeto.dao.DepartamentoDAO;
import br.com.projeto.dao.TicketDAO;
import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.domain.Departamento;
import br.com.projeto.domain.Ticket;
import br.com.projeto.domain.Usuario;
import br.com.projeto.util.EmailUtils;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TicketExternoBean implements Serializable {
	private Ticket ticket;
	private Departamento departamento;

	private List<Ticket> tickets;
	private List<Departamento> departamentos;
	private List<Usuario> usuarios;

	private AutenticacaoBean autenticacaoBean;
	private Usuario usuario;

	private FacesMessage message;
	private String busca;
	private String buscaDepartamento;

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

	public String getBuscaDepartamento() {
		return buscaDepartamento;
	}

	public void setBuscaDepartamento(String buscaDepartamento) {
		this.buscaDepartamento = buscaDepartamento;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void inicializar() {
		try {
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.listarAtendimento();

			novo();

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Inicializar Rotinas.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void novo() {
		autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
		usuario = autenticacaoBean.getUsuarioLogado();

		departamento = new Departamento();

		ticket = new Ticket();
		ticket.setUsuario(usuario);
		ticket.setDataAbertura(new java.util.Date());
		ticket.setUltimaInteracao(new java.util.Date());
		ticket.setStatus("Pendente");
		ticket.setPrioridade("Normal");
		ticket.setEmailDepartamento(false);
		ticket.setEmailSolicitante(false);
	}

	public void salvar() throws EmailException {
		try {
			TicketDAO ticketDAO = new TicketDAO();

			ticket.setEmailDepartamento(false);
			ticket.setEmailSolicitante(false);

			ticketDAO.merge(ticket);

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Abriu Ticket Externo");

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Ticket Aberto com Sucesso!",
					"Para acompanhar o andamento de seu Ticket acesse o menu Meus Ticket's"));

			novo();

		} catch (

		RuntimeException erro) {
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
}
