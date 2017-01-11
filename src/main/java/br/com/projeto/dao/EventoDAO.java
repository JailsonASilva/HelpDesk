package br.com.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.Evento;
import br.com.projeto.util.HibernateUtil;

public class EventoDAO extends GenericDAO<Evento> {

	@SuppressWarnings("unchecked")
	public List<Evento> pesquisarEvento(String titulo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Evento.class);
			consulta.add(Restrictions.like("titulo", "%" + titulo + "%"));
			consulta.addOrder(Order.asc("titulo"));

			List<Evento> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
