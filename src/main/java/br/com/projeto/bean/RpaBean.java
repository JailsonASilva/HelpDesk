package br.com.projeto.bean;

import java.io.Serializable;
import java.util.Date;
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
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.AuditoriaDAO;
import br.com.projeto.dao.FornecedorDAO;
import br.com.projeto.dao.RpaDAO;
import br.com.projeto.domain.Fornecedor;
import br.com.projeto.domain.Rpa;
import br.com.projeto.domain.Usuario;
import br.com.projeto.util.EmailUtils;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RpaBean implements Serializable {
	private Rpa rpa;
	private List<Rpa> rpas;

	private Fornecedor fornecedor;
	private List<Fornecedor> fornecedores;

	private Fornecedor fornecedorBusca;
	private List<Fornecedor> fornecedoresBusca;
	private Date dataInicial;
	private Date dataFinal;
	private String referenteBusca;

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

	public List<Rpa> getRpas() {
		return rpas;
	}

	public void setRpas(List<Rpa> rpas) {
		this.rpas = rpas;
	}

	public Rpa getRpa() {
		return rpa;
	}

	public void setRpa(Rpa rpa) {
		this.rpa = rpa;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<Fornecedor> getFornecedoresBusca() {
		return fornecedoresBusca;
	}

	public void setFornecedoresBusca(List<Fornecedor> fornecedoresBusca) {
		this.fornecedoresBusca = fornecedoresBusca;
	}

	public Fornecedor getFornecedorBusca() {
		return fornecedorBusca;
	}

	public void setFornecedorBusca(Fornecedor fornecedorBusca) {
		this.fornecedorBusca = fornecedorBusca;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getReferenteBusca() {
		return referenteBusca;
	}

	public void setReferenteBusca(String referenteBusca) {
		this.referenteBusca = referenteBusca;
	}

	@PostConstruct
	public void inicializarTabelas() {
		try {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listar("nome");
			fornecedoresBusca = fornecedorDAO.listar("nome");

			dataInicial = new Date();
			dataFinal = new Date();

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Pesquisar Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void pesquisar() {
		try {
			RpaDAO rpaDAO = new RpaDAO();
			rpas = rpaDAO.pesquisarRpa(busca);

			rpa = null;

			fornecedor = new Fornecedor();

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Pesquisou RPA's");

			if (rpas.isEmpty() == true) {
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

		rpa = new Rpa();

		rpa.setUsuario(usuario);
		rpa.setAprovado("Aguardando");
		rpa.setRecebidoRH(false);
		rpa.setCarteiraIdentidade(false);
		rpa.setCpf(false);
		rpa.setTituloEleitor(false);
		rpa.setPendente(true);
		rpa.setComprovanteResidencia(false);
		rpa.setCartaoPIS(false);
		rpa.setContaBancaria(false);
		rpa.setData(new Date());
		rpa.setPagouISS("NÃO");
		rpa.setValorLiquido("NÃO");

		fornecedor = new Fornecedor();
	}

	public void salvar() {
		try {
			RpaDAO rpaDAO = new RpaDAO();
			rpaDAO.merge(rpa);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			rpas = rpaDAO.pesquisarRpa("");

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Salvou RPA: " + rpa.getReferente());

			if (rpa.getCodigo() == null) {

				enviarEmail();

				auditoriaDAO.auditar("E-mail de Solicição de RPA enviado para o RH");

				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Aviso!", "Solicitação Enviada para o RH com Sucesso!"));

			} else {

				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));
			}

			rpa = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();

		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void salvarEnviarEmail() {
		try {
			RpaDAO rpaDAO = new RpaDAO();
			rpaDAO.merge(rpa);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			rpas = rpaDAO.pesquisarRpa("");

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Salvou RPA: " + rpa.getReferente());

			enviarEmail();

			auditoriaDAO.auditar("E-mail de Solicição de RPA enviado para o RH");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Solicitação Enviada para o RH com Sucesso!"));

			rpa = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar e Enviar E-mail.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();

		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			RpaDAO rpaDAO = new RpaDAO();
			rpaDAO.excluir(rpa);

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Excluído com Sucesso"));

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Excluir RPA: " + rpa.getReferente());

			rpas = rpaDAO.pesquisarRpa("");

			rpa = null;

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
		rpa = null;
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
			rpa = (Rpa) evento.getComponent().getAttributes().get("registroSelecionado");

			RpaDAO rpaDAO = new RpaDAO();
			rpaDAO.excluir(rpa);

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Excluir RPA: " + rpa.getReferente());

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null,
					new FacesMessage("Registro Excluído com Sucesso!", "Registro: " + rpa.getFornecedor().getNome()));

			rpas = rpaDAO.pesquisarRpa("");

			rpa = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			rpa = (Rpa) evento.getComponent().getAttributes().get("registroSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarFornecedor() {
		try {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.merge(fornecedor);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoFornecedor').hide();");

			fornecedores = fornecedorDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			fornecedor = new Fornecedor();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void enviarEmail() throws EmailException {
		try {
			String mensagem = "Solicitação de RPA " + "\n" + "\n" + "Data Solicitação: " + rpa.getDataFormatada() + "\n"
					+ "Solicitante: " + rpa.getUsuario().getNome() + "\n" + "Fornecedor: "
					+ rpa.getFornecedor().getNome() + "\n" + "" + "\n" +

					"Dados da Solicitação " + "\n" + "\n" + "Data Início: " + rpa.getDataInicioFortamada() + "\n"
					+ "Data Prevista Fim: " + rpa.getDataPrevistaFimFortamada() + "\n" + "Valor: "
					+ rpa.getValorFormatado() + "\n" + "Referente: " + rpa.getReferente() + "\n";

			EmailUtils.enviaEmail("Solicitação de RPA", mensagem, "jailson_silva@facema.edu.br");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Enviar E-mail",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}

	}

	public void pesquisarAvancada() {
		try {
			RpaDAO rpaDAO = new RpaDAO();
			rpas = rpaDAO.buscaAvancada(dataInicial, dataFinal, fornecedorBusca, referenteBusca);
			
			if (rpas.isEmpty() == true) {

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
}