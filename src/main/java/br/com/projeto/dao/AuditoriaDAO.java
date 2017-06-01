package br.com.projeto.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.faces.application.FacesMessage;

import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;

import br.com.projeto.bean.AutenticacaoBean;
import br.com.projeto.domain.Auditoria;
import br.com.projeto.domain.Usuario;

public class AuditoriaDAO extends GenericDAO<Auditoria> {

	public void auditar(String acao) {
		try {

			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			Auditoria auditoria = new Auditoria();

			auditoria.setAcao(acao);
			auditoria.setData(new Date());
			auditoria.setHora(new Date());
			auditoria.setIp(InetAddress.getLocalHost().getHostAddress());
			auditoria.setUsuario(usuario);

			merge(auditoria);

		} catch (

		RuntimeException | UnknownHostException erro) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Salvar Auditoria", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}
}
