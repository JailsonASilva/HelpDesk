package br.com.projeto.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import br.com.projeto.dao.AuditoriaDAO;
import br.com.projeto.dao.RpaDAO;
import br.com.projeto.domain.Rpa;
import br.com.projeto.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class RpaPendenteBean implements Serializable {

	private Rpa rpa;
	private List<Rpa> rpas;

	private Boolean pendente = true;
	private FacesMessage message;

	public Rpa getRpa() {
		return rpa;
	}

	public void setRpa(Rpa rpa) {
		this.rpa = rpa;
	}

	public List<Rpa> getRpas() {
		return rpas;
	}

	public void setRpas(List<Rpa> rpas) {
		this.rpas = rpas;
	}

	public Boolean getPendente() {
		return pendente;
	}

	public void setPendente(Boolean pendente) {
		this.pendente = pendente;
	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	@PostConstruct
	public void inicializar() {

		RpaDAO rpaDAO = new RpaDAO();
		rpas = rpaDAO.ListarPendentes(pendente);
	}

	public void pesquisar() {
		try {
			RpaDAO rpaDAO = new RpaDAO();
			rpas = rpaDAO.ListarPendentes(pendente);

			rpa = null;

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

	public void visualizar(ActionEvent evento) {
		try {
			rpa = (Rpa) evento.getComponent().getAttributes().get("registroSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
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

	public void salvar() {
		try {
			rpa.setRecebidoRH(true);

			RpaDAO rpaDAO = new RpaDAO();
			rpaDAO.merge(rpa);

			rpas = rpaDAO.ListarPendentes(pendente);

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Salvou RPA: " + rpa.getReferente());

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
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

	public void imprimirRpa() {
		try {
			rpa.setRecebidoRH(true);

			RpaDAO rpaDAO = new RpaDAO();
			rpaDAO.merge(rpa);

			if (rpa.getAprovado() == "Não" || rpa.getAprovado() == "Aguardando") {

				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Rpa não Aprovada",
						"Não possível imprimir a Rpa: Rpa não Aprovada.");

				RequestContext.getCurrentInstance().showMessageInDialog(message);

				return;
			}

			else

			if (rpa.getCarteiraIdentidade() == false || rpa.getCpf() == false || rpa.getTituloEleitor() == false
					|| rpa.getComprovanteResidencia() == false || rpa.getCartaoPIS() == false) {

				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Documento Pendente",
						"Não possível imprimir a Rpa: Documento(s) Pendente(s).");

				RequestContext.getCurrentInstance().showMessageInDialog(message);

				return;

			} else {

				String caminho = Faces.getRealPath("/impressao/rpa.jasper");
				Connection conexao = HibernateUtil.getConexao();

				Map<String, Object> parametros = new HashMap<>();
				parametros.put("CODIGO", rpa.getCodigo());

				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);

				JasperPrintManager.printReport(relatorio, true);
			}

		} catch (RuntimeException | JRException erro) {

			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Imprimir Rpa.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();

		}
	}

	public void imprimirRpaAtalho(ActionEvent evento) {
		try {
			rpa = (Rpa) evento.getComponent().getAttributes().get("registroSelecionado");

			rpa.setRecebidoRH(true);

			RpaDAO rpaDAO = new RpaDAO();
			rpaDAO.merge(rpa);

			if (rpa.getCarteiraIdentidade() == false || rpa.getCpf() == false || rpa.getTituloEleitor() == false
					|| rpa.getComprovanteResidencia() == false || rpa.getCartaoPIS() == false) {

				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Documento Pendente",
						"Não possível imprimir a Rpa: Documento(s) Pendente(s).");

				RequestContext.getCurrentInstance().showMessageInDialog(message);

				return;

			} else {

				String caminho = Faces.getRealPath("/impressao/rpa.jasper");
				Connection conexao = HibernateUtil.getConexao();

				Map<String, Object> parametros = new HashMap<>();
				parametros.put("CODIGO", rpa.getCodigo());

				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);

				JasperPrintManager.printReport(relatorio, true);
			}

		} catch (RuntimeException | JRException erro) {

			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Imprimir Rpa.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();

		}
	}

	public void aprovar() {
		try {
			RpaDAO rpaDAO = new RpaDAO();
			rpaDAO.merge(rpa);

			if (rpa.getCarteiraIdentidade() == false || rpa.getCpf() == false || rpa.getTituloEleitor() == false
					|| rpa.getComprovanteResidencia() == false || rpa.getCartaoPIS() == false) {

				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Documento Pendente",
						"Não possível Aprovar a Rpa: Documento(s) Pendente(s).");

				RequestContext.getCurrentInstance().showMessageInDialog(message);

				return;

			} else {

				rpa.setPendente(false);
				rpa.setAprovado("Sim");

				rpaDAO.merge(rpa);

				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Aviso!", "Rpa aprovada com Sucesso"));
			}

		} catch (RuntimeException erro) {

			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Aprovar Rpa.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();

		}
	}

	public void aprovarAtalho(ActionEvent evento) {
		try {
			rpa = (Rpa) evento.getComponent().getAttributes().get("registroSelecionado");

			RpaDAO rpaDAO = new RpaDAO();
			rpaDAO.merge(rpa);

			if (rpa.getCarteiraIdentidade() == false || rpa.getCpf() == false || rpa.getTituloEleitor() == false
					|| rpa.getComprovanteResidencia() == false || rpa.getCartaoPIS() == false) {

				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Documento Pendente",
						"Não possível Aprovar a Rpa: Documento(s) Pendente(s).");

				RequestContext.getCurrentInstance().showMessageInDialog(message);

				return;

			} else {

				rpa.setPendente(false);
				rpa.setAprovado("Sim");

				rpaDAO.merge(rpa);

				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage("Aviso!", "Rpa aprovada com Sucesso"));
			}

		} catch (RuntimeException erro) {

			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Aprovar Rpa.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();

		}
	}

	public void exibirReprovacao(ActionEvent evento) {
		try {
			rpa = (Rpa) evento.getComponent().getAttributes().get("registroSelecionado");

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoReprovar').show();");

		} catch (RuntimeException erro) {

			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Exibir Reprovação.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();

		}
	}

	public void reprovar() {
		try {
			rpa.setRecebidoRH(true);
			rpa.setAprovado("Não");
			rpa.setPendente(false);

			RpaDAO rpaDAO = new RpaDAO();
			rpaDAO.merge(rpa);

			pesquisar();

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Reprovou RPA: " + rpa.getReferente());

			FacesContext context = FacesContext.getCurrentInstance();

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoReprovar').hide();");
			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			context.addMessage(null, new FacesMessage("Aviso!", "Rpa Reprovada com Sucesso"));

		} catch (RuntimeException erro) {

			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Reprovar Rpa.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();

		}
	}

}
