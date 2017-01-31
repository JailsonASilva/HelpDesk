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

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.projeto.dao.ArtigoDAO;
import br.com.projeto.dao.ClassificacaoDAO;
import br.com.projeto.dao.NivelDAO;
import br.com.projeto.domain.Artigo;
import br.com.projeto.domain.Classificacao;
import br.com.projeto.domain.Nivel;
import br.com.projeto.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ArtigoBean implements Serializable {
	private Artigo artigo;
	private List<Artigo> artigos;

	private List<Nivel> niveis;
	private List<Classificacao> classificacoes;

	private Nivel nivel;
	private Classificacao classificacao;

	private FacesMessage message;

	private String busca;
	private String buscaNivel;
	private String buscaClassificacao;

	private StreamedContent arquivo;

	private AutenticacaoBean autenticacaoBean;
	private Usuario usuario;

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

	public List<Artigo> getArtigos() {
		return artigos;
	}

	public void setArtigos(List<Artigo> artigos) {
		this.artigos = artigos;
	}

	public Artigo getArtigo() {
		return artigo;
	}

	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
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

	public List<Nivel> getNiveis() {
		return niveis;
	}

	public void setNiveis(List<Nivel> niveis) {
		this.niveis = niveis;
	}

	public List<Classificacao> getClassificacoes() {
		return classificacoes;
	}

	public void setClassificacoes(List<Classificacao> classificacoes) {
		this.classificacoes = classificacoes;
	}

	public StreamedContent getArquivo() {
		return arquivo;
	}

	public void setArquivo(StreamedContent arquivo) {
		this.arquivo = arquivo;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public String getBuscaNivel() {
		return buscaNivel;
	}

	public void setBuscaNivel(String buscaNivel) {
		this.buscaNivel = buscaNivel;
	}

	public String getBuscaClassificacao() {
		return buscaClassificacao;
	}

	public void setBuscaClassificacao(String buscaClassificacao) {
		this.buscaClassificacao = buscaClassificacao;
	}
	
	@PostConstruct
	public void abrirTabelas() {
		try {
			NivelDAO nivelDAO = new NivelDAO();
			niveis = nivelDAO.listar("nome");
			
			ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();
			classificacoes = classificacaoDAO.listar("nome");

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
			ArtigoDAO artigoDAO = new ArtigoDAO();
			artigos = artigoDAO.pesquisarPalavraChave(busca);

			artigo = null;

			nivel = new Nivel();
			classificacao = new Classificacao();

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

	public void novo() {
		autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
		usuario = autenticacaoBean.getUsuarioLogado();

		artigo = new Artigo();

		artigo.setAutor(usuario);
		artigo.setDataCadastro(new java.util.Date());
		artigo.setAtivo(true);

		nivel = new Nivel();
		classificacao = new Classificacao();
	}

	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			artigo.setCaminho(arquivoTemp.toString());

		} catch (IOException erro) {
			Messages.addGlobalInfo("Ocorreu um erro ao tentar realizar o upload de arquivo");
			erro.printStackTrace();
		}
	}

	public void download(ActionEvent evento) {
		try {
			artigo = (Artigo) evento.getComponent().getAttributes().get("artigoSelecionado");

			InputStream stream = new FileInputStream("C:/Artigos/" + artigo.getCodigo() + ".pdf");
			arquivo = new DefaultStreamedContent(stream, "pdf/pdf", artigo.getCodigo() + ".pdf");

		} catch (FileNotFoundException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar efetuar o download da foto");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if (artigo.getCaminho() == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Campo Obrigatório!", "Informe o Arquivo a ser Anexado!"));
				
				return;
			}

			ArtigoDAO artigoDAO = new ArtigoDAO();
			Artigo artigoRetorno = artigoDAO.merge(artigo);

			Path origem = Paths.get(artigo.getCaminho());
			Path destino = Paths.get("C:/Artigos/" + artigoRetorno.getCodigo() + ".pdf");

			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			artigos = artigoDAO.listar("titulo");

			artigo = null;

		} catch (RuntimeException | IOException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarNivel() {
		try {
			NivelDAO nivelDAO = new NivelDAO();
			nivelDAO.merge(nivel);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoNivel').hide();");

			nivel = new Nivel();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarClassificacao() {
		try {
			ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();
			classificacaoDAO.merge(classificacao);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoClassificacao').hide();");

			classificacao = new Classificacao();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			ArtigoDAO artigoDAO = new ArtigoDAO();
			artigoDAO.excluir(artigo);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + artigo.getTitulo());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			artigos = artigoDAO.listar("titulo");

			artigo = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) {
		artigo.setCaminho("C:/Artigos/" + artigo.getCodigo() + ".pdf");
	}

	public void onRowUnselect(UnselectEvent event) {
		artigo = null;
	}

	public void pesquisarNivel() {
		try {
			NivelDAO nivelDAO = new NivelDAO();
			niveis = nivelDAO.pesquisar(buscaNivel);

			if (niveis.isEmpty() == true) {
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

	public void selecionarNivel(ActionEvent evento) {
		try {

			Nivel nivelPesq = (Nivel) evento.getComponent().getAttributes().get("nivelSelecionado");

			artigo.setNivel(nivelPesq);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqNivel').hide();");
			org.primefaces.context.DefaultRequestContext.getCurrentInstance().update(":formCadastro:nivel");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void pesquisarClassificacao() {
		try {
			ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();
			classificacoes = classificacaoDAO.pesquisar(buscaClassificacao);

			if (classificacoes.isEmpty() == true) {
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

	public void selecionarClassificacao(ActionEvent evento) {
		try {

			Classificacao classificacaoPesq = (Classificacao) evento.getComponent().getAttributes()
					.get("classificacaoSelecionado");

			artigo.setClassificacao(classificacaoPesq);

			org.primefaces.context.RequestContext.getCurrentInstance()
					.execute("PF('dialogoPesqClassificacao').hide();");
			org.primefaces.context.DefaultRequestContext.getCurrentInstance().update(":formCadastro:classificacao");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}
	
	public void duploCliqueNivel(SelectEvent evento) {
		try {
			artigo.setNivel(nivel);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqNivel').hide();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}
	
	public void duploCliqueClassificacao(SelectEvent evento) {
		try {
			artigo.setClassificacao(classificacao);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqClassificacao').hide();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}	

}
