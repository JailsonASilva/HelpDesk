package br.com.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.Ocorrencia;
import br.com.projeto.util.HibernateUtil;

public class OcorrenciaDAO extends GenericDAO<Ocorrencia> {

	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	public List<Ocorrencia> pesquisarOcorrenciaTicket(Long ticket) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Ocorrencia.class);			
			
			Criteria consultaTicket = consulta.createCriteria("ticket", "ticket",
					Criteria.INNER_JOIN, Restrictions.eq("ticket.codigo", ticket));			
			
			consulta.addOrder(Order.desc("data"));
			consulta.addOrder(Order.desc("codigo"));
			
			List<Ocorrencia> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
