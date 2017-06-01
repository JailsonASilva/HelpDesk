package br.com.projeto.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.projeto.dao.ArtigoDAO;
import br.com.projeto.dao.AuditoriaDAO;
import br.com.projeto.domain.Artigo;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class BaseConhecimentoBean implements Serializable {
	private Artigo artigo;
	private List<Artigo> artigos;

	private StreamedContent arquivo;
	
	private FacesMessage message;
	private String busca;

	public Artigo getArtigo() {
		return artigo;
	}

	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}

	public List<Artigo> getArtigos() {
		return artigos;
	}

	public void setArtigos(List<Artigo> artigos) {
		this.artigos = artigos;
	}

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

	public StreamedContent getArquivo() {
		return arquivo;
	}

	public void setArquivo(StreamedContent arquivo) {
		this.arquivo = arquivo;
	}

	public void pesquisar() {
		try {
			ArtigoDAO artigoDAO = new ArtigoDAO();
			artigos = artigoDAO.pesquisarBaseConhecimento(busca);
			
			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Pesquisou Base de Conhecimento: '" + busca + "'");			

			if (artigos.isEmpty() == true) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Registro não Encontrado!", "Por favor tente novamente."));
			}

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Pesquisar Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void download(ActionEvent evento) {
		try {
			artigo = (Artigo) evento.getComponent().getAttributes().get("artigoSelecionado");

			InputStream stream = new FileInputStream("C:/Artigos/" + artigo.getCodigo() + ".pdf");
			arquivo = new DefaultStreamedContent(stream, "pdf/pdf", artigo.getCodigo() + ".pdf");
			
			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Baixou Anexo Base de Conhecimento: " + artigo.getTitulo());				

		} catch (FileNotFoundException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar efetuar o download da foto");
			erro.printStackTrace();
		}
	}

}
