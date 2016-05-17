package br.clinica.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import br.clinica.domain.Pessoa;
import br.clinica.util.HibernateUtil;

public class PessoaDAO extends GenericDAO<Pessoa> {

	public Long proximoID() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria criteria = sessao.createCriteria(Pessoa.class);

			Long id = (Long) criteria.setProjection(Projections.max("codigo")).uniqueResult();

			return id + 1L;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
