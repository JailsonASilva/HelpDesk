
package br.com.projeto.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.projeto.bean.AutenticacaoBean;
import br.com.projeto.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();

		boolean paginaDeAutenticacao = paginaAtual.contains("autenticacao.xhtml");
		boolean paginaBaseConhecimento = paginaAtual.contains("baseConhecimento.xhtml");
		boolean paginaFerias = paginaAtual.contains("ferias.xhtml");

		if (!paginaDeAutenticacao & !paginaBaseConhecimento) {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

			if (autenticacaoBean == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}

			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			if (usuario == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			} else {
				
				if (paginaFerias & !usuario.getAcesso().getFerias()) {
					Faces.navigate("/pages/autenticacao.xhtml");
					return;
				}
				
			}

		}

	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
