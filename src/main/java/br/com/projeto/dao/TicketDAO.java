package br.com.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.Ticket;
import br.com.projeto.util.HibernateUtil;

public class TicketDAO extends GenericDAO<Ticket> {

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Ticket> pesquisarDepartamento(String departamento, String status) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Ticket.class);
			
			@SuppressWarnings("unused")
			Criteria consultaDepartamento = consulta.createCriteria("departamento", "departamento",
					Criteria.INNER_JOIN, Restrictions.like("departamento.nome", "%" + departamento + "%"));
			
			consulta.add(Restrictions.eq("status", status));
			
			consulta.addOrder(Order.asc("prioridade"));
			consulta.addOrder(Order.asc("dataAbertura"));

			List<Ticket> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
