package br.com.projeto.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;

import br.com.projeto.bean.AutenticacaoBean;
import br.com.projeto.domain.Auditoria;
import br.com.projeto.domain.Usuario;
import br.com.projeto.util.HibernateUtil;

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

	@SuppressWarnings("unchecked")
	public List<Auditoria> pesquisarAuditoria(String acao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Auditoria.class);
			consulta.add(Restrictions.like("acao", "%" + acao + "%"));

			consulta.addOrder(Order.desc("data"));
			consulta.addOrder(Order.desc("hora"));
			consulta.addOrder(Order.desc("codigo"));

			List<Auditoria> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked", "unused" })
	public List<Auditoria> pesquisaAvancada(String acao, Long usuario, Date dataInicial, Date dataFinal) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Auditoria.class);

			if (usuario > 0L) {
				Criteria consultaUsuario = consulta.createCriteria("usuario", "usuario", Criteria.INNER_JOIN,
						Restrictions.eq("usuario.codigo", usuario));
			}

			consulta.add(Restrictions.like("acao", "%" + acao + "%"));
			consulta.add(Restrictions.between("data", dataInicial, dataFinal));

			consulta.addOrder(Order.desc("data"));
			consulta.addOrder(Order.desc("hora"));
			consulta.addOrder(Order.desc("codigo"));

			List<Auditoria> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
