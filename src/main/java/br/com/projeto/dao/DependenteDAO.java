package br.com.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.Dependente;
import br.com.projeto.util.HibernateUtil;

public class DependenteDAO extends GenericDAO<Dependente> {

	@SuppressWarnings({ "deprecation", "unchecked", "unused" })
	public List<Dependente> exibirDependente(Long Colaborador) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Dependente.class);

			Criteria consultaColaborador = consulta.createCriteria("colaborador", "colaborador", Criteria.INNER_JOIN,
					Restrictions.eq("colaborador.codigo", Colaborador));

			consulta.addOrder(Order.desc("codigo"));

			List<Dependente> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
