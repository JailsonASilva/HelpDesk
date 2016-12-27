package br.com.projeto.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
	}

	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getNome(), usuario.getSenha());
			
			if(usuarioLogado == null){
				Messages.addGlobalError("Login e/ou Senha Incorretos");
				return;
			}
			
			Faces.redirect("./pages/principal.xhtml");
			
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível Realizar a Autenticação. Erro: " + erro.getMessage());
		}
	}
	
//	public boolean temPermissoes(List<String> permissoes){	
//		for(String permissao : permissoes){
//			if(usuarioLogado.getTipo() == permissao.charAt(0)){
//				return true;
//			}
//		}
//		
//		return false;
//	}
	
}
