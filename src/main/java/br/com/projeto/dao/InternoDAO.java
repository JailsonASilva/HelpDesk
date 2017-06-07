package br.com.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.Interno;
import br.com.projeto.util.HibernateUtil;

public class InternoDAO extends GenericDAO<Interno> {

	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	public List<Interno> listarInternos(Long ticket) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Interno.class);

			Criteria consultaTicket = consulta.createCriteria("ticket", "ticket", Criteria.INNER_JOIN,
					Restrictions.eq("ticket.codigo", ticket));

			consulta.addOrder(Order.desc("data"));
			consulta.addOrder(Order.desc("codigo"));

			List<Interno> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	public List<Interno> pesquisarInternoTicket(Long ticket) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Interno.class);

			Criteria consultaTicket = consulta.createCriteria("ticket", "ticket", Criteria.INNER_JOIN,
					Restrictions.eq("ticket.codigo", ticket));

			consulta.addOrder(Order.desc("data"));
			consulta.addOrder(Order.desc("codigo"));

			List<Interno> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
