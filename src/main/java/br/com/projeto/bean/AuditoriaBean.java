package br.com.projeto.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.AuditoriaDAO;
import br.com.projeto.domain.Auditoria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AuditoriaBean implements Serializable {
	
	private Auditoria audtoria;
	private List<Auditoria> audtorias;

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

	public List<Auditoria> getAuditorias() {
		return audtorias;
	}

	public void setAuditorias(List<Auditoria> audtorias) {
		this.audtorias = audtorias;
	}

	public Auditoria getAuditoria() {
		return audtoria;
	}

	public void setAuditoria(Auditoria audtoria) {
		this.audtoria = audtoria;
	}

	public void pesquisar() {
		try {
			AuditoriaDAO audtoriaDAO = new AuditoriaDAO();
			audtorias = audtoriaDAO.pesquisar(busca);

			audtoria = null;

			if (audtorias.isEmpty() == true) {
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

	public void onRowSelect(SelectEvent event) {

	}

	public void onRowUnselect(UnselectEvent event) {
		audtoria = null;
	}
}