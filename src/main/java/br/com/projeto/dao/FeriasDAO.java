package br.com.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.Ferias;
import br.com.projeto.util.HibernateUtil;

public class FeriasDAO extends GenericDAO<Ferias> {

	@SuppressWarnings({ "deprecation", "unchecked", "unused" })
	public List<Ferias> pesquisarFerias(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Ferias.class);

			Criteria consultaColaborador = consulta.createCriteria("colaborador", "colaborador", Criteria.INNER_JOIN,
					Restrictions.like("colaborador.nome", "%" + nome + "%"));

			consulta.addOrder(Order.desc("codigo"));

			List<Ferias> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked", "unused" })
	public List<Ferias> exibirFerias(Long Colaborador) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Ferias.class);

			Criteria consultaColaborador = consulta.createCriteria("colaborador", "colaborador", Criteria.INNER_JOIN,
					Restrictions.eq("colaborador.codigo", Colaborador));

			consulta.addOrder(Order.desc("codigo"));

			List<Ferias> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
