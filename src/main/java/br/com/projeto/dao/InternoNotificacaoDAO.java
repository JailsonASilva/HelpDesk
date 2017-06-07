package br.com.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.InternoNotificacao;
import br.com.projeto.domain.Usuario;
import br.com.projeto.util.HibernateUtil;

public class InternoNotificacaoDAO extends GenericDAO<InternoNotificacao> {

	@SuppressWarnings({ "unchecked" })
	public List<InternoNotificacao> pesquisarInternoNotificacao() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(InternoNotificacao.class);

			List<InternoNotificacao> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void salvarInteracaoNotificacao(Long Ticket, Long Usuario, Boolean lido) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {

			String Interno;
			String InternoNotificacao;

			sessao.getTransaction().begin();

			Interno = (String) sessao
					.createSQLQuery("SELECT LAST_INSERT_ID(codigo) FROM interno ORDER BY codigo DESC LIMIT 1")
					.uniqueResult().toString();

			List<InternoNotificacao> notificacoes = pesquisarInternoNotificacao();

			if (notificacoes.size() > 0) {

				InternoNotificacao = (String) sessao
						.createSQLQuery(
								"SELECT LAST_INSERT_ID(codigo) + 1 FROM internonotificacao ORDER BY codigo DESC LIMIT 1")
						.uniqueResult().toString();

			} else {
				InternoNotificacao = "1";
			}

			sessao.createSQLQuery(
					"INSERT INTO internonotificacao (codigo, interno_codigo, ticket_codigo, usuario_codigo, lido) VALUES ("
							+ InternoNotificacao + "," + Interno + "," + Ticket.toString() + "," + Usuario.toString()
							+ "," + lido.toString() + ")")
					.executeUpdate();

			sessao.getTransaction().commit();

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	public List<InternoNotificacao> pesquisarInternoNaoLido(Usuario usuario) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(InternoNotificacao.class);

			Criteria consultaInterno = consulta.createCriteria("interno", "interno", Criteria.INNER_JOIN,
					Restrictions.ne("interno.usuario", usuario));

			consulta.add(Restrictions.eq("usuario", usuario));
			consulta.add(Restrictions.eq("lido", false));
			consulta.addOrder(Order.desc("codigo"));

			List<InternoNotificacao> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	public List<InternoNotificacao> listarInternos(Long ticket) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(InternoNotificacao.class);

			Criteria consultaInterno = consulta.createCriteria("interno", "interno", Criteria.INNER_JOIN);

			Criteria consultaTicket = consultaInterno.createCriteria("ticket", "ticket", Criteria.INNER_JOIN,
					Restrictions.eq("ticket.codigo", ticket));

			consulta.addOrder(Order.desc("interno.data"));
			consulta.addOrder(Order.desc("interno.codigo"));

			List<InternoNotificacao> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	public List<InternoNotificacao> pesquisarMensagem(String busca) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(InternoNotificacao.class);


			Criteria consultaInterno = consulta.createCriteria("interno", "interno", Criteria.INNER_JOIN,
					Restrictions.like("interno.observacao", "%" + busca + "%")); 

			consulta.addOrder(Order.desc("interno.data"));
			consulta.addOrder(Order.desc("interno.codigo"));

			List<InternoNotificacao> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}	
}
