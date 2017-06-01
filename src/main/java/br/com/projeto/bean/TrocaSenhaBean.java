package br.com.projeto.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;

import br.com.projeto.dao.AuditoriaDAO;
import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.domain.Usuario;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class TrocaSenhaBean implements Serializable {

	private AutenticacaoBean autenticacaoBean;
	private Usuario usuario;

	private String senhaAtual;
	private String novaSenha;
	private String confirmaSenha;

	private FacesMessage message;

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
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

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public void trocarSenha() {
		try {
			autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			usuario = autenticacaoBean.getUsuarioLogado();

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuarioLogado = usuarioDAO.autenticar(usuario.getEmail(), senhaAtual);

			if (usuarioLogado == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha Atual",
						"Senha Atual Inválida! Por favor tente novamente.");

				RequestContext.getCurrentInstance().showMessageInDialog(message);

				return;

			}

			if (!novaSenha.equals(confirmaSenha)) {

				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha Inválida",
						"Senhas não coincidem! Por favor tente novamente.");

				RequestContext.getCurrentInstance().showMessageInDialog(message);

				return;
			}

			SimpleHash hash = new SimpleHash("md5", novaSenha);
			usuario.setSenha(hash.toHex());

			usuarioDAO.merge(usuario);

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Aviso!", "Senha Salva com Sucesso!"));

			AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
			auditoriaDAO.auditar("Trocou de Senha");

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Trocar Senha.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

}
